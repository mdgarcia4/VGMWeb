package com.vgmsistemas.vgmweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vgmsistemas.vgmweb.entity.Empresa;

@Repository
public interface EmpresaRepo  extends JpaRepository<Empresa, Long>  {

}
