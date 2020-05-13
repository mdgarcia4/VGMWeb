package com.vgmsistemas.vgmweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

	@GetMapping({"/login"})
	public String login() {
		return "index1";
	}
	
	@GetMapping({"/","/index"})
	public String index() {
		return "index1";
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
	public String categorias() {
		return "categorias";
	}
}
