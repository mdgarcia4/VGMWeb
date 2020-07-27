package com.vgmsistemas.vgmweb.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vgmsistemas.vgmweb.dto.DetailViewWsDto;
import com.vgmsistemas.vgmweb.service.PropertiesService;
import com.vgmsistemas.vgmweb.service.VentaService;

@Controller
@RequestMapping({"/shopping-cart","/shopping-cart.html","/shopping-cart.htm"})
public class ShoppingCartController {
	@Autowired
	VentaService vtaService;

	@Autowired
	PropertiesService prepertyService;	
	@GetMapping
	public String getShoppingCar(Model model) {		
		model.addAttribute("token","nada");
		model.addAttribute("nameapp",prepertyService.getNameApp());
		return "shopping-cart";
	}
	
	@PostMapping
	public @ResponseBody String checkin(@RequestBody DetailViewWsDto detallePedido, HttpServletRequest request) {
		if (detallePedido == null) {
			return "el servidor no pudo interpretar la solicitud dada una sintaxis inválida.";
		}else {
			return vtaService.generarVenta(detallePedido);
		}
	}
}

