package com.vgmsistemas.vgmweb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.vgmsistemas.vgmweb.entity.Cliente;
import com.vgmsistemas.vgmweb.entity.PkCliente;

@Repository
public interface ClienteRepo extends JpaRepository<Cliente, PkCliente>  {
	
	@Query("SELECT c FROM Cliente c, Usuario u "
    		+ "WHERE c.id.idSucursal = u.idSucursal"
			+ "  and c.id.idCliente = u.idCliente"
			+ "  and c.id.idComercio = u.idComercio"
    		+ "  and u.usuario= ?1")
	public Cliente findClienteByUsuario(String usuario);
	
	@Query("SELECT c FROM Cliente c, Usuario u "
    		+ "WHERE c.id.idSucursal = u.idSucursal"
			+ "  and c.id.idCliente = u.idCliente"
    		+ "  and u.usuario= ?1")
	public Optional<List<Cliente>> allClientesByUsuario(String usuario);
	
	@Query("SELECT c FROM Cliente c, Usuario u "
    		+ "WHERE c.id.idSucursal = u.idSucursal"
			+ "  and c.id.idCliente = u.idCliente"
			+ "  and c.id.idComercio = ?2"
    		+ "  and u.usuario= ?1")
	public Optional<Cliente> getClienteByUsuarioComercio(String usuario, long comercio);
}