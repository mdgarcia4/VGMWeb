package com.vgmsistemas.vgmweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.vgmsistemas.vgmweb.entity.Banner;


@Repository
public interface BannerRepo extends JpaRepository<Banner, Long>  {
	@Query("SELECT b FROM Banner b "
    		+ "WHERE b.dePagina = ?1 "
    		+ "and b.snActivo = ?2")
	public List<Banner> findByDePaginaAndSnActivo(String dePagina, String snActivo);
}