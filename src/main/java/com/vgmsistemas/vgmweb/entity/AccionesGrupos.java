package com.vgmsistemas.vgmweb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "acciones_grupos")
public class AccionesGrupos {
	@Id
	@Column(name = "id_acciones_grupos")
	private int idAccionesGrupos;
	@Column(name = "id_acciones_com")
	private int idAccionesCom;
	@Column(name = "id_grupo_clie")
	private int idGrupoClie;
	
	public int getIdAccionesGrupos() {
		return idAccionesGrupos;
	}
	public void setIdAccionesGrupos(int idAccionesGrupos) {
		this.idAccionesGrupos = idAccionesGrupos;
	}
	public int getIdAccionesCom() {
		return idAccionesCom;
	}
	public void setIdAccionesCom(int idAccionesCom) {
		this.idAccionesCom = idAccionesCom;
	}
	public int getIdGrupoClie() {
		return idGrupoClie;
	}
	public void setIdGrupoClie(int idGrupoClie) {
		this.idGrupoClie = idGrupoClie;
	}
}
