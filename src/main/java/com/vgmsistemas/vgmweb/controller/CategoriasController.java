package com.vgmsistemas.vgmweb.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vgmsistemas.vgmweb.entity.Articulo;
import com.vgmsistemas.vgmweb.service.ArticuloService;
import com.vgmsistemas.vgmweb.service.MarcaService;
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
	
		
	@GetMapping("/categorias")
	public String categorias(@RequestParam(defaultValue = "1") Integer pagNro,
            @RequestParam(defaultValue = "12") Integer pagTamanio,
            @RequestParam(defaultValue = "descripcion") String ordenadoPor,
            Model model) {
		
		int paginaRecuperar;
		int paginasTotal;
		int paginaSiguiente;
		int paginaAnterior;
		int productoDesde;
		int productoHasta;
		long productosTotal;
		
		paginaRecuperar = pagNro - 1; 
		
		Page<Articulo> paginaArticulos = articuloService.getAll(paginaRecuperar,pagTamanio,ordenadoPor);
		
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
		model.addAttribute("articulos",paginaArticulos);
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
	
	/*@GetMapping("/subrubro")
	public String categorias(@RequestParam("rubro") Long rubro, @RequestParam("subrubro") Long subrubro  , Model model, @PageableDefault(page = 0, size = 9) Pageable pageable) {
		model.addAttribute("marcas", marcaService.getBySnWeb("S"));
		model.addAttribute("rubros", rubroService.getBySnWeb("S"));
		model.addAttribute("proveedores", proveedorService.getBySnWeb("S"));
		model.addAttribute("articulos",articuloService.getByRubroAndSubrubro(rubro, subrubro,pageable));
		
		return "categorias";
	}
	
	@GetMapping("/marca")
	public String categorias(@RequestParam("marca") Long marca,  Model model, @PageableDefault(page = 0, size = 9) Pageable pageable) {
		model.addAttribute("marcas", marcaService.getBySnWeb("S"));
		model.addAttribute("rubros", rubroService.getBySnWeb("S"));
		model.addAttribute("proveedores", proveedorService.getBySnWeb("S"));
		model.addAttribute("articulos",articuloService.getByMarca(marca, pageable));
		
		return "categorias";
	}
	
	@GetMapping("/proveedor")
	public String categoriasProv(@RequestParam("proveedor") Long proveedor,  Model model, @PageableDefault(page = 0, size = 9) Pageable pageable) {
		model.addAttribute("marcas", marcaService.getBySnWeb("S"));
		model.addAttribute("rubros", rubroService.getBySnWeb("S"));
		model.addAttribute("proveedores", proveedorService.getBySnWeb("S"));
		model.addAttribute("articulos",articuloService.getByProveedor(proveedor, pageable));
		
		return "categorias";
	}*/
	
	
}