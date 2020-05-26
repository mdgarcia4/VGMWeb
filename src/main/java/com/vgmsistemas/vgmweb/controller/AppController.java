package com.vgmsistemas.vgmweb.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vgmsistemas.vgmweb.entity.Usuario;
import com.vgmsistemas.vgmweb.service.ArticuloService;
import com.vgmsistemas.vgmweb.service.BannerService;
import com.vgmsistemas.vgmweb.service.MarcaService;
import com.vgmsistemas.vgmweb.service.ProveedorService;
import com.vgmsistemas.vgmweb.service.RubroService;
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
	
	@GetMapping({"/login","/login.html","/longin.htm"})
	public String login(Model model) {
		//model.addAttribute("usr",new Usuario());
		return "login";
	}
	
	
	@GetMapping({"/registrar","/registrar.html","/registrar.htm"})
	public String getRegistrar(Model model) {
		
		return "registrar";
	}
	
	@ModelAttribute(value = "usr")
	public Usuario newUsuario()
	{
	    return new Usuario();
	}
	
	@PostMapping({"/registrar","/registrar.html","/registrar.htm"})
	public String registrar(@ModelAttribute("usr") Usuario usr) {
		userService.crearActualizarUsuario(usr);
		return "login";
	}
	
	
	@GetMapping({"/","/index","/index.html","/index.htm"})
	public String index(Model model) {
		model.addAttribute("banners",bannerService.getByDePaginaAndSnActivo("index", "S"));
		return "index";
	}
	
		
	@GetMapping("/categorias1")
	public String categorias(Model model, @PageableDefault(page = 0, size = 9) Pageable pageable) {
		model.addAttribute("marcas", marcaService.getBySnWeb("S"));
		model.addAttribute("rubros", rubroService.getBySnWeb("S"));
		model.addAttribute("proveedores", proveedorService.getBySnWeb("S"));
		model.addAttribute("articulos",articuloService.getAll(pageable));
		
		return "categorias1";
	}
	
	@GetMapping("/subrubro")
	public String categorias(@RequestParam("rubro") Long rubro, @RequestParam("subrubro") Long subrubro  , Model model, @PageableDefault(page = 0, size = 9) Pageable pageable) {
		model.addAttribute("marcas", marcaService.getBySnWeb("S"));
		model.addAttribute("rubros", rubroService.getBySnWeb("S"));
		model.addAttribute("proveedores", proveedorService.getBySnWeb("S"));
		model.addAttribute("articulos",articuloService.getByRubroAndSubrubro(rubro, subrubro,pageable));
		
		return "categorias";
	}
	
	@GetMapping("/marca")
	public String categorias(@RequestParam("marca") Long marca,  Model model, @PageableDefault(page = 0, size = 9) Pageable pageable) {
		model.addAttribute("marcas", marcaService.getBySnWeb("S"));
		model.addAttribute("rubros", rubroService.getBySnWeb("S"));
		model.addAttribute("proveedores", proveedorService.getBySnWeb("S"));
		model.addAttribute("articulos",articuloService.getByMarca(marca, pageable));
		
		return "categorias";
	}
	
	@GetMapping("/proveedor")
	public String categoriasProv(@RequestParam("proveedor") Long proveedor,  Model model, @PageableDefault(page = 0, size = 9) Pageable pageable) {
		model.addAttribute("marcas", marcaService.getBySnWeb("S"));
		model.addAttribute("rubros", rubroService.getBySnWeb("S"));
		model.addAttribute("proveedores", proveedorService.getBySnWeb("S"));
		model.addAttribute("articulos",articuloService.getByProveedor(proveedor, pageable));
		
		return "categorias";
	}
	
	@GetMapping("/contacto" )
	public String contacto(Model model) {
		Double latitud=-29.704071;
		Double longitud=-57.1297873;
				
		model.addAttribute("latitud", latitud);
		model.addAttribute("longitud",longitud);
		return "contacto";
	}
	
	/*@GetMapping("/categorias1")
	public String categorias1(Model model, @PageableDefault(page = 0, size = 9) Pageable pageable) {
		model.addAttribute("marcas", marcaService.getBySnWeb("S"));
		model.addAttribute("rubros", rubroService.getBySnWeb("S"));
		model.addAttribute("proveedores", proveedorService.getBySnWeb("S"));
		model.addAttribute("articulos",articuloService.getAll(pageable));
		
		return "categorias1";
	}*/
	
	@GetMapping("/header")
	public String header() {
		return "header";
	}
	
	@GetMapping("/footer")
	public String footer() {
		return "footer";
	}
}
