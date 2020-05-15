package com.vgmsistemas.vgmweb.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vgmsistemas.vgmweb.entity.Articulo;
import com.vgmsistemas.vgmweb.repository.ArticuloRepo;

@Service
public class ArticuloService {

	@Autowired
	ArticuloRepo articuloRepo;

	public List<Articulo> getAll()  {

		List<Articulo> articulos ;
		articulos = articuloRepo.findAll();
		return articulos;
	}

	public ArticuloRepo getArticuloRepo() {
		return articuloRepo;
	}

	public void setArticuloRepo(ArticuloRepo articuloRepo) {
		this.articuloRepo = articuloRepo;
	}
}