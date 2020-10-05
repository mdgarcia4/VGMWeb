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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.vgmsistemas.vgmweb.dto.Clave;
import com.vgmsistemas.vgmweb.service.ClienteService;
import com.vgmsistemas.vgmweb.service.EnvioMail;
import com.vgmsistemas.vgmweb.service.PropertiesService;
import com.vgmsistemas.vgmweb.service.UserDetailsServiceImpl;

@Controller
public class ClaveController {
	@Autowired
	PropertiesService prepertyService;
	@Autowired
	UserDetailsServiceImpl userService;
	@Autowired
	EnvioMail mail;
	@Autowired
	ClienteService clienteService;
	static Logger logger = LoggerFactory.getLogger(ClaveController.class);
	String usuarioName = "";

	@GetMapping({ "/change-password", "/change-password.html", "/change-password.htm" })
	public String cambioClave(Model model) {
		// Obtengo el usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		// si no esta logueado mando null a la vista
		if (auth.getPrincipal().equals("anonymousUser")) {
			usuarioName = null;
		} else {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			usuarioName = userDetail.getUsername();
		}
		model.addAttribute("nameapp", prepertyService.getNameApp());
		model.addAttribute("usuarioLogin", usuarioName);
		return "change-password";
	}

	@ModelAttribute(value = "clave")
	public Clave newUsuario() {
		return new Clave();
	}

	@PostMapping({ "/change-password", "/change-password.html", "/change-password.htm" })
	public String cambiarClave(Model model, @ModelAttribute("clave") Clave clave) {
		try {
			// Obtengo el usuario
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			// si no esta logueado mando null a la vista
			if (auth.getPrincipal().equals("anonymousUser")) {
				usuarioName = null;
			} else {
				UserDetails userDetail = (UserDetails) auth.getPrincipal();
				usuarioName = userDetail.getUsername();
			}
			if (usuarioName == null) {
				throw new Exception("Usuario no esta Logueado.");
			}
			clave.setUser(usuarioName);
			if (userService.cambiarClave(clave).equals("OK")) {
				return "login";
			}
			model.addAttribute("nameapp", prepertyService.getNameApp());
			model.addAttribute("usuarioLogin", usuarioName);
			model.addAttribute("CambiarClaveErrorMensaje", "No se encontró Cliente");
			return "change-password";
		} catch (Exception e) {
			logger.error("Error inesperado en clase  ClaveController-Página: change-password. " + e.getStackTrace()
					+ " VGMMESAGGE: " + e.getMessage() + " VGMTOSTRING: " + e.toString());
			logger.error("Error inesperado en clase  ClaveController-Página: change-password. " + e);
			model.addAttribute("nameapp", prepertyService.getNameApp());
			model.addAttribute("usuarioLogin", usuarioName);
			model.addAttribute("CambiarClaveErrorMensaje", e.getMessage());
			return "change-password";
		}
	}

	@GetMapping({ "/recover-password", "/recover-password.html", "/recover-password.htm" })
	public String getClave(Model model) {
		model.addAttribute("nameapp", prepertyService.getNameApp());
		return "recover-password";
	}

	@PostMapping({ "/recover-password", "/recover-password.html", "/recover-password.htm" })
	public String recuperarClave(Model model, @ModelAttribute("clave") Clave clave) {
		try {
			if (clave != null && !clave.getMail().equals("")) {
				// genero el password
				if (userService.recuperarClave(clave).equals("OK")) {
					return "login";
				}
			}
			model.addAttribute("nameapp", prepertyService.getNameApp());
			return "recover-password";
		} catch (Exception e) {
			logger.error("Error inesperado en clase  ClaveController-Página: recover-password. " + e.getStackTrace()
					+ " VGMMESAGGE: " + e.getMessage() + " VGMTOSTRING: " + e.toString());
			logger.error("Error inesperado en clase  ClaveController-Página: recover-password. " + e);
			model.addAttribute("nameapp", prepertyService.getNameApp());
			model.addAttribute("RecuperarClaveErrorMensaje", e.getMessage());
			return "recover-password";
		}
	}

}
