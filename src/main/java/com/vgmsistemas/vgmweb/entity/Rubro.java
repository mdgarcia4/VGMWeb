/**
 * 
 */
package com.vgmsistemas.vgmweb.entity;
import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * @author pablo
 *
 */
/*@XmlRootElement(name="Rubro")
@XmlAccessorType(XmlAccessType.FIELD)*/
@Entity
@Table( name = "segmento" )
public class Rubro implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7046464468790325830L;
	@EmbeddedId
	private PkRubro id;
	
	@Column(name = "de_segmento")
	private String descripcion;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_negocio", referencedColumnName = "id_negocio", insertable = false, updatable = false)
	private Negocio negocio;
	
	@OneToMany(mappedBy = "rubro")
	private Set<Subrubro> subrubros;
	
	public PkRubro getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(PkRubro id) {
		this.id = id;
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
	/**
	 * @return the negocio
	 */
	public Negocio getNegocio() {
		return negocio;
	}
	
	
	public Set<Subrubro> getSubrubros() {
		return subrubros;
	}
	public void setSubrubros(Set<Subrubro> subrubros) {
		this.subrubros = subrubros;
	}
	/**
	 * @param negocio the negocio to set
	 */
	public void setNegocio(Negocio negocio) {
		this.negocio = negocio;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (!(obj instanceof Rubro)) {
			return false;
		}
		Rubro other = (Rubro) obj;
		if (descripcion == null) {
			if (other.descripcion != null) {
				return false;
			}
		} else if (!descripcion.equals(other.descripcion)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (negocio == null) {
			if (other.negocio != null) {
				return false;
			}
		} else if (!negocio.equals(other.negocio)) {
			return false;
		}
		return true;
	}
	
}
