package com.vgmsistemas.vgmweb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vgmsistemas.vgmweb.entity.PkRolesUsuarios;
import com.vgmsistemas.vgmweb.entity.RolesUsuarios;
import com.vgmsistemas.vgmweb.entity.Usuario;

@Repository
public interface RolesUsuariosRepo extends JpaRepository<RolesUsuarios, PkRolesUsuarios>  {
    
}