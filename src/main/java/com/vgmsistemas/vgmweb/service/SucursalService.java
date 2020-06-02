package com.vgmsistemas.vgmweb.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vgmsistemas.vgmweb.entity.Banner;
import com.vgmsistemas.vgmweb.entity.Marca;
import com.vgmsistemas.vgmweb.entity.Sucursal;
import com.vgmsistemas.vgmweb.repository.BannerRepo;
import com.vgmsistemas.vgmweb.repository.MarcaRepo;
import com.vgmsistemas.vgmweb.repository.SucursalRepo;

@Service
public class SucursalService {

	@Autowired
	SucursalRepo sucursalRepo;

	public List<Sucursal> getAll(){
		List<Sucursal> sucursales;
		sucursales = sucursalRepo.findAll();
		return sucursales;
	}
	
}