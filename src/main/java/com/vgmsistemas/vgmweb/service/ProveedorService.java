package com.vgmsistemas.vgmweb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vgmsistemas.vgmweb.entity.Proveedor;
import com.vgmsistemas.vgmweb.repository.ProveedorRepo;

@Service
public class ProveedorService {

	@Autowired
	ProveedorRepo proveedorRepo;

	public List<Proveedor> getAll() throws Exception {

		List<Proveedor> proveedores;
		proveedores = proveedorRepo.findAll();
		return proveedores;
	}

	public List<Proveedor> getBySnWeb(String sn) throws Exception {
		List<Proveedor> marcas;
		marcas = proveedorRepo.findBySnWeb(sn);
		return marcas;
	}

	public ProveedorRepo getProveedorRepo() throws Exception {
		return proveedorRepo;
	}

	public void setProveedorRepo(ProveedorRepo proveedorRepo) throws Exception {
		this.proveedorRepo = proveedorRepo;
	}
	
	public Optional<Proveedor> getById(Long id) {
		return proveedorRepo.findById(id);
	}
}