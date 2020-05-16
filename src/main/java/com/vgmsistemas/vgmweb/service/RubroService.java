package com.vgmsistemas.vgmweb.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vgmsistemas.vgmweb.entity.Rubro;
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
	
	public List<Rubro> getBySnWeb(String sn)  {

		List<Rubro> rubros ;
		rubros = rubroRepo.findBySnWeb(sn);
		return rubros;
	}
	
	public RubroRepo getRubroRepo() {
		return rubroRepo;
	}

	public void setRubroRepo(RubroRepo rubroRepo) {
		this.rubroRepo = rubroRepo;
	}

}