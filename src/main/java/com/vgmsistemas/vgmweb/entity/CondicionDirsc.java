package com.vgmsistemas.vgmweb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table( name = "cond_dirsc" )
public class CondicionDirsc implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5496073849234220845L;
	@Id
	@GeneratedValue
	@Column(name = "ti_dirsc")
	private String id;
	@Column(name = "de_dirsc")
	private String descripcion;
	@Column(name = "ta_impuesto")
	private float tasaImpuesto;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_postal", insertable = false, updatable = false)
	private Localidad localidad;
	@ManyToOne
	@JoinColumn(name="id_provincia", insertable = false, updatable = false)
	private Provincia provincia;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
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
	 * @return the tasaImpuesto
	 */
	public float getTasaImpuesto() {
		return tasaImpuesto;
	}
	/**
	 * @param tasaImpuesto the tasaImpuesto to set
	 */
	public void setTasaImpuesto(float tasaImpuesto) {
		this.tasaImpuesto = tasaImpuesto;
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
		if (!(obj instanceof CondicionDirsc)) {
			return false;
		}
		CondicionDirsc other = (CondicionDirsc) obj;
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
		if (localidad == null) {
			if (other.localidad != null) {
				return false;
			}
		} else if (!localidad.equals(other.localidad)) {
			return false;
		}
		if (Float.floatToIntBits(tasaImpuesto) != Float
				.floatToIntBits(other.tasaImpuesto)) {
			return false;
		}
		return true;
	}
	public Localidad getLocalidad() {
		return localidad;
	}
	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}
	public Provincia getProvincia() {
		return provincia;
	}
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	
	
	
}
