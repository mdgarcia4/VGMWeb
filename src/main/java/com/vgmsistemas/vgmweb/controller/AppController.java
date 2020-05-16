package com.vgmsistemas.vgmweb.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.vgmsistemas.vgmweb.entity.Articulo;
import com.vgmsistemas.vgmweb.entity.Rubro;
import com.vgmsistemas.vgmweb.entity.Subrubro;
import com.vgmsistemas.vgmweb.service.ArticuloService;
import com.vgmsistemas.vgmweb.service.RubroService;

import org.springframework.ui.Model;

@Controller
public class AppController {
	
	@Autowired
	ArticuloService articuloService;
	
	@Autowired
	RubroService rubroService;

	@GetMapping({"/login"})
	public String login() {
		return "login";
	}
	
	@GetMapping({"/","/index"})
	public String index() {
		return "index";
	}
	
	@GetMapping("/menu")
	public String menu() {
		return "menu";
	}
	
	@GetMapping("/user")
	public String user() {
		return "user";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}
	
	@GetMapping("/categorias")
	public String categorias(Model model, @PageableDefault(page = 0, size = 9) Pageable pageable) {
		List<Rubro> rubros;
		rubros = rubroService.getAll();
				
		for (Rubro rubro : rubros) {
			Set<Subrubro> subrubros;
			subrubros = rubro.getSubrubros();
		}
		model.addAttribute("rubros", rubros);
		model.addAttribute("articulos",articuloService.getAll(pageable));
		
		return "categorias";
	}
}
