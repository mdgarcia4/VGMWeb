/**
 * 
 */
package com.vgmsistemas.vgmweb.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PkRolesUsuarios implements Serializable{

		 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "id_usuario")
	private long idUsuario;
	@Column(name = "id_rol")
	private long idRol;
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idUsuario ^ (idUsuario >>> 32));
		result = prime * result + (int) (idRol ^ (idRol >>> 32));
		return result;
	}
	public long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public long getIdRol() {
		return idRol;
	}
	public void setIdRol(long idRol) {
		this.idRol = idRol;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof PkRolesUsuarios)) {
			return false;
		}
		PkRolesUsuarios other = (PkRolesUsuarios) obj;
		if (idUsuario != other.idUsuario) {
			return false;
		}
		if (idRol != other.idRol) {
			return false;
		}
		return true;
	}

}
