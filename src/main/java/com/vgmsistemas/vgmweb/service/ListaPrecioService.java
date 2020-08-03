package com.vgmsistemas.vgmweb.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vgmsistemas.vgmweb.entity.ListaPrecio;
import com.vgmsistemas.vgmweb.repository.ListaPrecioRepo;

@Service
public class ListaPrecioService {
	@Autowired
	ListaPrecioRepo listaPrecioRepo;
	
	public Optional<ListaPrecio> getByIdListaPrecio(Long id) {
		Optional<ListaPrecio> listaPrecio;
		listaPrecio = listaPrecioRepo.findById(id);
		return listaPrecio;
	} 
}
