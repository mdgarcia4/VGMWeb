package com.vgmsistemas.vgmweb.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.vgmsistemas.vgmweb.dto.ContactoConsulta;
import com.vgmsistemas.vgmweb.entity.Banner;
import com.vgmsistemas.vgmweb.entity.Sucursal;
import com.vgmsistemas.vgmweb.service.SucursalService;
import com.vgmsistemas.vgmweb.service.BannerService;
import com.vgmsistemas.vgmweb.service.ContactoService;
import com.vgmsistemas.vgmweb.service.PropertiesService;

@Controller
public class ContactoController {
	@Autowired
	SucursalService sucursalService;
	@Autowired
	PropertiesService prepertyService;
	@Autowired
	BannerService bannerService;
	@Autowired
	ContactoService contactoService;
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
			logger.error("Error inesperado en clase ContactoController-Página: contacto. " + e);
			return "error";
		}

	}

	@ModelAttribute(value = "contacto")
	public ContactoConsulta newContactoConsult() {
		return new ContactoConsulta();
	}

	@PostMapping({ "/contacto", "/contacto", "/contacto.htm" })
	public String consultaContacto(Model model, @ModelAttribute("contacto") ContactoConsulta contacto) {
		Float latitud = -29.704071F;
		Float longitud = -57.1297873F;
		String infoWindowDesc = "Casa Central";
		String respuesta = "";
		List<Sucursal> sucursales = null;
		try {
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
			respuesta = contactoService.mandarConsulta(contacto);
			model.addAttribute("respuestaEnvio", respuesta);
		} catch (Exception e) {
			logger.error("Error inesperado en clase ContactoController-Página: contacto. Método Post. " + e.getStackTrace()
					+ " VGMMESAGGE: " + e.getMessage() + " VGMTOSTRING: " + e.toString());
			logger.error("Error inesperado en clase ContactoController-Página: contacto. Método Post. " + e);
			// TODO: handle exception
			model.addAttribute("respuestaEnvio", e.toString());
		}

		model.addAttribute("latitud", latitud);
		model.addAttribute("longitud", longitud);
		model.addAttribute("infoWindowDescripcion", infoWindowDesc);
		model.addAttribute("sucursales", sucursales);
		model.addAttribute("apiKey", prepertyService.getApiKeyGoogle());
		model.addAttribute("nameapp", prepertyService.getNameApp());
		return "contacto";

	}
}
