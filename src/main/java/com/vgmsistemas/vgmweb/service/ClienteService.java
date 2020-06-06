package com.vgmsistemas.vgmweb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.vgmsistemas.vgmweb.entity.Articulo;
import com.vgmsistemas.vgmweb.entity.Cliente;
import com.vgmsistemas.vgmweb.entity.PkCliente;
import com.vgmsistemas.vgmweb.repository.ArticuloRepo;
import com.vgmsistemas.vgmweb.repository.ClienteRepo;

@Service
public class ClienteService {

	@Autowired
	ClienteRepo clienteRepo;

	public Optional<Cliente> getAll(PkCliente id)  {
		
		 Optional<Cliente> cliente = clienteRepo.findById(id);
	     return cliente;
	}

	public ClienteRepo getClienteRepo() {
		return clienteRepo;
	}

	public void setClienteRepo(ClienteRepo clienteRepo) {
		this.clienteRepo = clienteRepo;
	}
	
	
	
}