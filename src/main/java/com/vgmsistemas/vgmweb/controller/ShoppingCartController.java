package com.vgmsistemas.vgmweb.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vgmsistemas.vgmweb.dto.DetailViewWsDto;
import com.vgmsistemas.vgmweb.entity.Clientes;
import com.vgmsistemas.vgmweb.service.ClienteService;
import com.vgmsistemas.vgmweb.service.PropertiesService;
import com.vgmsistemas.vgmweb.service.VentaService;

@Controller
@RequestMapping({ "/shopping-cart", "/shopping-cart.html", "/shopping-cart.htm" })
public class ShoppingCartController {
	@Autowired
	VentaService vtaService;
	@Autowired
	PropertiesService prepertyService;
	@Autowired
	ClienteService clienteService;
	static Logger logger = LoggerFactory.getLogger(ShoppingCartController.class);

	@GetMapping
	public String getShoppingCar(Model model) {
		try {
			// Obtengo el usuario
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			String usuario = userDetail.getUsername();
			Clientes clientesComerciosPorUsuario = clienteService.getAllClientesByUsuario(usuario);
						
			model.addAttribute("comercios",clientesComerciosPorUsuario.getClientes());
			model.addAttribute("token", "");
			model.addAttribute("nameapp", prepertyService.getNameApp());
			return "shopping-cart";
		} catch (Exception e) {

			model.addAttribute("token", "");
			model.addAttribute("nameapp", prepertyService.getNameApp());
			return "shopping-cart";
		}
	}

	@PostMapping
	public @ResponseBody String checkin(@RequestBody DetailViewWsDto detallePedido, HttpServletRequest request) {
		if (detallePedido == null) {
			return "el servidor no pudo interpretar la solicitud dada una sintaxis inválida.";
		} else {
			try {
				return vtaService.generarVenta(detallePedido);
			} catch (Exception e) {
				logger.error("Error inesperado en clase ContactoController-Página: contacto. " + e.getStackTrace());
				return "error";
			}
		}
	}
}
