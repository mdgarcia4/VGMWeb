package com.vgmsistemas.vgmweb.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.vgmsistemas.vgmweb.entity.Articulo;
import com.vgmsistemas.vgmweb.entity.Rubro;
import com.vgmsistemas.vgmweb.repository.ArticuloRepo;
import com.vgmsistemas.vgmweb.repository.RubroRepo;

@Service
public class RubroService {

	@Autowired
	RubroRepo rubroRepo;

	public List<Rubro> getAll()  {

		List<Rubro> rubros ;
		rubros = rubroRepo.findAll();
		return rubros;
	}
	
	public RubroRepo getRubroRepo() {
		return rubroRepo;
	}

	public void setRubroRepo(RubroRepo rubroRepo) {
		this.rubroRepo = rubroRepo;
	}

	
}