package com.vgmsistemas.vgmweb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name = "proveedores" )
public class Proveedor {
	@Id
	@GeneratedValue
	@Column(name = "id_proveedor")
	private Long idProveedor;
	@Column(name = "de_proveedor")
	private String deProveedor;
	@Column(name = "ti_proveedor")
	private String tiProveedor;
	@Column(name = "id_plancta")
	private Integer idPlancta;
	@Column(name = "id_sucursal")
	private Long idSucursal;
	@Column(name = "nu_cuit")
	private String nuCuit;
	
	public Long getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(Long idProveedor) {
		this.idProveedor = idProveedor;
	}
	public String getDeProveedor() {
		return deProveedor;
	}
	public void setDeProveedor(String deProveedor) {
		this.deProveedor = deProveedor;
	}
	public String getTiProveedor() {
		return tiProveedor;
	}
	public void setTiProveedor(String tiProveedor) {
		this.tiProveedor = tiProveedor;
	}
	public Integer getIdPlancta() {
		return idPlancta;
	}
	public void setIdPlancta(Integer idPlancta) {
		this.idPlancta = idPlancta;
	}
	public Long getIdSucursal() {
		return idSucursal;
	}
	public void setIdSucursal(Long idSucursal) {
		this.idSucursal = idSucursal;
	}
	public String getNuCuit() {
		return nuCuit;
	}
	public void setNuCuit(String nuCuit) {
		this.nuCuit = nuCuit;
	}

}
