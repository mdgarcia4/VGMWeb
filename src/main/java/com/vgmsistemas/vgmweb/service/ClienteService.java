package com.vgmsistemas.vgmweb.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vgmsistemas.vgmweb.entity.Cliente;
import com.vgmsistemas.vgmweb.entity.Clientes;
import com.vgmsistemas.vgmweb.entity.PkCliente;
import com.vgmsistemas.vgmweb.repository.ClienteRepo;

@Service
public class ClienteService {

	@Autowired
	ClienteRepo clienteRepo;

	public Optional<Cliente> getAll(PkCliente id) throws Exception {
		Optional<Cliente> cliente = clienteRepo.findById(id);
		return cliente;
	}

	public Cliente getClienteByUsuario(String usuario) throws Exception {
		Cliente cliente = clienteRepo.findClienteByUsuario(usuario);
		return cliente;
	}

	public ClienteRepo getClienteRepo() throws Exception {
		return clienteRepo;
	}

	public void setClienteRepo(ClienteRepo clienteRepo) throws Exception {
		this.clienteRepo = clienteRepo;
	}

	public Clientes getAllClientesByUsuario(String usuario) throws Exception {
		Clientes clientesByUser = new Clientes();
		Optional<List<Cliente>> optCliente = clienteRepo.allClientesByUsuario(usuario);
		if (optCliente.isPresent()) {
			clientesByUser.setClientes(optCliente.get());
		}
		return clientesByUser;
	}

	/* Devuelvo una exception si no existe el comercio para el usuario */
	public Cliente getClienteByUsuarioComercio(String usuario, long comercio) throws Exception {
		return clienteRepo.getClienteByUsuarioComercio( usuario, comercio).orElseThrow(() -> new Exception("El Comercio "
				+ String.valueOf(comercio) + " ingresado no existe con el usuario '" + usuario + "'logueado."));
	}

}