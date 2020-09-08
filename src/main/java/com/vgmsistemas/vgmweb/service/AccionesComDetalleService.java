package com.vgmsistemas.vgmweb.service;


import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.vgmsistemas.vgmweb.entity.AccionesCom;
import com.vgmsistemas.vgmweb.entity.AccionesComDetalle;
import com.vgmsistemas.vgmweb.entity.Cliente;
import com.vgmsistemas.vgmweb.entity.ListaPrecio;
import com.vgmsistemas.vgmweb.entity.ListaPrecioDetalle;
import com.vgmsistemas.vgmweb.entity.Sucursal;
import com.vgmsistemas.vgmweb.repository.AccionesComDetalleRepo;

@Service
public class AccionesComDetalleService {
	@Autowired
	AccionesComDetalleRepo accionesComDetRepo;
	@Autowired
	ListaPrecioService listaPrecioService;
	@Autowired
	SucursalService sucursalService;
	Float _cantidadPedida = 1F; // se pone 1 como es para calcular el precio del articulo
	static Logger logger = LoggerFactory.getLogger(AccionesComDetalleService.class);

	public void validarAccionesComDetalle(Page<ListaPrecioDetalle> listadoArticulos, Cliente cliente) throws Exception {
		try {
			ListaPrecio listaPrecio = null;
			for (Iterator<ListaPrecioDetalle> iterator = listadoArticulos.iterator(); iterator.hasNext();) {
				ListaPrecioDetalle listaPrecioDetalle = (ListaPrecioDetalle) iterator.next();
				// busco la lista de precio para ver el ti_lista
				if (listaPrecio == null) {
					// Busco por la lista del listado
					Optional<ListaPrecio> optListaPrecio = listaPrecioService
							.getByIdListaPrecio(listaPrecioDetalle.getId().getIdLista());
					// asigno lista de precio del listado si existe sino asgino lista 1
					listaPrecio = optListaPrecio.isPresent() ? optListaPrecio.get()
							: listaPrecioService.getByIdListaPrecio(1L).get();
				}
				// determino si es necesario aplicar acciones comerciales.
				if (listaPrecio.getTipoLista() == ListaPrecio.TIPO_LISTA_BASE
						|| listaPrecio.getTipoLista() == ListaPrecio.TIPO_LISTA_BASE_X_ART_LIBRE
						|| listaPrecio.getTipoLista() == ListaPrecio.TIPO_LISTA_BASE_X_CANTIDAD) {
					buscarAccionesComDetalle(listaPrecioDetalle, cliente, AccionesCom.TI_ORIGEN_PROVEEDOR);
					buscarAccionesComDetalle(listaPrecioDetalle, cliente, AccionesCom.TI_ORIGEN_EMPRESA);
					buscarAccionesComDetalle(listaPrecioDetalle, cliente, AccionesCom.TI_ORIGEN_GLOBALES);
					// buscarAccionesComDetalle(listaPrecioDetalle, cliente, AccionesCom.TI_ORIGEN_CONJUNTAS);
				}
			}
		} catch (NoSuchElementException  e1) {
			logger.error("No existen mas elemento a recorrer. " + e1.getStackTrace() + " MENSAJEEX: " + e1.getMessage() );
			throw e1;
		}catch (Exception e) {
			logger.error("Error inesperado en clase AccionesComDetalleService-Met: validarAccionesComDetalle. " + e.getStackTrace() + " MENSAJEEX: " + e.getMessage() );
			throw e;
		}
		
	}

	private void buscarAccionesComDetalle(ListaPrecioDetalle lstPrecioDetalle, Cliente cliente, String origen) throws Exception{
		Optional<AccionesComDetalle> optAccionesComDetalle = null;
		Integer tipoAccion = AccionesCom.TIPO_ACCIONES_ARTICULO;
		Long idArticulo = lstPrecioDetalle.getArticulo().getId();
		Long idSucursal = cliente.getId().getIdSucursal();
		Long idCliente = cliente.getId().getIdCliente();
		Long idComercio = cliente.getId().getIdComercio();
		Date fechaActuald = Calendar.getInstance().getTime();
		String tiOrigen = origen;
		Long idProveedor = (origen.equals(AccionesCom.TI_ORIGEN_PROVEEDOR))
				? lstPrecioDetalle.getArticulo().getIdProveedor()
				: 0L;

		try {
			optAccionesComDetalle = accionesComDetRepo.recoveryAccionPorArticulo(idArticulo, idSucursal, idCliente,
					idComercio, fechaActuald, tiOrigen, tipoAccion, idProveedor, _cantidadPedida);
			if (optAccionesComDetalle.isPresent()) {
				calcularDescuento(lstPrecioDetalle, optAccionesComDetalle.get(), cliente, tipoAccion, origen);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error inesperado en clase AccionesComDetalleService. Método: buscarAccionesComDetalle. " + e.getStackTrace() + " MENSAJEEX: " + e.getMessage() );
			throw e;
		}

	}

	private void calcularDescuento(ListaPrecioDetalle aListaPrecioDetalle, AccionesComDetalle accionesComDetalle,
			Cliente cliente, Integer tipoAccion, String tiOrigen) throws Exception{

		Float taDto = 0f;

		// AccionesComDetalleService.logger.info("Tipo de Accion Comercial: " +
		// tipoAccion);
		if (tipoAccion != null && tipoAccion.equals(AccionesCom.TIPO_ACCIONES_ARTICULO)) {
			Sucursal sucursal = null;
			try {
				if (cliente != null) {
					sucursal = sucursalService.getById(cliente.getId().getIdSucursal());
					// AccionesComDetalleService.logger.info("Sucursal: " +
					// cliente.getId().getIdSucursal() + ". Tipo control acciones comerciales: " +
					// sucursal.getTiCtrlAccom() + ". Tipo Origen: " + tiOrigen);
				} else {
					// AccionesComDetalleService.logger.error("El cliente es nulo para la acción de
					// tipo artículo. No se aplicará la acción. Corrija el tipo de la acción
					// comercial.");
				}
			} catch (Exception e) {
				// AccionesComDetalleService.logger.error("AccionesComBo. calcularDescuento().
				// No se pudo recuperar la sucursal id: "+cliente.getId().getIdSucursal());
				e.printStackTrace();
				logger.error("No se pudo recuperar la sucursal id: "+ cliente.getId().getIdSucursal()+". Método: calcularDescuento. " + e.getStackTrace() + " MENSAJEEX: " + e.getMessage() );
				throw e;
			}
			if (sucursal != null) {
				float caMaxima;
				if (accionesComDetalle.getCaMaxima() != null && accionesComDetalle.getCaMaxima() > 0) {
					caMaxima = accionesComDetalle.getCaMaxima();
					// AccionesComDetalleService.logger.info("Cantidad maxima: " + caMaxima);
				} else {
					caMaxima = Float.MAX_VALUE;
					// AccionesComDetalleService.logger.info("Cantidad maxima nula o 0. No se
					// controlará cantidad máxima para la acción.");
				}

				if (accionesComDetalle.getCaVendida() == null) {
					accionesComDetalle.setCaVendida(0f);
				}

				if ((caMaxima - accionesComDetalle.getCaVendida() >= _cantidadPedida) || // Si hay cantidad disponible
						(sucursal.getTiCtrlAccom() != null && // o si no hay pero es tipo CA o NR aplico descuento
						 !sucursal.getTiCtrlAccom().equals(Sucursal.TI_CONTROL_SACA_DESCUENTO))) { 
																												
					if (_cantidadPedida % aListaPrecioDetalle.getArticulo().getUnidadPorBulto() == 0
							&& accionesComDetalle.getTaDtoBcerrado() != null
							&& accionesComDetalle.getTaDtoBcerrado() > 0) {
						taDto = accionesComDetalle.getTaDtoBcerrado(); // Bultos cerrados
					} else {
						taDto = accionesComDetalle.getTaDto(); // Bultos abiertos
					}

				} else {
					// No seteo descuentos
					// AccionesComDetalleService.logger.info("No se aplicarán descuentos. Cantidad
					// máxima superada para la acción y sucursal configurada para sacar descuentos
					// (SD) o no configurada.");
					// Seteo ids de accion null por si venia con un id seteado del movil
				}

				if (tiOrigen != null) {
					if (tiOrigen.equals(AccionesCom.TI_ORIGEN_GLOBALES)) {
						aListaPrecioDetalle.setTaDto(taDto);
					} else if (tiOrigen.equals(AccionesCom.TI_ORIGEN_EMPRESA)) {
						aListaPrecioDetalle.setTaDtoCliente(taDto);
					} else if (tiOrigen.equals(AccionesCom.TI_ORIGEN_CONJUNTAS)) {
						// TODO falta analizar.
					} else if (tiOrigen.equals(AccionesCom.TI_ORIGEN_PROVEEDOR)) {
						aListaPrecioDetalle.setTaDtoProveedor(taDto);
					} else { // por tipo proveedor por default
						aListaPrecioDetalle.setTaDtoProveedor(taDto);
					}
				}
			}
		} else {
			taDto = accionesComDetalle.getTaDto();
			if (tiOrigen != null) {
				if (tiOrigen.equals(AccionesCom.TI_ORIGEN_GLOBALES)) {
					aListaPrecioDetalle.setTaDto(taDto);
				} else if (tiOrigen.equals(AccionesCom.TI_ORIGEN_EMPRESA)) {
					aListaPrecioDetalle.setTaDtoCliente(taDto);
				} else if (tiOrigen.equals(AccionesCom.TI_ORIGEN_CONJUNTAS)) {
					// TODO falta analizar.
				} else if (tiOrigen.equals(AccionesCom.TI_ORIGEN_PROVEEDOR)) {
					aListaPrecioDetalle.setTaDtoProveedor(taDto);
				} else { // por tipo proveedor por default
					aListaPrecioDetalle.setTaDtoProveedor(taDto);
				}
			}
		}
	}

}
