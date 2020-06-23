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
import com.vgmsistemas.vgmweb.service.VentaService;

@Controller
@RequestMapping({"/shopping-cart","/shopping-cart.html","/shopping-cart.htm"})
public class ShoppingCartController {
	@Autowired
	VentaService vtaService;
		
	@GetMapping
	public String getShoppingCar(Model model) {		
		model.addAttribute("token","nada");
		return "shopping-cart";
	}
	
	@PostMapping
	public @ResponseBody int checkin(@RequestBody DetailViewWsDto detallePedido, HttpServletRequest request) {
		if (detallePedido == null) {
			return 401;//error en la recepcion del detalle
		}else {
			return vtaService.generarVenta(detallePedido);
		}
		
		/*String uriWebService = "http://vgmsistemas.no-ip.biz:8081/servicios341/oauth/token";
		try {
		HttpResponse<JsonNode>  response = Unirest.post(uriWebService)
	            .header("Authorization", "Basic dmdtcHJldmVudGE6cHJldmVudGEq")
	            .header("Accept","application/json")
	            .field("grant_type", "password")
	            .field("username", "vgm")
	            .field("password", "abc123")
	            .asJson();
		String var = response.getBody().getObject().getString("access_token").toString();
		String headeras = response.getHeaders().toString();
		JSONObject objJson = response.getBody().getObject();
		String var2 = response.getStatusText().toString();
		
		}catch(Exception ex) {
			ex.getStackTrace();
		}*/
		/*if (detallePedido == null) {
			
		}else {
			
		}*/
	}

}

