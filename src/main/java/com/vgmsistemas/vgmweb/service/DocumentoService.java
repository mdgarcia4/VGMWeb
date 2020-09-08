package com.vgmsistemas.vgmweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vgmsistemas.vgmweb.entity.Documento;
import com.vgmsistemas.vgmweb.entity.PkDocumento;
import com.vgmsistemas.vgmweb.repository.DocumentoRepo;

@Service
public class DocumentoService {
	@Autowired
	DocumentoRepo documentoRepo;

	public Documento getById(PkDocumento id) throws Exception{
		return documentoRepo.findById(id).get();
	}
}
