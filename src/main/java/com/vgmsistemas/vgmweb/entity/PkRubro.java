/**
 * 
 */
package com.vgmsistemas.vgmweb.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author pablo
 *
 */
@Embeddable
public class PkRubro implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6509685569523286853L;
	 
	@Column(name = "id_segmento")
	private long _idRubro;
	@Column(name = "id_negocio")
	private long _idNegocio;
	/**
	 * @return the idSegmento
	 */
	public long getIdRubro() {
		return this._idRubro;
	}
	/**
	 * @param idSegmento the idSegmento to set
	 */
	public void setIdRubro(long idSegmento) {
		this._idRubro = idSegmento;
	}
	/**
	 * @return the idNegocio
	 */
	public long getIdNegocio() {
		return this._idNegocio;
	}
	/**
	 * @param idNegocio the idNegocio to set
	 */
	public void setIdNegocio(long idNegocio) {
		this._idNegocio = idNegocio;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (_idNegocio ^ (_idNegocio >>> 32));
		result = prime * result + (int) (_idRubro ^ (_idRubro >>> 32));
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
		if (!(obj instanceof PkRubro)) {
			return false;
		}
		PkRubro other = (PkRubro) obj;
		if (_idNegocio != other._idNegocio) {
			return false;
		}
		if (_idRubro != other._idRubro) {
			return false;
		}
		return true;
	}

}
