package com.vgmsistemas.vgmweb.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vgmsistemas.vgmweb.entity.Articulo;
import com.vgmsistemas.vgmweb.entity.Marca;
import com.vgmsistemas.vgmweb.entity.Rubro;
import com.vgmsistemas.vgmweb.entity.Subrubro;

@Repository
public interface ArticuloRepo extends JpaRepository<Articulo, Long>  {
    public List<Articulo> findBySnActivo(String snActivo);
    
    //@Query("select a from Articulo a")
    //Page<Articulo> findByAllPage(Pageable pageable);
    //public List<Articulo> findBySubrubro(Integer subrubro);
    //public List<Articulo> findByRubro(Integer rubro);
    //public List<Articulo> findByMarca(Integer marca);
}