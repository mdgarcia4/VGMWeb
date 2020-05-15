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
/*@XmlRootElement(name="Marca")
@XmlAccessorType(XmlAccessType.NONE)*/
@Entity
@Table( name = "linea" )
public class Marca implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3593807007462447749L;
	//@XmlElement(name="idMarca")
	@Id
	@GeneratedValue
	@Column(name = "id_linea")	
	private long id;
	//@XmlElement(name="descripcionMarca", required=false)
	@Column(name = "de_linea")
	private String descripcion;
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
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
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
		
}
