package com.vgmsistemas.vgmweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.vgmsistemas.vgmweb.dto.CustomerWsDto;
import com.vgmsistemas.vgmweb.dto.PkCustomerWsDto;
import com.vgmsistemas.vgmweb.dto.DailyOrderDetailWsDto;
import com.vgmsistemas.vgmweb.dto.DailyOrderWsDto;
import com.vgmsistemas.vgmweb.dto.DetailViewWsDto;

import com.vgmsistemas.vgmweb.dto.ItemWsDto;
import com.vgmsistemas.vgmweb.entity.Cliente;
import com.vgmsistemas.vgmweb.service.rest.VentaWs;

@Service
public class VentaService {
	
	@Autowired
	ClienteService clienteService;	
	PkCustomerWsDto pkCustomer;	
	CustomerWsDto customer; 	
	DailyOrderWsDto dailyOrder;
	VentaWs ventaWs;
	
	public int generarVenta(DetailViewWsDto detallePedido) {
		try {
			/**** Armo la venta ****/
			// Obtengo el usuario para poder obtener el cliente 
			Authentication auth = SecurityContextHolder
					.getContext()
					.getAuthentication();
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			String usuario = userDetail.getUsername();				
			Cliente cliente = clienteService.getClienteByUsuario(usuario);
			pkCustomer = new PkCustomerWsDto();
			pkCustomer.setIdSucursal(cliente.getId().getIdSucursal());
			pkCustomer.setIdCliente(cliente.getId().getIdCliente());
			pkCustomer.setIdComercio(cliente.getId().getIdComercio());
			customer = new CustomerWsDto();
			customer.setId(pkCustomer);
			
			String idMovil = "web-PD-" + pkCustomer.getIdSucursal()+ "-" + pkCustomer.getIdCliente()
							 + "-" + pkCustomer.getIdComercio()+ "-" 
							 + detallePedido.getVentaDetalle().get(0).getIdMovil();
			dailyOrder = new DailyOrderWsDto();
			dailyOrder.setCliente(customer);
			dailyOrder.setIdMovil(idMovil);
			dailyOrder.setPie("Creado por el usuario: " + usuario);
			dailyOrder.setVentaDetalle(detallePedido.getVentaDetalle()); 
			
			/*** Mando el pedido ***/
			ventaWs = new VentaWs();
			int respuesta = ventaWs.send(dailyOrder);
			
			return respuesta;
		} catch (Exception e) {
			// TODO: handle exception
			return -1;
		}
		
	}
}
