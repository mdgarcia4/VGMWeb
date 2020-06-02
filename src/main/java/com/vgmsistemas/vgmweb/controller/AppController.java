package com.vgmsistemas.vgmweb.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vgmsistemas.vgmweb.entity.Articulo;
import com.vgmsistemas.vgmweb.entity.Sucursal;
import com.vgmsistemas.vgmweb.entity.Usuario;
import com.vgmsistemas.vgmweb.service.ArticuloService;
import com.vgmsistemas.vgmweb.service.BannerService;
import com.vgmsistemas.vgmweb.service.MarcaService;
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
	SucursalService sucursalService;
		
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
		
		List<Articulo> paginaArticulos = articuloService.getByTiWebDestacados();
		
		model.addAttribute("banners",bannerService.getByDePaginaAndSnActivo("index", "S"));
		model.addAttribute("articulos", paginaArticulos);
		
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
		List<Sucursal> sucursales;	
		sucursales = sucursalService.getAll();
		model.addAttribute("latitud", latitud);
		model.addAttribute("longitud",longitud);
		model.addAttribute("sucursales",sucursales );
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
	
	@GetMapping({"/shopping-cart","/shopping-cart.html","/shopping-cart.htm"})
	public String getShoppingCar(Model model) {
		
		return "shopping-cart";
	}
	
	@GetMapping("/header")
	public String header() {
		return "header";
	}
	
	@GetMapping("/footer")
	public String footer() {
		return "footer";
	}
	
	@GetMapping("/home")
	public String home(@RequestParam(defaultValue = "1") Integer pagNro,
            @RequestParam(defaultValue = "12") Integer pagTamanio,
            @RequestParam(defaultValue = "descripcion") String ordenadoPor,
            Model model) {
		
		int paginaRecuperar;
		int paginasTotal;
		int paginaSiguiente;
		int paginaAnterior;
		int productoDesde;
		int productoHasta;
		long productosTotal;
		
		paginaRecuperar = pagNro - 1; 
		
		Page<Articulo> paginaArticulos = articuloService.getAll(paginaRecuperar,pagTamanio,ordenadoPor);
		
		paginasTotal = paginaArticulos.getTotalPages();
		productosTotal = paginaArticulos.getTotalElements();
		
		List<Integer> paginas = getVectorPaginas(pagNro,paginasTotal);
		
		if (pagNro <= 1) {
			paginaAnterior = 1;
		}
		else {
			paginaAnterior = pagNro - 1;
		}
		
		if (pagNro >= paginasTotal) {
			paginaSiguiente = pagNro ;
		}
		else {
			paginaSiguiente = pagNro + 1;
		}
		
		productoDesde = paginaRecuperar * pagTamanio + 1;
		productoHasta = paginaRecuperar * pagTamanio + pagTamanio;
				
		model.addAttribute("paginas",paginas);
		model.addAttribute("marcas", marcaService.getBySnWeb("S"));
		model.addAttribute("rubros", rubroService.getBySnWeb("S"));
		model.addAttribute("proveedores", proveedorService.getBySnWeb("S"));
		model.addAttribute("articulos",paginaArticulos);
		model.addAttribute("paginaAnterior", paginaAnterior);
		model.addAttribute("paginaActual",pagNro);
		model.addAttribute("paginaSiguiente",paginaSiguiente);
		model.addAttribute("productoDesde", productoDesde);
		model.addAttribute("productoHasta", productoHasta);
		model.addAttribute("productosTotal", productosTotal);
		
		return "home";
	}
	
	private List<Integer> getVectorPaginas(Integer pagNro, Integer paginasTotal) {
		int vectorPaginaInial;
		int vectorPaginaFinal;
	 	vectorPaginaInial = pagNro / 9;
		vectorPaginaInial = vectorPaginaInial * 9 + 1;
		vectorPaginaFinal = vectorPaginaInial + 9;
		if (vectorPaginaFinal > paginasTotal) {
			vectorPaginaFinal = paginasTotal;
		}
		
		List<Integer> paginas = IntStream.rangeClosed(vectorPaginaInial, vectorPaginaFinal).boxed().collect(Collectors.toList());
			
		return paginas;
	}
}
