package com.vgmsistemas.vgmweb.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.vgmsistemas.vgmweb.entity.Cliente;
import com.vgmsistemas.vgmweb.entity.Empresa;
import com.vgmsistemas.vgmweb.entity.ListaPrecioDetalle;
import com.vgmsistemas.vgmweb.repository.ListaPrecioDetalleRepo;

@Service
public class ListaPrecioDetalleService {

	@Autowired
	ListaPrecioDetalleRepo listaPrecioDetalleRepo;
	@Autowired
	AccionesComDetalleService accionesComDetalleService;
	@Autowired
	EmpresaService empresaService;
	@Autowired
	PropertiesService propertyService;

	private Empresa empresa;

	public Page<ListaPrecioDetalle> getListaBySucursalAndLista(Long sucursal, Long lista, Integer pagNro,
			Integer pagTamanio, String ordenadoPor, Long rubro, Long subrubro, Long proveedor, Long marca) {

		Pageable pagina = PageRequest.of(pagNro, pagTamanio, Sort.by(ordenadoPor));

		Page<ListaPrecioDetalle> listaPrecios = listaPrecioDetalleRepo.findListaBySucursalAndLista(sucursal, lista,
				pagina);
		return controlCalculosPorArticulos(listaPrecios);
	}

	public Page<ListaPrecioDetalle> getListaPrecio(Long sucursal, Long lista, Integer pagNro, Integer pagTamanio,
			String ordenadoPor, Long rubro, Long subrubro, Long proveedor, Long marca) {

		Pageable pagina = PageRequest.of(pagNro, pagTamanio, Sort.by(ordenadoPor));
		Page<ListaPrecioDetalle> listaPrecios;

		if (rubro != 0 && subrubro != 0) {
			listaPrecios = listaPrecioDetalleRepo.findListaBySucursalAndListaAndSubrubro(sucursal, lista, rubro,
					subrubro, pagina);
		} else if (proveedor != 0) {
			listaPrecios = listaPrecioDetalleRepo.findListaBySucursalAndListaAndProveedor(sucursal, lista, proveedor,
					pagina);
		} else if (marca != 0) {
			listaPrecios = listaPrecioDetalleRepo.findListaBySucursalAndListaAndMarca(sucursal, lista, marca, pagina);
		} else {
			listaPrecios = listaPrecioDetalleRepo.findListaBySucursalAndLista(sucursal, lista, pagina);
		}

		return controlCalculosPorArticulos(listaPrecios);
	}

	public Page<ListaPrecioDetalle> getListaPrecioPorCliente(Cliente cliente, Integer pagNro, Integer pagTamanio,
			String ordenadoPor, Long rubro, Long subrubro, Long proveedor, Long marca) {

		Pageable pagina = PageRequest.of(pagNro, pagTamanio, Sort.by(ordenadoPor));
		Page<ListaPrecioDetalle> listaPrecios;
		Long sucursal = cliente.getId().getIdSucursal();
		Long lista = cliente.getListaPrecio().getId();
		empresa = empresaService.getById(1L);// hardco empresa 1

		listaPrecios = listaPrecioDetalleRepo.findListaBySucListaRubroSubrubroMarcaProvedor(sucursal, lista, rubro,
				subrubro, marca, proveedor, pagina);
		if (empresa.getSnAccionesCom().equals(Empresa.PERMITE)) {
			getAccionesComerciales(listaPrecios, cliente);
		}

		return controlCalculosPorArticulos(listaPrecios);
	}

	private void getAccionesComerciales(Page<ListaPrecioDetalle> asListaprecio, Cliente aCliente) {
		try {
			accionesComDetalleService.validarAccionesComDetalle(asListaprecio, aCliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Page<ListaPrecioDetalle> controlCalculosPorArticulos(Page<ListaPrecioDetalle> listado) {
		// para controlar la existencia de imagen
		String srcImagenControl, pathSourceImage, path, pathHtml;
		ApplicationHome home = new ApplicationHome(this.getClass());
		File dirApp = home.getDir();// obtengo la dir donde esta el proyecto
		pathSourceImage = propertyService.getPathSrcImagen();
		if (!pathSourceImage.equals("") && pathSourceImage != null) {
			//path = pathSourceImage;  No se uso por ahora
			path = dirApp.toString() + "\\src\\main\\resources\\static\\img\\";
		} else {
			path = dirApp.toString() + "\\src\\main\\resources\\static\\img\\";
		}
		//pathHtml = path.replaceAll("\\\\", "/");  No se uso por ahora
		// TODO buscar en empresa por que nro buscar la imagen
		// ej:empresa.getTipoBusquedaArticulo();
		String tipoBusqueda = "CB";
		for (ListaPrecioDetalle list : listado) {
			// busco el nombre por el cual se buscará la imagen
			srcImagenControl = getSrcPorTipoBusqueda(list, tipoBusqueda);
			File myFile = new File(path + srcImagenControl + ".jpg");
			// Control imagen si no existe pongo imagen no disponible.

			if (!myFile.exists()) {
				//list.setSrcImagen(pathHtml + "/imagen_no_disp"); No se uso por ahora
				list.setSrcImagen("imagen_no_disp");
			} else {
				//list.setSrcImagen(pathHtml + "/" + srcImagenControl); No se uso por ahora
				list.setSrcImagen(srcImagenControl);
			}
			calcularTotales(list);
		}
		return listado;
	}

	private String getSrcPorTipoBusqueda(ListaPrecioDetalle lista, String tipoBusquedaEmpresa) {
		if (tipoBusquedaEmpresa.equals(ListaPrecioDetalle.BUSQUEDA_POR_CODBARRA)) {
			return lista.getArticulo().getIdCodigoBarras();
		} else if (tipoBusquedaEmpresa.equals(ListaPrecioDetalle.BUSQUEDA_POR_ID)) {
			return lista.getArticulo().getId().toString();
		} else if (tipoBusquedaEmpresa.equals(ListaPrecioDetalle.BUSQUEDA_POR_IDEMPRESA)) {
			return lista.getArticulo().getCodigoEmpresa();
		} else {
			return lista.getId().toString();
		}
	}

	private void calcularTotales(ListaPrecioDetalle listaPrecioDetalle) {
		Double precioTotalConIvaYDtos = 0D;
		Float taDtoProveedor = (listaPrecioDetalle.getTaDtoProveedor() == null) ? 0f
				: listaPrecioDetalle.getTaDtoProveedor();
		Float taDtoCliente = (listaPrecioDetalle.getTaDtoCliente() == null) ? 0f : listaPrecioDetalle.getTaDtoCliente();
		Float taDto = (listaPrecioDetalle.getTaDto() == null) ? 0f : listaPrecioDetalle.getTaDto();
		precioTotalConIvaYDtos = listaPrecioDetalle.getPrecioConIva() * (1 + taDtoProveedor) * (1 + taDtoCliente)
				* (1 + taDto);
		listaPrecioDetalle.setPrecioConIvaYDtos(formatearDecimales(precioTotalConIvaYDtos, 2));
		listaPrecioDetalle.setPrecioConIva(formatearDecimales(listaPrecioDetalle.getPrecioConIva(), 2));
		listaPrecioDetalle.setPrecioSinIva(formatearDecimales(listaPrecioDetalle.getPrecioSinIva(), 2));
	}

	private Double formatearDecimales(Double numero, Integer numeroDecimales) {
		return Math.round(numero * Math.pow(10, numeroDecimales)) / Math.pow(10, numeroDecimales);
	}

}