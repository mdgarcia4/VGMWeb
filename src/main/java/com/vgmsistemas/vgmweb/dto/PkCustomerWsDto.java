package com.vgmsistemas.vgmweb.dto;

import java.io.Serializable;

public class PkCustomerWsDto implements Serializable {
	
	private static final long serialVersionUID = -199332245759982384L;
	private long idCliente;
	private long idComercio;
	private long idSucursal;
	
	
	public long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}
	public long getIdComercio() {
		return idComercio;
	}
	public void setIdComercio(long idComercio) {
		this.idComercio = idComercio;
	}
	public long getIdSucursal() {
		return idSucursal;
	}
	public void setIdSucursal(long idSucursal) {
		this.idSucursal = idSucursal;
	}

}
