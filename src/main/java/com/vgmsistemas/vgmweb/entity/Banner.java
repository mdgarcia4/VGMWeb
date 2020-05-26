package com.vgmsistemas.vgmweb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Banner {
	@Id
	@Column(name = "id")	
	private Long id;
	
	@Column(name = "de_pagina")
	private String dePagina;
	
	@Column(name = "de_titulo")
	private String deTitulo;
	
	@Column(name = "de_imagen")
	private String deImagen;
	  
	@Column(name = "de_descripcion")
	private String deDescripcion;
	 
	@Column(name = "de_enlace")
	private String deEnlace;
	
	@Column(name = "de_descripcion_enlace")
	private String deDescripcionEnlace;
	  
	@Column(name = "sn_activo")
	private String snActivo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDePagina() {
		return dePagina;
	}

	public void setDePagina(String dePagina) {
		this.dePagina = dePagina;
	}

	public String getDeTitulo() {
		return deTitulo;
	}

	public void setDeTitulo(String deTitulo) {
		this.deTitulo = deTitulo;
	}

	public String getDeImagen() {
		return deImagen;
	}

	public void setDeImagen(String deImagen) {
		this.deImagen = deImagen;
	}

	public String getDeDescripcion() {
		return deDescripcion;
	}

	public void setDeDescripcion(String deDescripcion) {
		this.deDescripcion = deDescripcion;
	}

	public String getDeEnlace() {
		return deEnlace;
	}

	public void setDeEnlace(String deEnlace) {
		this.deEnlace = deEnlace;
	}

	public String getDeDescripcionEnlace() {
		return deDescripcionEnlace;
	}

	public void setDeDescripcionEnlace(String deDescripcionEnlace) {
		this.deDescripcionEnlace = deDescripcionEnlace;
	}

	public String getSnActivo() {
		return snActivo;
	}

	public void setSnActivo(String snActivo) {
		this.snActivo = snActivo;
	}

	
}
