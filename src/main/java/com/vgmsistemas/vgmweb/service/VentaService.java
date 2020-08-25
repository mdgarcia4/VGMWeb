package com.vgmsistemas.vgmweb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.vgmsistemas.vgmweb.dto.CustomerWsDto;
import com.vgmsistemas.vgmweb.dto.PkCustomerWsDto;
import com.vgmsistemas.vgmweb.dto.DailyOrderWsDto;
import com.vgmsistemas.vgmweb.dto.DetailViewWsDto;

import com.vgmsistemas.vgmweb.entity.Cliente;
import com.vgmsistemas.vgmweb.service.rest.VentaWs;
import com.vgmsistemas.vgmweb.util.CodeResult;

@Service
public class VentaService {
	@Autowired
	VentaWs ventaWs;
	@Autowired
	ClienteService clienteService;
	PkCustomerWsDto pkCustomer;
	CustomerWsDto customer;
	DailyOrderWsDto dailyOrder;
	static Logger logger = LoggerFactory.getLogger(VentaService.class);

	public String generarVenta(DetailViewWsDto detallePedido) throws Exception {
		try {
			/**** Armo la venta ****/
			// Obtengo el usuario para poder obtener el cliente
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			String usuario = userDetail.getUsername();
			// Cliente cliente = clienteService.getClienteByUsuario(usuario);
			Cliente cliente = clienteService.getClienteByUsuarioComercio(usuario,
					detallePedido.getComercioSeleccionado());
			pkCustomer = new PkCustomerWsDto();
			pkCustomer.setIdSucursal(cliente.getId().getIdSucursal());
			pkCustomer.setIdCliente(cliente.getId().getIdCliente());
			pkCustomer.setIdComercio(cliente.getId().getIdComercio());
			customer = new CustomerWsDto();
			customer.setId(pkCustomer);

			String idMovil = "WEB-PD-" + pkCustomer.getIdSucursal() + "-" + pkCustomer.getIdCliente() + "-"
					+ pkCustomer.getIdComercio() + "-" + detallePedido.getVentaDetalle().get(0).getIdMovil();
			dailyOrder = new DailyOrderWsDto();
			dailyOrder.setCliente(customer);
			dailyOrder.setIdMovil(idMovil);
			dailyOrder.setPie("Creado por el usuario: " + usuario);
			dailyOrder.setVentaDetalle(detallePedido.getVentaDetalle());

			/*** Mando el pedido ***/
			int respuesta = ventaWs.send(dailyOrder);
			if (respuesta == 0) {
				return String.valueOf(CodeResult.RESULT_OK);
			} else // por errores se ingresa aquí
			{
				return "StatusCode:" + String.valueOf(respuesta) + "-" + CodeResult.getHttpError(respuesta);/* "0"; */
			}

		} catch (Exception e) {
			logger.error("Error inesperado en clase VentaService-Met: generarVenta. " + e.getMessage());
			logger.error("Error inesperado en clase VentaService-Met: generarVenta. " + e.getStackTrace());
			throw e;
		}

	}
}
