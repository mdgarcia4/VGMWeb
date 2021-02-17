package com.vgmsistemas.vgmweb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vgmsistemas.vgmweb.dto.DetailViewWsDto;
import com.vgmsistemas.vgmweb.entity.Banner;
import com.vgmsistemas.vgmweb.entity.Clientes;
import com.vgmsistemas.vgmweb.service.BannerService;
import com.vgmsistemas.vgmweb.service.ClienteService;
import com.vgmsistemas.vgmweb.service.PropertiesService;
import com.vgmsistemas.vgmweb.service.VentaService;

@Controller
public class ShoppingCartController {
	@Autowired
	VentaService vtaService;
	@Autowired
	PropertiesService propertyService;
	@Autowired
	ClienteService clienteService;
	@Autowired
	BannerService bannerService;
	static Logger logger = LoggerFactory.getLogger(ShoppingCartController.class);

	@GetMapping({ "/shopping-cart", "/shopping-cart.html", "/shopping-cart.htm" })
	public String getShoppingCar(Model model) {
		try {
			// Obtengo el usuario
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			String usuario = userDetail.getUsername();
			Clientes clientesComerciosPorUsuario = clienteService.getAllClientesByUsuario(usuario);

			model.addAttribute("comercios", clientesComerciosPorUsuario.getClientes());
			model.addAttribute("token", "");
			model.addAttribute("nameapp", propertyService.getNameApp());
			model.addAttribute("nombre_otra_pagina", propertyService.getNombreOtraPagina());
			model.addAttribute("link_otra_pagina", propertyService.getLinkOtraPagina());
			List<Banner> list = bannerService.getByDePaginaAndSnActivo("shopping-cart", "S");
			if (list != null) {
				model.addAttribute("banner", list.get(0));
			}
			return "shopping-cart";
		} catch (Exception e) {
			logger.error("Error inesperado en clase ShoppingCartController-Página: shopping-cart. " + e.getStackTrace()
					+ " VGMMESAGGE: " + e.getMessage() + " VGMTOSTRING: " + e.toString());
			logger.error("Error inesperado en clase ShoppingCartController-Página: shopping-cart. " + e);
			model.addAttribute("token", "");
			model.addAttribute("nameapp", propertyService.getNameApp());
			return "shopping-cart";
		}
	}

	@PostMapping({ "/shopping-cart", "/shopping-cart.html", "/shopping-cart.htm" })
	public @ResponseBody String checkin(@RequestBody DetailViewWsDto detallePedido, HttpServletRequest request) {
		if (detallePedido == null) {
			return "el servidor no pudo interpretar la solicitud dada una sintaxis inválida.";
		} else {
			try {
				return vtaService.generarVenta(detallePedido);
			} catch (Exception e) {
				logger.error("Error inesperado en clase ShoppingCartController-Página: shopping-cart. "
						+ e.getStackTrace() + " VGMMESAGGE: " + e.getMessage() + " VGMTOSTRING: " + e.toString());
				logger.error("Error inesperado en clase ShoppingCartController-Página: shopping-cart. "
						+ e);
				return "error";
			}
		}
	}
}
