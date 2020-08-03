package com.vgmsistemas.vgmweb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vgmsistemas.vgmweb.entity.ListaPrecio;

@Repository
public interface ListaPrecioRepo extends JpaRepository<ListaPrecio, Long>{
	public Optional<ListaPrecio> findById(Long id);
}
