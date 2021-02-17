package com.vgmsistemas.vgmweb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vgmsistemas.vgmweb.dto.FiltroPagina;
import com.vgmsistemas.vgmweb.entity.Banner;
import com.vgmsistemas.vgmweb.entity.Cliente;
import com.vgmsistemas.vgmweb.entity.ListaPrecioDetalle;
import com.vgmsistemas.vgmweb.entity.Marca;
import com.vgmsistemas.vgmweb.entity.Proveedor;
import com.vgmsistemas.vgmweb.entity.Rubro;
import com.vgmsistemas.vgmweb.entity.Subrubro;
import com.vgmsistemas.vgmweb.service.ArticuloService;
import com.vgmsistemas.vgmweb.service.BannerService;
import com.vgmsistemas.vgmweb.service.ClienteService;
import com.vgmsistemas.vgmweb.service.ListaPrecioDetalleService;
import com.vgmsistemas.vgmweb.service.MarcaService;
import com.vgmsistemas.vgmweb.service.PropertiesService;
import com.vgmsistemas.vgmweb.service.ProveedorService;
import com.vgmsistemas.vgmweb.service.RubroService;

import org.springframework.ui.Model;

@Controller
public class CategoriasController {

	@Autowired
	ArticuloService articuloService;
	@Autowired
	RubroService rubroService;
	@Autowired
	MarcaService marcaService;
	@Autowired
	ProveedorService proveedorService;
	@Autowired
	ClienteService clienteService;
	@Autowired
	ListaPrecioDetalleService listaPrecioDetalleService;
	@Autowired
	BannerService bannerService;
	@Autowired
	PropertiesService propertyService;
	static Logger logger = LoggerFactory.getLogger(CategoriasController.class);

	private List<Marca> listMarca;
	private List<Rubro> listRubro;
	private List<Proveedor> listProveedor;
	private int prDesde;
	private int prHasta;

	private Long rubroElegido;
	private Long subrubroElegido;
	private Long proveedorElegido;
	private Long marcaElegido;
	private Long minElegido;
	private Long maxElegido;
	private String usuarioElegido;
	private Cliente cliente;
	private long productosTotal;
	private float taImpProvicial;
	private float taImpMunicipal;
	private float dtoCondicionVenta;
	private String letraSegunTipoContribuyente;

	@GetMapping({ "/categorias", "/categorias.html", "/categorias.htm" })
	public String categorias(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "12") Integer size,
			@RequestParam(defaultValue = "articulo.descripcion") String order,
			@RequestParam(defaultValue = "0") Long rubro, @RequestParam(defaultValue = "0") Long subrubro,
			@RequestParam(defaultValue = "0") Long proveedor, @RequestParam(defaultValue = "0") Long marca,
			@RequestParam(defaultValue = "0") Long min, @RequestParam(defaultValue = "0") Long max, 
			@RequestParam(defaultValue = "N") String stk, Model model) {

		int paginaRecuperar;
		try {
			// Obtengo el usuario para poder obtener el cliente relacionado con su lista de
			// precio
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			String usuario = userDetail.getUsername();

			cliente = clienteService.getClienteByUsuario(usuario);

			paginaRecuperar = page - 1;

			Page<ListaPrecioDetalle> paginaArticulos = listaPrecioDetalleService.getListaPrecioPorClienteStock(cliente,
					paginaRecuperar, size, order, rubro, subrubro, proveedor, marca, min, max, stk);

			/* resguardo datos elegidos */
			rubroElegido = rubro;
			subrubroElegido = subrubro;
			proveedorElegido = proveedor;
			marcaElegido = marca;
			minElegido = min;
			maxElegido = max;
			usuarioElegido = usuario;
			taImpProvicial = cliente.getCondicionRenta().getTasaDgr().equals(null)? 0 : cliente.getCondicionRenta().getTasaDgr();
			taImpMunicipal = (cliente.getCondicionDirsc() == null) ? 0 : cliente.getCondicionDirsc().getTasaImpuesto();
			letraSegunTipoContribuyente= cliente.getCategoriaFiscal().getId().equals("IN") ? "A" : "B";
			dtoCondicionVenta = cliente.getCondicionVenta() == null ? 0 : cliente.getCondicionVenta().getTasaDescuento();
			/* Cargo la vista */
			cargarModel(model, rubroElegido, subrubroElegido, proveedorElegido, marcaElegido, minElegido, maxElegido,
					usuarioElegido,paginaArticulos, Integer.valueOf(page),Integer.valueOf(size));
			return "categorias";
		} catch (Exception e) {
			logger.error("Error inesperado en clase CategoriasController-Página: categorias. " + e.getStackTrace()
					+ " VGMMESAGGE:" + e.getMessage() + " VGMTOSTRING:" + e.toString());
			logger.error("Error inesperado en clase CategoriasController-Página: categorias. " + e);
			return "error";
		}

	}

	public String categoriasSearch(String search, Model model,
			@PageableDefault(page = 0, size = 12) Pageable pageable) {
		try {

			Page<ListaPrecioDetalle> paginaArticulos = listaPrecioDetalleService.findDescripcionArticulos(cliente,
					search, pageable);
			model.addAttribute("preciosArticulos", paginaArticulos);

			cargarModel(model, rubroElegido, subrubroElegido, proveedorElegido, marcaElegido, minElegido, maxElegido,
					usuarioElegido, paginaArticulos, Integer.valueOf(pageable.getPageNumber()),
					Integer.valueOf(pageable.getPageSize()));

			return "categorias";

		} catch (Exception e) {
			logger.error("Error inesperado en clase CategoriasController-Página: categoriasSearch. " + e.getStackTrace()
					+ " VGMMESAGGE: " + e.getMessage() + " VGMTOSTRING: " + e.toString());
			logger.error("Error inesperado en clase CategoriasController-Página: categoriasSearch. " + e);
			return "error";
		}
	}

	private List<Integer> getVectorPaginasLista(Integer pagVisual, Integer totalPaginas) {
		int tamaniopagina, pagInicial, pagFinal, cociente, residuo;
		tamaniopagina = 7;
		pagInicial = 1;
		if ((pagVisual / tamaniopagina) >= 1) {
			cociente = pagVisual / tamaniopagina;
			residuo = pagVisual % tamaniopagina;
			pagInicial = (residuo == 0) ? (cociente * tamaniopagina) : (cociente * tamaniopagina + 1);
		}
		pagFinal = ((pagInicial + (tamaniopagina - 1)) > totalPaginas) ? totalPaginas
				: (pagInicial + (tamaniopagina - 1));
		List<Integer> paginas = IntStream.rangeClosed(pagInicial, pagFinal).boxed().collect(Collectors.toList());

		return paginas;
	}

	private List<FiltroPagina> getFiltros(Long lRubro, Long lSubrubro, Long lProveedor, Long lMarca, Long lMin,
			Long lMax) {
		List<FiltroPagina> listFiltros = new ArrayList<FiltroPagina>();
		// trato rubro y subrubro
		if (lRubro > 0 && lSubrubro > 0 && listRubro != null) {
			// busco rubro
			Optional<Rubro> r = listRubro.stream().filter(item -> item.getId().getIdRubro() == lRubro).findFirst();
			// si esta el rubro busco subrubro
			if (r.isPresent()) {
				FiltroPagina filtroRubro = new FiltroPagina("rubro", r.get().getDescripcion());
				listFiltros.add(filtroRubro);
				Optional<Subrubro> sr = r.get().getSubrubros().stream()
						.filter(item -> item.getId().getIdSubrubro() == lSubrubro).findFirst();
				if (sr.isPresent()) {
					FiltroPagina filtroSubRubro = new FiltroPagina("subrubro", sr.get().getDescripcion());
					listFiltros.add(filtroSubRubro);
				}
			}
		}
		// trato proveedor
		if (lProveedor > 0 && listProveedor != null) {
			// busco proveedor
			Optional<Proveedor> p = listProveedor.stream().filter(item -> item.getIdProveedor().equals(lProveedor))
					.findFirst();
			// si esta el proveedor lo agrego
			if (p.isPresent()) {
				FiltroPagina filtroProv = new FiltroPagina("proveedor", p.get().getDeProveedor());
				listFiltros.add(filtroProv);
			}
		}
		// trato marca
		if (lMarca > 0 && listMarca != null) {
			// busco marca
			Optional<Marca> m = listMarca.stream().filter(item -> item.getId() == lMarca).findFirst();
			// si esta la marca lo agrego
			if (m.isPresent()) {
				FiltroPagina filtroMarca = new FiltroPagina("marca", m.get().getDescripcion());
				listFiltros.add(filtroMarca);
			}
		}
		// trato precios
		if (lMin > 0 && lMax > 0 && (lMin != prDesde || lMax != prHasta)) {
			FiltroPagina filtroMmin = new FiltroPagina("prminimo", lMin.toString());
			listFiltros.add(filtroMmin);
			FiltroPagina filtroMax = new FiltroPagina("prmaximo", lMax.toString());
			listFiltros.add(filtroMax);
		}
		return (listFiltros.size() > 0) ? listFiltros : null;
	}

	@PostMapping({ "/categorias", "/categorias.html", "/categorias.htm" })

	private void cargarModel(Model model, Long rubro, Long subrubro, Long proveedor, Long marca, Long min, Long max,
			String usuario, Page<ListaPrecioDetalle> paginaArticulos, Integer page, Integer size) throws Exception {

		int paginaRecuperar;
		int paginasTotal;
		int paginaSiguiente;
		int paginaAnterior;
		int productoDesde;
		int productoHasta;

		paginaRecuperar = page - 1;
		paginasTotal = paginaArticulos.getTotalPages();
		productosTotal = paginaArticulos.getTotalElements();

		List<Integer> paginas = getVectorPaginasLista(page, paginasTotal);

		if (page <= 1) {
			paginaAnterior = 1;
		} else {
			paginaAnterior = page - 1;
		}

		if (page >= paginasTotal) {
			paginaSiguiente = page;
		} else {
			paginaSiguiente = page + 1;
		}

		productoDesde = paginaRecuperar * size + 1;
		productoHasta = paginaRecuperar * size + size;

		listMarca = marcaService.getBySnWeb("S");
		listRubro = rubroService.getBySnWeb("S");
		listProveedor = proveedorService.getBySnWeb("S");
		prDesde = propertyService.getPrecioDesde();
		prHasta = propertyService.getPrecioHasta();

		/* determino los filtros hechos para mandar a la pagina */
		model.addAttribute("filtros", getFiltros(rubro, subrubro, proveedor, marca, min, max));
		model.addAttribute("marcas", listMarca);
		model.addAttribute("rubros", listRubro);
		model.addAttribute("proveedores", listProveedor);
		model.addAttribute("preciominimo", prDesde);
		model.addAttribute("preciomaximo", prHasta);
		model.addAttribute("nameapp", propertyService.getNameApp());
		model.addAttribute("userlogincontroller", usuario);
		model.addAttribute("paginas", paginas);
		model.addAttribute("preciosArticulos", paginaArticulos);
		model.addAttribute("paginaAnterior", paginaAnterior);
		model.addAttribute("paginaActual", page);
		model.addAttribute("paginaSiguiente", paginaSiguiente);
		model.addAttribute("productoDesde", productoDesde);
		model.addAttribute("productoHasta", productoHasta);
		model.addAttribute("productosTotal", productosTotal);
		model.addAttribute("clienteUsuario", cliente);
		model.addAttribute("taImpProvincial", taImpProvicial);
		model.addAttribute("taImpMunicipal", taImpMunicipal);
		model.addAttribute("dtoCondicionVenta", dtoCondicionVenta);
		model.addAttribute("letraComprobante",letraSegunTipoContribuyente);
		model.addAttribute("nombre_otra_pagina", propertyService.getNombreOtraPagina());
		model.addAttribute("link_otra_pagina", propertyService.getLinkOtraPagina());
		List<Banner> list = bannerService.getByDePaginaAndSnActivo("categorias", "S");
		if (list != null) {
			model.addAttribute("banner", list.get(0));
		}

	}
}
