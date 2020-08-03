package com.vgmsistemas.vgmweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vgmsistemas.vgmweb.entity.Empresa;
import com.vgmsistemas.vgmweb.repository.EmpresaRepo;

@Service
public class EmpresaService {
	@Autowired
	EmpresaRepo empresaRepo;

	public Empresa getById(Long id) {
		return empresaRepo.findById(id).get();
	}
}
