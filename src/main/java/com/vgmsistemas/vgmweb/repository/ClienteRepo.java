package com.vgmsistemas.vgmweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vgmsistemas.vgmweb.entity.Cliente;
import com.vgmsistemas.vgmweb.entity.PkCliente;

@Repository
public interface ClienteRepo extends JpaRepository<Cliente, PkCliente>  {
    
}