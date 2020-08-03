package com.vgmsistemas.vgmweb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PkAccionesComDetalle implements Serializable{
	private static final long serialVersionUID = 5966053595981650797L;
	@Column(name = "id_acciones_com")
	int idAccionesCom;
	@Column(name = "id_acciones_com_detalle")
	int idAccionesComDetalle;
	
	public int getIdAccionesCom() {
		return idAccionesCom;
	}
	public void setIdAccionesCom(int idAccionesCom) {
		this.idAccionesCom = idAccionesCom;
	}
	public int getIdAccionesComDetalle() {
		return idAccionesComDetalle;
	}
	public void setIdAccionesComDetalle(int idAccionesComDetalle) {
		this.idAccionesComDetalle = idAccionesComDetalle;
	}	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idAccionesCom ^ (idAccionesCom >>> 32));
		result = prime * result + (int) (idAccionesComDetalle ^ (idAccionesComDetalle >>> 32));
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
		PkAccionesComDetalle other = (PkAccionesComDetalle) obj;
		if (idAccionesCom != other.idAccionesCom) {
			return false;
		}
		if (idAccionesComDetalle != other.idAccionesComDetalle) {
			return false;
		}
		return true;
	}
}
