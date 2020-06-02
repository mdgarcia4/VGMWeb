package com.vgmsistemas.vgmweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vgmsistemas.vgmweb.entity.Sucursal;


@Repository
public interface SucursalRepo extends JpaRepository<Sucursal, Long>  {
		
}