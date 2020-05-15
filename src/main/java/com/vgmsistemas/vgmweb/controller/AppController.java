package com.vgmsistemas.vgmweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.vgmsistemas.vgmweb.entity.Articulo;
import com.vgmsistemas.vgmweb.service.ArticuloService;

import org.springframework.ui.Model;

@Controller
public class AppController {
	
	@Autowired
	ArticuloService articuloService;

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
		
		model.addAttribute("articulos",articuloService.getAll());
		return "categorias";
	}
}
