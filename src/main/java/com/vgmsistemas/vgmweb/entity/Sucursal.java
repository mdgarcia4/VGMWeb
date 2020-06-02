package com.vgmsistemas.vgmweb.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table( name = "sucursales" )
public class Sucursal implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4939769535224766428L;
		@Id
	@Column(name = "id_sucursal")
	private long id;
	
	@Column(name = "id_deposito")
	private int idDeposito;
	
	@Column(name = "de_sucursal")
	private String descripcion;
	
	@Column(name = "fe_sistema")
	private Date fechaSistema;
	
	@Column(name="nu_telefono")
	private String nuTelefono;
	
	@Column(name="de_mail")
	private String deMail;
	
	@Column(name="de_domicilio")
	private String deDomicilio;
	
	@Formula("(SELECT empresas.ta_ivainscripto"
			+ " FROM empresas)")
	private Float tasaIvaInscripto;
	@Formula("(SELECT empresas.ta_ivanoinscripto"
			+ " FROM empresas)")
	private Float tasaIvaNoInscripto;	
	
	 
	    
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the idDeposito
	 */
	public int getIdDeposito() {
		return idDeposito;
	}
	/**
	 * @param idDeposito the idDeposito to set
	 */
	public void setIdDeposito(int idDeposito) {
		this.idDeposito = idDeposito;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Sucursal)) {
			return false;
		}
		Sucursal other = (Sucursal) obj;
		if (descripcion == null) {
			if (other.descripcion != null) {
				return false;
			}
		} else if (!descripcion.equals(other.descripcion)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (idDeposito != other.idDeposito) {
			return false;
		}
		return true;
	}
	/**
	 * @return the fechaSistema
	 */
	public Date getFechaSistema() {
		return fechaSistema;
	}
	/**
	 * @param fechaSistema the fechaSistema to set
	 */
	public void setFechaSistema(Date fechaSistema) {
		this.fechaSistema = fechaSistema;
	}
	/**
	 * @return the tasaIvaInscripto
	 */
	public Float getTasaIvaInscripto() {
		return tasaIvaInscripto;
	}
	/**
	 * @param tasaIvaInscripto the tasaIvaInscripto to set
	 */
	public void setTasaIvaInscripto(Float tasaIvaInscripto) {
		this.tasaIvaInscripto = tasaIvaInscripto;
	}
	/**
	 * @return the tasaIvaNoInscripto
	 */
	public Float getTasaIvaNoInscripto() {
		return tasaIvaNoInscripto;
	}
	/**
	 * @param tasaIvaNoInscripto the tasaIvaNoInscripto to set
	 */
	public void setTasaIvaNoInscripto(Float tasaIvaNoInscripto) {
		this.tasaIvaNoInscripto = tasaIvaNoInscripto;
	}
		
	public String getNuTelefono() {
		return nuTelefono;
	}
	public void setNuTelefono(String nuTelefono) {
		this.nuTelefono = nuTelefono;
	}
	public String getDeMail() {
		return deMail;
	}
	public void setDeMail(String deMail) {
		this.deMail = deMail;
	}
	public String getDeDomicilio() {
		return deDomicilio;
	}
	public void setDeDomicilio(String deDomicilio) {
		this.deDomicilio = deDomicilio;
	}
	
}
