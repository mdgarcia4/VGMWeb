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
@Entity
@Table( name = "cond_Iva" )
public class CategoriaFiscal implements Serializable{

	public static final String INSCRIPTO = "IN";
	public static final String CONSUMIDOR_FINAL = "CF";
	public static final String NO_CATEGORIZADO = "NC";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3172579163578710285L;
	@Id
	@GeneratedValue
	@Column(name = "ti_contribuyente")
	private String id;
	@Column(name = "de_contribuyente")
	private String descripcion;
	//@Column(name = "sn_bn")
	//private String snBn;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return this.id;
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
		return this.descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	/*public String getSnBn() {
		return snBn;
	}
	public void setSnBn(String snBn) {
		this.snBn = snBn;
	}*/
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
		if (!(obj instanceof CategoriaFiscal)) {
			return false;
		}
		CategoriaFiscal other = (CategoriaFiscal) obj;
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
		return true;
	}
	
	
	
}
