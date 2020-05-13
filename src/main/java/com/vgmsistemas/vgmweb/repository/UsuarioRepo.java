package com.vgmsistemas.vgmweb.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vgmsistemas.vgmweb.entity.Usuario;

@Repository
public interface UsuarioRepo extends CrudRepository<Usuario, Long>  {
    public Optional<Usuario> findByUsuario(String username);
}