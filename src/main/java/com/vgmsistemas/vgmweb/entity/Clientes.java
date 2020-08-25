package com.vgmsistemas.vgmweb.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Clientes")
@XmlAccessorType(XmlAccessType.FIELD)

public class Clientes implements Serializable {

	private static final long serialVersionUID = 7714551411495534695L;
	@XmlElement(name = "Cliente")
	private List<Cliente> clientes;

	/**
	 * @return the clientes
	 */
	public List<Cliente> getClientes() {
		if (clientes == null) {
			clientes = new ArrayList<Cliente>();
		}
		return clientes;
	}

	/**
	 * @param clientes the clientes to set
	 */
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

}
