package com.vgmsistemas.vgmweb.controller;

import java.util.List;
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

import com.vgmsistemas.vgmweb.entity.Banner;
import com.vgmsistemas.vgmweb.entity.Cliente;
import com.vgmsistemas.vgmweb.entity.ListaPrecioDetalle;
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

	@GetMapping({ "/categorias", "/categorias.html", "/categorias.htm" })
	public String categorias(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "12") Integer size,
			@RequestParam(defaultValue = "articulo.descripcion") String order,
			@RequestParam(defaultValue = "0") Long rubro, @RequestParam(defaultValue = "0") Long subrubro,
			@RequestParam(defaultValue = "0") Long proveedor, @RequestParam(defaultValue = "0") Long marca,
			Model model) {

		int paginaRecuperar;
		int paginasTotal;
		int paginaSiguiente;
		int paginaAnterior;
		int productoDesde;
		int productoHasta;
		long productosTotal;
		try {
			// Obtengo el usuario para poder obtener el cliente relacionado con su lista de
			// precio
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			String usuario = userDetail.getUsername();

			Cliente cliente = clienteService.getClienteByUsuario(usuario);

			paginaRecuperar = page - 1;

			Page<ListaPrecioDetalle> paginaArticulos = listaPrecioDetalleService.getListaPrecioPorClienteStock(cliente,
					paginaRecuperar, size, order, rubro, subrubro, proveedor, marca);

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

			model.addAttribute("paginas", paginas);
			model.addAttribute("marcas", marcaService.getBySnWeb("S"));
			model.addAttribute("rubros", rubroService.getBySnWeb("S"));
			model.addAttribute("proveedores", proveedorService.getBySnWeb("S"));
			model.addAttribute("preciosArticulos", paginaArticulos);
			model.addAttribute("paginaAnterior", paginaAnterior);
			model.addAttribute("paginaActual", page);
			model.addAttribute("paginaSiguiente", paginaSiguiente);
			model.addAttribute("productoDesde", productoDesde);
			model.addAttribute("productoHasta", productoHasta);
			model.addAttribute("productosTotal", productosTotal);
			model.addAttribute("preciominimo", propertyService.getPrecioDesde());
			model.addAttribute("preciomaximo", propertyService.getPrecioHasta());
			model.addAttribute("nameapp", propertyService.getNameApp());
			model.addAttribute("userlogincontroller", usuario);
			List<Banner> list = bannerService.getByDePaginaAndSnActivo("categorias", "S");
			if (list != null) {
				model.addAttribute("banner", list.get(0));
			}

			return "categorias";
		} catch (Exception e) {
			logger.error("Error inesperado en clase CategoriasController-Página: categorias. " + e.getStackTrace()
					+ " VGMMESAGGE:" + e.getMessage() + " VGMTOSTRING:" + e.toString());
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

	@PostMapping({ "/categorias", "/categorias.html", "/categorias.htm" })
	public String categoriasSearch(String search, Model model, @PageableDefault(page = 0, size = 9) Pageable pageable) {
		try {
			model.addAttribute("marcas", marcaService.getBySnWeb("S"));
			model.addAttribute("rubros", rubroService.getBySnWeb("S"));
			model.addAttribute("proveedores", proveedorService.getBySnWeb("S"));
			model.addAttribute("articulos", articuloService.searchByDescripcion(search, pageable));

			return "categorias";

		} catch (Exception e) {
			logger.error("Error inesperado en clase CategoriasController-Página: categoriasSearch. " + e.getStackTrace()
					+ " VGMMESAGGE: " + e.getMessage() + " VGMTOSTRING: " + e.toString());
			return "error";
		}
	}
}
