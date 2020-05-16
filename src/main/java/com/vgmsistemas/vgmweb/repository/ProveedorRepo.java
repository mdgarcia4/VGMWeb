package com.vgmsistemas.vgmweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vgmsistemas.vgmweb.entity.Proveedor;

@Repository
public interface ProveedorRepo extends JpaRepository<Proveedor, Long>  {
    public List<Proveedor> findBySnWeb(String snWeb);
 
}