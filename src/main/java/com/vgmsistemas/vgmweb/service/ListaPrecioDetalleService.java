package com.vgmsistemas.vgmweb.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	static Logger logger = LoggerFactory.getLogger(ListaPrecioDetalleService.class);
	private Empresa empresa;
	private Long idDeposito;

	public Page<ListaPrecioDetalle> getListaPrecioPorCliente(Cliente cliente, Integer pagNro, Integer pagTamanio,
			String ordenadoPor, Long rubro, Long subrubro, Long proveedor, Long marca) throws Exception {
		try {
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
		} catch (Exception e) {
			logger.error("Error inesperado en clase ListaPrecioDetalleService-Met: getListaPrecioPorCliente. "
					+ e.getStackTrace() + " MENSAJEEX: " + e.getMessage() + " MENSAJEStr: " + e.toString());
			throw e;
		}
	}

	public Page<ListaPrecioDetalle> getListaPrecioPorClienteStock(Cliente cliente, Integer pagNro, Integer pagTamanio,
			String ordenadoPor, Long rubro, Long subrubro, Long proveedor, Long marca) throws Exception {
		try {
			Pageable pagina = PageRequest.of(pagNro, pagTamanio, Sort.by(ordenadoPor));
			Page<ListaPrecioDetalle> listaPrecios;
			Long sucursal = cliente.getId().getIdSucursal();
			Long lista = cliente.getListaPrecio().getId();
			empresa = empresaService.getById(1L);// hardco empresa 1

			idDeposito = empresaService.getDepositoActivo(empresa, cliente);

			listaPrecios = listaPrecioDetalleRepo.findListaBySucListaRubroSubrubroMarcaProvedorStock(sucursal, lista,
					rubro, subrubro, marca, proveedor, idDeposito, pagina);
			if (empresa.getSnAccionesCom().equals(Empresa.PERMITE)) {
				getAccionesComerciales(listaPrecios, cliente);
			}

			return controlCalculosPorArticulos(listaPrecios);
		} catch (Exception e) {
			logger.error("Error inesperado en clase ListaPrecioDetalleService-Met: getListaPrecioPorClienteStock. "
					+ e.getStackTrace() + " MENSAJEEX: " + e.getMessage() + " MENSAJEStr: " + e.toString());
			throw e;
		}
	}

	public Iterator<ListaPrecioDetalle> getListaPrecioArticulosDestacados(Cliente cliente) throws Exception {
		ArrayList<String> arrTiWebDestacados = new ArrayList<>();

		arrTiWebDestacados.add("cat1");// cat1
		arrTiWebDestacados.add("cat2");// cat2
		arrTiWebDestacados.add("cat3");// cat3
		Pageable pagina = PageRequest.of(0, 100, Sort.by("articulo.descripcion"));
		Page<ListaPrecioDetalle> listaPrecios;
		Long sucursal = cliente.getId().getIdSucursal();
		Long lista = cliente.getListaPrecio().getId();
		// buscar stockt
		empresa = empresaService.getById(1L);
		Long idDeposito = empresaService.getDepositoActivo(empresa, cliente);
		listaPrecios = listaPrecioDetalleRepo.findByTiWebDestacadosInStock(sucursal, lista, arrTiWebDestacados,
				idDeposito, pagina);
		// listaPrecios = listaPrecioDetalleRepo.findByTiWebDestacadosIn(sucursal,
		// lista, arrTiWebDestacados, pagina);
		return controlCalculosPorArticulos(listaPrecios).iterator();
	}

	private void getAccionesComerciales(Page<ListaPrecioDetalle> asListaprecio, Cliente aCliente) throws Exception {
		try {
			accionesComDetalleService.validarAccionesComDetalle(asListaprecio, aCliente);
		} catch (Exception e) {
			logger.error("Error inesperado en clase ListaPrecioDetalleService-Met: getAccionesComerciales. "
					+ e.getStackTrace() + " MENSAJEEX: " + e.getMessage() + " MsjTOStr: " + e.toString());
			throw e;
		}
	}

	private Page<ListaPrecioDetalle> controlCalculosPorArticulos(Page<ListaPrecioDetalle> listado) throws Exception {
		try {
			// para controlar la existencia de imagen
			String srcImagenControl, pathSourceImage, path;
			ApplicationHome home = new ApplicationHome(this.getClass());
			File dirApp = home.getDir();// obtengo la dir donde esta el proyecto
			pathSourceImage = propertyService.getPathSrcImagen();
			if (!pathSourceImage.equals("") && pathSourceImage != null) {
				path = pathSourceImage; // No se uso por ahora
				// path = dirApp.toString() + "\\src\\main\\resources\\static\\img\\";
			} else {
				path = dirApp.toString() + "\\src\\main\\resources\\static\\img\\";
			}
			// pathHtml = path.replaceAll("\\\\", "/"); No se uso por ahora

			String tipoBusqueda = empresaService.getById(1L).getTiBusquedaImgArticulo();// "CB";
			for (ListaPrecioDetalle list : listado) {
				// busco el nombre por el cual se buscará la imagen
				srcImagenControl = getSrcPorTipoBusqueda(list, tipoBusqueda);
				File myFile = new File(path + srcImagenControl + ".jpg");
				// Control imagen si no existe pongo imagen no disponible.

				if (!myFile.exists()) {
					// list.setSrcImagen(pathHtml + "/imagen_no_disp"); No se uso por ahora
					list.setSrcImagen("imagen_no_disp");
				} else {
					// list.setSrcImagen(pathHtml + "/" + srcImagenControl); No se uso por ahora
					list.setSrcImagen(srcImagenControl);
				}
				calcularTotales(list);
			}
			return listado;
		} catch (Exception e) {
			logger.error("Error inesperado en clase ListaPrecioDetalleService-Met: controlCalculosPorArticulos. "
					+ e.getStackTrace() + " MENSAJEEX: " + e.getMessage() + " MENSAJEStr: " + e.toString());
			throw e;
		}
	}

	private String getSrcPorTipoBusqueda(ListaPrecioDetalle lista, String tipoBusquedaEmpresa) throws Exception {
		if (tipoBusquedaEmpresa.equals(ListaPrecioDetalle.BUSQUEDA_POR_CODBARRA)) {
			return lista.getArticulo().getIdCodigoBarras();
		} else if (tipoBusquedaEmpresa.equals(ListaPrecioDetalle.BUSQUEDA_POR_ID)) {
			return lista.getArticulo().getId().toString();
		} else if (tipoBusquedaEmpresa.equals(ListaPrecioDetalle.BUSQUEDA_POR_IDEMPRESA)) {
			return lista.getArticulo().getCodigoEmpresa();
		} else {
			return lista.getId().toString();/* por defecto busca por id, sino tiene nada en el tipo de búsquda */
		}
	}

	private void calcularTotales(ListaPrecioDetalle listaPrecioDetalle) throws Exception {
		try {
			Double precioTotalConIvaYDtos = 0D;
			Float taDtoProveedor = (listaPrecioDetalle.getTaDtoProveedor() == null) ? 0f
					: listaPrecioDetalle.getTaDtoProveedor();
			Float taDtoCliente = (listaPrecioDetalle.getTaDtoCliente() == null) ? 0f
					: listaPrecioDetalle.getTaDtoCliente();
			Float taDto = (listaPrecioDetalle.getTaDto() == null) ? 0f : listaPrecioDetalle.getTaDto();
			precioTotalConIvaYDtos = listaPrecioDetalle.getPrecioConIva() * (1 + taDtoProveedor) * (1 + taDtoCliente)
					* (1 + taDto);
			listaPrecioDetalle.setPrecioConIvaYDtos(formatearDecimales(precioTotalConIvaYDtos, 2));
			listaPrecioDetalle.setPrecioConIva(formatearDecimales(listaPrecioDetalle.getPrecioConIva(), 2));
			listaPrecioDetalle.setPrecioSinIva(formatearDecimales(listaPrecioDetalle.getPrecioSinIva(), 2));
		} catch (Exception e) {
			logger.error("Error inesperado en clase ListaPrecioDetalleService-Met: calcularTotales. "
					+ e.getStackTrace() + " MENSAJEEX: " + e.getMessage());
			throw e;
		}
	}

	private Double formatearDecimales(Double numero, Integer numeroDecimales) throws Exception {
		return Math.round(numero * Math.pow(10, numeroDecimales)) / Math.pow(10, numeroDecimales);
	}

}