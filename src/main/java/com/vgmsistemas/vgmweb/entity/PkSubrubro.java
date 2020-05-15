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
public class PkSubrubro implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3497239050731647740L;
	@Column(name = "id_subrubro")
	private long idSubrubro;
	@Column(name = "id_segmento")
	private long idRubro;
	@Column(name = "id_negocio")
	private long idNegocio;
	/**
	 * @return the idSubrubro
	 */
	public long getIdSubrubro() {
		return idSubrubro;
	}
	/**
	 * @param idSubrubro the idSubrubro to set
	 */
	public void setIdSubrubro(long idSubrubro) {
		this.idSubrubro = idSubrubro;
	}
	/**
	 * @return the idRubro
	 */
	public long getIdRubro() {
		return idRubro;
	}
	/**
	 * @param idRubro the idRubro to set
	 */
	public void setIdRubro(long idRubro) {
		this.idRubro = idRubro;
	}
	/**
	 * @return the idNegocio
	 */
	public long getIdNegocio() {
		return idNegocio;
	}
	/**
	 * @param idNegocio the idNegocio to set
	 */
	public void setIdNegocio(long idNegocio) {
		this.idNegocio = idNegocio;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idNegocio ^ (idNegocio >>> 32));
		result = prime * result + (int) (idRubro ^ (idRubro >>> 32));
		result = prime * result + (int) (idSubrubro ^ (idSubrubro >>> 32));
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
		if (!(obj instanceof PkSubrubro)) {
			return false;
		}
		PkSubrubro other = (PkSubrubro) obj;
		if (idNegocio != other.idNegocio) {
			return false;
		}
		if (idRubro != other.idRubro) {
			return false;
		}
		if (idSubrubro != other.idSubrubro) {
			return false;
		}
		return true;
	}
	
}
