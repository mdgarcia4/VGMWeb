package com.vgmsistemas.vgmweb.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vgmsistemas.vgmweb.entity.Cliente;
import com.vgmsistemas.vgmweb.entity.PkCliente;
import com.vgmsistemas.vgmweb.repository.ClienteRepo;

@Service
public class ClienteService {

	@Autowired
	ClienteRepo clienteRepo;

	public Optional<Cliente> getAll(PkCliente id)  {
		 Optional<Cliente> cliente = clienteRepo.findById(id);
	     return cliente;
	}
	
	public Cliente getClienteByUsuario(String usuario){
		Cliente cliente = clienteRepo.findClienteByUsuario(usuario);
		return cliente;
	}

	public ClienteRepo getClienteRepo() {
		return clienteRepo;
	}

	public void setClienteRepo(ClienteRepo clienteRepo) {
		this.clienteRepo = clienteRepo;
	}
	
	
	
}