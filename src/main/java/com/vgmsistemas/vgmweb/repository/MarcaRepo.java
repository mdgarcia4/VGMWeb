package com.vgmsistemas.vgmweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vgmsistemas.vgmweb.entity.Marca;

@Repository
public interface MarcaRepo extends JpaRepository<Marca, Long>  {
    public List<Marca> findBySnWeb(String snWeb);
 
}