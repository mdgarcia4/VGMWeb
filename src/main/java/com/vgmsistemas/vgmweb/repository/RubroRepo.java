package com.vgmsistemas.vgmweb.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vgmsistemas.vgmweb.entity.Articulo;
import com.vgmsistemas.vgmweb.entity.Marca;
import com.vgmsistemas.vgmweb.entity.Negocio;
import com.vgmsistemas.vgmweb.entity.Rubro;
import com.vgmsistemas.vgmweb.entity.Subrubro;

@Repository
public interface RubroRepo extends CrudRepository<Rubro, Long>  {
    public List<Rubro> findByNegocio(Negocio negocio);
 
}