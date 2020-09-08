package com.vgmsistemas.vgmweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vgmsistemas.vgmweb.entity.Documento;
import com.vgmsistemas.vgmweb.entity.PkDocumento;

@Repository
public interface DocumentoRepo  extends JpaRepository<Documento, PkDocumento>  {

}
