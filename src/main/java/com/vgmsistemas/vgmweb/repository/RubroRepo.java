package com.vgmsistemas.vgmweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vgmsistemas.vgmweb.entity.Negocio;
import com.vgmsistemas.vgmweb.entity.Rubro;


@Repository
public interface RubroRepo extends JpaRepository<Rubro, Long>  {
    public List<Rubro> findByNegocio(Negocio negocio);
    public List<Rubro> findBySnWeb(String snWeb);
}