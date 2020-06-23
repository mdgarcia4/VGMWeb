package com.vgmsistemas.vgmweb.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vgmsistemas.vgmweb.entity.Articulo;
import com.vgmsistemas.vgmweb.entity.Cliente;
import com.vgmsistemas.vgmweb.entity.ListaPrecioDetalle;
import com.vgmsistemas.vgmweb.service.ArticuloService;
import com.vgmsistemas.vgmweb.service.ClienteService;
import com.vgmsistemas.vgmweb.service.ListaPrecioDetalleService;
import com.vgmsistemas.vgmweb.service.MarcaService;
import com.vgmsistemas.vgmweb.service.ProveedorService;
import com.vgmsistemas.vgmweb.service.RubroService;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/categorias")
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
	
		
	//@GetMapping ("/categorias")
	@GetMapping
	public String categorias(@RequestParam(defaultValue = "1") Integer pagNro,
            @RequestParam(defaultValue = "12") Integer pagTamanio,
            @RequestParam(defaultValue = "articulo.descripcion") String ordenadoPor,
            @RequestParam(defaultValue = "0") Long rubro,
            @RequestParam(defaultValue = "0") Long subrubro,
            @RequestParam(defaultValue = "0") Long proveedor,
            @RequestParam(defaultValue = "0") Long marca,
            Model model) {
		
		int paginaRecuperar;
		int paginasTotal;
		int paginaSiguiente;
		int paginaAnterior;
		int productoDesde;
		int productoHasta;
		long productosTotal;
		
		
		// Obtengo el usuario para poder obtener el cliente relacionado con su lista de precio
		Authentication auth = SecurityContextHolder
				.getContext()
				.getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		String usuario = userDetail.getUsername();
		
		Cliente cliente = clienteService.getClienteByUsuario(usuario);
		
		paginaRecuperar = pagNro - 1; 
		
		Page<ListaPrecioDetalle> paginaArticulos = listaPrecioDetalleService.getListaPrecio(cliente.getId().getIdSucursal(),cliente.getListaPrecio().getId(),paginaRecuperar,pagTamanio,ordenadoPor,rubro,subrubro,proveedor,marca);
		
		
		paginasTotal = paginaArticulos.getTotalPages();
		productosTotal = paginaArticulos.getTotalElements();
		
		List<Integer> paginas = getVectorPaginas(pagNro,paginasTotal);
		
		if (pagNro <= 1) {
			paginaAnterior = 1;
		}
		else {
			paginaAnterior = pagNro - 1;
		}
		
		if (pagNro >= paginasTotal) {
			paginaSiguiente = pagNro ;
		}
		else {
			paginaSiguiente = pagNro + 1;
		}
		
		productoDesde = paginaRecuperar * pagTamanio + 1;
		productoHasta = paginaRecuperar * pagTamanio + pagTamanio;
				
		model.addAttribute("paginas",paginas);
		model.addAttribute("marcas", marcaService.getBySnWeb("S"));
		model.addAttribute("rubros", rubroService.getBySnWeb("S"));
		model.addAttribute("proveedores", proveedorService.getBySnWeb("S"));
		model.addAttribute("preciosArticulos",paginaArticulos);
		model.addAttribute("paginaAnterior", paginaAnterior);
		model.addAttribute("paginaActual",pagNro);
		model.addAttribute("paginaSiguiente",paginaSiguiente);
		model.addAttribute("productoDesde", productoDesde);
		model.addAttribute("productoHasta", productoHasta);
		model.addAttribute("productosTotal", productosTotal);
		
		
		
		
		return "categorias";
	}
	
	private List<Integer> getVectorPaginas(Integer pagNro, Integer paginasTotal) {
		int vectorPaginaInial;
		int vectorPaginaFinal;
	 	vectorPaginaInial = pagNro / 9;
		vectorPaginaInial = vectorPaginaInial * 9 + 1;
		vectorPaginaFinal = vectorPaginaInial + 9;
		if (vectorPaginaFinal > paginasTotal) {
			vectorPaginaFinal = paginasTotal;
		}
		
		List<Integer> paginas = IntStream.rangeClosed(vectorPaginaInial, vectorPaginaFinal).boxed().collect(Collectors.toList());
			
		return paginas;
	}
	
	
	//@GetMapping("/categorias/search" , method = RequestMethod.POST)
		//@PostMapping(value = "/categorias",method = RequestMethod.POST)
		@PostMapping
		public String categoriasSearch(String search,  Model model, @PageableDefault(page = 0, size = 9) Pageable pageable) {
			model.addAttribute("marcas", marcaService.getBySnWeb("S"));
			model.addAttribute("rubros", rubroService.getBySnWeb("S"));
			model.addAttribute("proveedores", proveedorService.getBySnWeb("S"));
			model.addAttribute("articulos",articuloService.searchByDescripcion(search, pageable));
			
			return "categorias";
		}
}
