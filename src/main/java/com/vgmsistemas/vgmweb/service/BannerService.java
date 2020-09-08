package com.vgmsistemas.vgmweb.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vgmsistemas.vgmweb.entity.Banner;
import com.vgmsistemas.vgmweb.repository.BannerRepo;

@Service
public class BannerService {

	@Autowired
	BannerRepo bannerRepo;

	public List<Banner> getByDePaginaAndSnActivo(String dePagina, String snActivo)throws Exception{
		List<Banner> banners;
		banners = bannerRepo.findByDePaginaAndSnActivo(dePagina, snActivo);
		if (banners.isEmpty()) {
			return null;
		}
		return banners;
	}
	
}