package com.vgmsistemas.vgmweb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vgmsistemas.vgmweb.entity.Sucursal;
import com.vgmsistemas.vgmweb.repository.SucursalRepo;

@Service
public class SucursalService {

	@Autowired
	SucursalRepo sucursalRepo;

	public List<Sucursal> getAll(){
		List<Sucursal> sucursales;
		sucursales = sucursalRepo.findAll();
		for(Sucursal suc: sucursales) 
		{
			if(suc.getLatitud()== null) 
			{
				suc.setLatitud(0F);
			}
			if(suc.getLongitud()== null) 
			{
				suc.setLongitud(0F);
			}
		}
		return sucursales;
	}
	
	public Sucursal getById(Long id) {
		Optional<Sucursal> optSucursal = sucursalRepo.findById(id);
		if( optSucursal.isPresent()) {
			return optSucursal.get();
		}else {
			return null;
		}
	}
}