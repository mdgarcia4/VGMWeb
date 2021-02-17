package com.vgmsistemas.vgmweb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.vgmsistemas.vgmweb.entity.Cliente;
import com.vgmsistemas.vgmweb.service.ClienteService;
import com.vgmsistemas.vgmweb.service.EnvioMail;
import com.vgmsistemas.vgmweb.service.PropertiesService;

@Controller
public class MiCuentaController {
	@Autowired
	PropertiesService propertyService;
	@Autowired
	ClienteService clienteService;
	@Autowired
	EnvioMail mail;
	Cliente cliente;
	static Logger logger = LoggerFactory.getLogger(MiCuentaController.class);

	@GetMapping({ "/myaccount", "/myaccount.html", "/myaccount.htm" })
	public String miCuenta(Model model) {
		try {
			// Obtengo el usuario
			String usuarioName = "";
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			// si no esta logueado asigno null
			if (auth.getPrincipal().equals("anonymousUser")) {
				cliente = null;
			} else {
				UserDetails userDetail = (UserDetails) auth.getPrincipal();
				usuarioName = userDetail.getUsername();
				cliente = clienteService.getClienteByUsuario(usuarioName);
			}
			model.addAttribute("nameapp", propertyService.getNameApp());
			model.addAttribute("nombre_otra_pagina", propertyService.getNombreOtraPagina());
			model.addAttribute("link_otra_pagina", propertyService.getLinkOtraPagina());
			model.addAttribute("cliente", cliente);
			model.addAttribute("userLogin", usuarioName);
			return "myaccount";
		} catch (Exception e) {
			logger.error("Error inesperado en clase  MiCuentaController-Página: miCuenta. " + e.getStackTrace()
			+ " VGMMESAGGE: " + e.getMessage() + " VGMTOSTRING: " + e.toString());
			logger.error("Error inesperado en clase  MiCuentaController-Página: miCuenta. " + e);
			model.addAttribute("nameapp", propertyService.getNameApp());
			return "myaccount";
		}
	}
}
