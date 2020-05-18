package com.vgmsistemas.vgmweb.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String categorias(@RequestParam(defaultValue = "0") Integer pagNro,
            @RequestParam(defaultValue = "12") Integer pagTamanio,
            @RequestParam(defaultValue = "descripcion") String ordenadoPor,
            Model model) {
		model.addAttribute("marcas", marcaService.getBySnWeb("S"));
		model.addAttribute("rubros", rubroService.getBySnWeb("S"));
		model.addAttribute("proveedores", proveedorService.getBySnWeb("S"));
		model.addAttribute("articulos",articuloService.getAll(pagNro,pagTamanio,ordenadoPor));
		
		return "categorias";
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
