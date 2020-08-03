package com.vgmsistemas.vgmweb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PkGrupoClientesDetalle implements Serializable{

	private static final long serialVersionUID = 5966053595981650796L;
	@Column(name = "id_grupo_clie")
	int idGrupoClie;
	@Column(name = "id_grupo_clie_detalle")
	int idGrupoClieDetalle;
	
	public int getIdGrupoClie() {
		return idGrupoClie;
	}
	public void setIdGrupoClie(int idGrupoClie) {
		this.idGrupoClie = idGrupoClie;
	}
	public int getIdGrupoClieDetalle() {
		return idGrupoClieDetalle;
	}
	public void setIdGrupoClieDetalle(int idGrupoClieDetalle) {
		this.idGrupoClieDetalle = idGrupoClieDetalle;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idGrupoClie ^ (idGrupoClie >>> 32));
		result = prime * result + (int) (idGrupoClieDetalle ^ (idGrupoClieDetalle >>> 32));
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof PkSubrubro)) {
			return false;
		}
		PkGrupoClientesDetalle other = (PkGrupoClientesDetalle) obj;
		if (idGrupoClie != other.idGrupoClie) {
			return false;
		}
		if (idGrupoClieDetalle != other.idGrupoClieDetalle) {
			return false;
		}
		return true;
	}
}
