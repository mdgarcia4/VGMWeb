/**
 * 
 */
package com.vgmsistemas.vgmweb.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonView;


@Embeddable
public class PkCliente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "id_sucursal")
	private long idSucursal;
	
	@Column(name = "id_cliente")
	private long idCliente;
	
	@Column(name = "id_comercio")
	private long idComercio;
	
	public PkCliente(){
		
	}
	public PkCliente (Long idSucursal, Long idCliente, Long idComercio){
		this.idSucursal = idSucursal;
		this.idComercio = idComercio;
		this.idCliente = idCliente;
	}
	
	/**
	 * @return the idSucursal
	 */
	public long getIdSucursal() {
		return this.idSucursal;
	}
	/**
	 * @param idSucursal the idSucursal to set
	 */
	public void setIdSucursal(long idSucursal) {
		this.idSucursal = idSucursal;
	}
	/**
	 * @return the idCliente
	 */
	public long getIdCliente() {
		return this.idCliente;
	}
	/**
	 * @param idCliente the idCliente to set
	 */
	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}
	/**
	 * @return the idComercio
	 */
	public long getIdComercio() {
		return this.idComercio;
	}
	/**
	 * @param idComercio the idComercio to set
	 */
	public void setIdComercio(long idComercio) {
		this.idComercio = idComercio;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (this.idCliente ^ (this.idCliente >>> 32));
		result = prime * result
				+ (int) (this.idComercio ^ (this.idComercio >>> 32));
		result = prime * result
				+ (int) (this.idSucursal ^ (this.idSucursal >>> 32));
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PkCliente other = (PkCliente) obj;
		if (this.idCliente != other.idCliente)
			return false;
		if (this.idComercio != other.idComercio)
			return false;
		if (this.idSucursal != other.idSucursal)
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.idSucursal + "-" +this.idCliente +"-" +this.idComercio;
	}

}
