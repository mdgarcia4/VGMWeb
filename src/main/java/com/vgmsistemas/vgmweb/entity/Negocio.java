/**
 * 
 */
package com.vgmsistemas.vgmweb.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author pablo
 *
 */

/*@XmlRootElement(name="Negocio")
@XmlAccessorType(XmlAccessType.FIELD)*/
@Entity
@Table( name = "negocio" )
public class Negocio implements Serializable{

	private static final long serialVersionUID = -6096220149840555434L;
	//@XmlElement(name = "idNegocio")
	@Id
	@GeneratedValue
	@Column(name = "id_negocio")
	private long id;
	//@XmlElement(name = "descripcionNegocio", required=false)
	@Column(name = "de_negocio")
	private String descripcion;
	
	/**
	 * @return the id
	 */
	//@XmlElement(name = "idNegocio")
	public long getId() {
		return this.id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the descripcion
	 */
	//@XmlElement(name = "descripcionNegocio", required=false)
	public String getDescripcion() {
		return this.descripcion;
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
		if (!(obj instanceof Negocio)) {
			return false;
		}
		Negocio other = (Negocio) obj;
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
		return true;
	}
	
}
