package com.vgmsistemas.vgmweb.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.vgmsistemas.vgmweb.entity.Banner;
import com.vgmsistemas.vgmweb.entity.Sucursal;
import com.vgmsistemas.vgmweb.service.SucursalService;
import com.vgmsistemas.vgmweb.service.BannerService;
import com.vgmsistemas.vgmweb.service.PropertiesService;

@Controller
public class ContactoController {
	@Autowired
	SucursalService sucursalService;
	@Autowired
	PropertiesService prepertyService;
	@Autowired
	BannerService bannerService;
	static Logger logger = LoggerFactory.getLogger(ContactoController.class);

	@GetMapping({ "/contacto", "/contacto", "/contacto.htm" })
	public String contacto(Model model) {
		Float latitud = -29.704071F;
		Float longitud = -57.1297873F;
		String infoWindowDesc = "Casa Central";
		
		try {
			List<Sucursal> sucursales;
			sucursales = sucursalService.getAll();
			for (Sucursal suc : sucursales) {
				if (suc.getId() == suc.getSucActivaEmpresa()) {
					latitud = suc.getLatitud();
					longitud = suc.getLongitud();
					infoWindowDesc = suc.getDescripcion();
				}
			}
			List<Banner> list = bannerService.getByDePaginaAndSnActivo("contacto", "S");
			if (list != null) {
				model.addAttribute("banner", list.get(0));
			}
			
			model.addAttribute("latitud", latitud);
			model.addAttribute("longitud", longitud);
			model.addAttribute("infoWindowDescripcion", infoWindowDesc);
			model.addAttribute("sucursales", sucursales);
			model.addAttribute("apiKey", prepertyService.getApiKeyGoogle());
			model.addAttribute("nameapp", prepertyService.getNameApp());
			return "contacto";
		} catch (Exception e) {
			logger.error("Error inesperado en clase ContactoController-Página: contacto. " + e.getStackTrace()
					+ " VGMMESAGGE: " + e.getMessage() + " VGMTOSTRING: " + e.toString());
			return "error";
		}

	}
}
