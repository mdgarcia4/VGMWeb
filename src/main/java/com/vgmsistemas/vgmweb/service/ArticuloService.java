package com.vgmsistemas.vgmweb.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.vgmsistemas.vgmweb.entity.Articulo;
import com.vgmsistemas.vgmweb.repository.ArticuloRepo;

@Service
public class ArticuloService {

	@Autowired
	ArticuloRepo articuloRepo;

	public Page<Articulo> getAll(Integer pagNro, Integer pagTamanio, String ordenadoPor) throws Exception {

		Pageable pagina = PageRequest.of(pagNro, pagTamanio, Sort.by(ordenadoPor));

		Page<Articulo> articulos = articuloRepo.findAll(pagina);

		/*
		 * if(articulos.hasContent()) { return articulos.getContent(); } else { return
		 * new ArrayList<Articulo>(); }
		 */

		return articulos;
	}

	public Page<Articulo> getArticuloBySucursalAndLista(Long sucursal, Long lista, Integer pagNro, Integer pagTamanio,
			String ordenadoPor) throws Exception {

		Pageable pagina = PageRequest.of(pagNro, pagTamanio, Sort.by(ordenadoPor));

		Page<Articulo> articulos = articuloRepo.findArticuloBySucursalAndLista(sucursal, lista, pagina);

		/*
		 * if(articulos.hasContent()) { return articulos.getContent(); } else { return
		 * new ArrayList<Articulo>(); }
		 */

		return articulos;
	}

	public Page<Articulo> getByRubroAndSubrubro(Long rubro, Long subrubro, Pageable pageable) throws Exception{
		Page<Articulo> articulos;
		articulos = articuloRepo.findArticuloByRubroAndSubrubro(rubro, subrubro, pageable);
		return articulos;
	};

	public Page<Articulo> getByMarca(Long marca, Pageable pageable) throws Exception{
		Page<Articulo> articulos;
		articulos = articuloRepo.findArticuloByMarca(marca, pageable);
		return articulos;
	};

	public Page<Articulo> getByProveedor(Long proveedor, Pageable pageable) throws Exception{
		Page<Articulo> articulos;
		articulos = articuloRepo.findArticuloByProveedor(proveedor, pageable);
		return articulos;
	};

	public Page<Articulo> getAll(Pageable pageable) throws Exception{
		return articuloRepo.findAll(pageable);
	}

	public Page<Articulo> searchByDescripcion(String descripcion, Pageable pageable) throws Exception{

		return articuloRepo.findByDescripcionContainingIgnoreCase(descripcion, pageable);
	}

	public List<Articulo> getByTiWebDestacados() throws Exception{
		ArrayList<String> tiWebDestacados = new ArrayList<>();

		tiWebDestacados.add("cat1");//cat1
		tiWebDestacados.add("cat2");//cat2
		tiWebDestacados.add("cat3");//cat3

		return articuloRepo.findByTiWebDestacadosIn(tiWebDestacados);
	}

	public ArticuloRepo getArticuloRepo() throws Exception{
		return articuloRepo;
	}

	public void setArticuloRepo(ArticuloRepo articuloRepo) throws Exception{
		this.articuloRepo = articuloRepo;
	}
}