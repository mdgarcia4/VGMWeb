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

@Entity
@Table( name = "repartidor" )
//@PrimaryKeyJoinColumn(name="id_repartidor",referencedColumnName="id_legajo")
public class Repartidor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7046071303736401170L;
	
	@Id
	@GeneratedValue
	@Column(name = "id_repartidor", insertable = false, updatable = false)
	protected Integer id;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="sn_movil")
	private String snMovil;
	
	@Column(name="ti_repartidor")
	private String tiRepartidor;

	/**
	 * @return the snMovil
	 */
	public String getSnMovil() {
		return snMovil;
	}

	/**
	 * @param snMovil the snMovil to set
	 */
	public void setSnMovil(String snMovil) {
		this.snMovil = snMovil;
	}

	public String getTiRepartidor() {
		return tiRepartidor;
	}

	public void setTiRepartidor(String tiRepartidor) {
		this.tiRepartidor = tiRepartidor;
	}
	
}
