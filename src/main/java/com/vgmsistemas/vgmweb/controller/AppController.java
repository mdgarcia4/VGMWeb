package com.vgmsistemas.vgmweb.controller;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.vgmsistemas.vgmweb.entity.Banner;
import com.vgmsistemas.vgmweb.entity.ListaPrecioDetalle;
import com.vgmsistemas.vgmweb.entity.Usuario;
import com.vgmsistemas.vgmweb.service.ArticuloService;
import com.vgmsistemas.vgmweb.service.BannerService;
import com.vgmsistemas.vgmweb.service.ClienteService;
import com.vgmsistemas.vgmweb.service.ListaPrecioDetalleService;
import com.vgmsistemas.vgmweb.service.MarcaService;
import com.vgmsistemas.vgmweb.service.PropertiesService;
import com.vgmsistemas.vgmweb.service.ProveedorService;
import com.vgmsistemas.vgmweb.service.RubroService;
import com.vgmsistemas.vgmweb.service.SucursalService;
import com.vgmsistemas.vgmweb.service.UserDetailsServiceImpl;
import org.springframework.ui.Model;

@Controller
public class AppController {

	@Autowired
	ArticuloService articuloService;
	@Autowired
	RubroService rubroService;
	@Autowired
	MarcaService marcaService;
	@Autowired
	ProveedorService proveedorService;
	@Autowired
	UserDetailsServiceImpl userService;
	@Autowired
	BannerService bannerService;
	@Autowired
	ClienteService clienteService;
	@Autowired
	ListaPrecioDetalleService listaPrecioDetalleService;
	@Autowired
	SucursalService sucursalService;
	@Autowired
	PropertiesService propertyService;
	static Logger logger = LoggerFactory.getLogger(AppController.class);

	@GetMapping({ "/login", "/login.html", "/login.htm" })
	public String login(Model model) {
		// model.addAttribute("usr",new Usuario());
		return "login";
	}

	@GetMapping({ "/registrar", "/registrar.html", "/registrar.htm" })
	public String getRegistrar(Model model) {
		return "registrar";
	}

	@ModelAttribute(value = "usr")
	public Usuario newUsuario() {
		return new Usuario();
	}

	@PostMapping({ "/registrar", "/registrar.html", "/registrar.htm" })
	public String registrar(Model model, @ModelAttribute("usr") Usuario usr) {
		try {
			userService.crearActualizarUsuario(usr);
			List<Banner> list = bannerService.getByDePaginaAndSnActivo("registrar", "S");
			if (list != null) {
				model.addAttribute("banner", list.get(0));
			}
			return "login";
		} catch (UsernameNotFoundException ex) {
			logger.error("Error en clase AppController-Página: registrar. Usuario no encontrado" + ex.getStackTrace()
					+ " VGMMESAGGE: " + ex.getMessage() + " VGMTOSTRING: " + ex.toString());

			logger.error("Error en clase AppController-Página: registrar. Usuario no encontrado" + ex);
			model.addAttribute("RegistrarErrorMensaje", ex.getMessage());
			return "registrar";
		} catch (Exception e) {
			logger.error("Error inesperado en clase AppController-Página: registrar. " + e.getStackTrace()
					+ " VGMMESAGGE: " + e.getMessage() + " VGMTOSTRING: " + e.toString());
			logger.error("Error inesperado en clase AppController-Página: registrar. " + e);
			model.addAttribute("RegistrarErrorMensaje", e.getMessage());
			return "registrar";
		}
	}

	@GetMapping({ "/", "/index", "/index.html", "/index.htm" })
	public String index(Model model) {
		try {
			String path = "";
			String usuario = "";
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			// si no esta logueado busco precio por el usuario comun vgm
			if (auth.getPrincipal().equals("anonymousUser")) {
				usuario = "vgm";
			} else {
				UserDetails userDetail = (UserDetails) auth.getPrincipal();
				usuario = userDetail.getUsername();
			}

			Iterator<ListaPrecioDetalle> paginaArticulos = listaPrecioDetalleService
					.getListaPrecioArticulosDestacados(clienteService.getClienteByUsuario(usuario));
			String pathSourceImage = propertyService.getPathSrcImagen();
			if (!pathSourceImage.equals("") && pathSourceImage != null) {
				path = pathSourceImage;
			} else {
				path = "img\\";
			}
			path = path.replaceAll("\\\\", "/");
			model.addAttribute("banners", bannerService.getByDePaginaAndSnActivo("index", "S"));
			model.addAttribute("articulos", paginaArticulos);
			model.addAttribute("nameapp", propertyService.getNameApp());
			model.addAttribute("pathsrcimagen", path);

			return "index";
		} catch (ClassCastException ex) {
			logger.error("Error en clase AppController-Página: No existe un usuario logueado." + ex.getStackTrace()
					+ " VGMMESAGGE: " + ex.getMessage() + " VGMTOSTRING: " + ex.toString());
			logger.error("Error en clase AppController-Página: No existe un usuario logueado." + ex);
			return "error";
		} catch (Exception e) {
			logger.error("Error inesperado en clase AppController-Página: index. " + e.getStackTrace() + " VGMMESAGGE:"
					+ e.getMessage() + " VGMTOSTRING:" + e.toString());
			logger.error("Error inesperado en clase AppController-Página: index. " + e);
			return "error";
		}
	}
}
