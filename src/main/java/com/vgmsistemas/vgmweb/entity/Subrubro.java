/**
 * 
 */
package com.vgmsistemas.vgmweb.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author pablo
 *
 */
/**
 * @author Fede
 *
 */
@Entity
@Table( name = "subrubro" )
public class Subrubro implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2788302087750143479L;
	@EmbeddedId
	private PkSubrubro id;
	
	@Column(name = "de_subrubro")
	private String descripcion;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
        @JoinColumn(
            name = "id_segmento",
            referencedColumnName = "id_segmento", insertable = false, updatable = false),
        @JoinColumn(
            name = "id_negocio",
            referencedColumnName = "id_negocio", insertable = false, updatable = false)
    })
	private Rubro rubro;
	@Column(name = "id_doc")
	private String documento;
	@Column(name = "id_ptovta")
	private Long puntoVenta;
	
	
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public Long getPuntoVenta() {
		return puntoVenta;
	}
	public void setPuntoVenta(Long puntoVenta) {
		this.puntoVenta = puntoVenta;
	}
	/**
	 * @return the id
	 */
	public PkSubrubro getId() {
		return this.id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(PkSubrubro id) {
		this.id = id;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return this.descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return the rubro
	 */
	public Rubro getRubro() {
		return this.rubro;
	}
	/**
	 * @param rubro the rubro to set
	 */
	public void setRubro(Rubro rubro) {
		this.rubro = rubro;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((rubro == null) ? 0 : rubro.hashCode());
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
		if (!(obj instanceof Subrubro)) {
			return false;
		}
		Subrubro other = (Subrubro) obj;
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
		if (rubro == null) {
			if (other.rubro != null) {
				return false;
			}
		} else if (!rubro.equals(other.rubro)) {
			return false;
		}
		return true;
	}
	
}
