/**
 * 
 */
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

/**
 * @author pablo
 *
 */
@Entity
@Table( name = "cond_DGR" )
public class CondicionRenta implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6987230094140976511L;
	@Id
	@GeneratedValue
	@Column(name = "ti_tadgr")
	private long id;
	@Column(name = "de_tadgr")
	private String descripcion;
	@Column(name = "ta_porcentaje")
	private Float tasaDgr;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_provincia", insertable = false, updatable = false)
	private Provincia provincia;
	@Column(name = "ti_calculo")
	private Long tipoCalculo;
	@Column(name = "pr_min_imp_dgr")
	private Float montoMinimoImpuestoDgr;
	/*@Column(name = "sn_aplica_a_nc")
	private String snAplicaANc;*/

	public static final long TIPO_CALCULO_CORRIENTES 		= 1;
	public static final long TIPO_CALCULO_CHACO 			= 2;
	public static final long TIPO_CALCULO_MISIONES 	= 3;
	public static final long TIPO_CALCULO_FORMOSA = 4;
	public static final long TIPO_CALCULO_POR_ARTICULO = 5;
	
	/**
	 * @return the id
	 */
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
	 * @return the tasaDgr
	 */
	public Float getTasaDgr() {
		return this.tasaDgr;
	}
	/**
	 * @param tasaDgr the tasaDgr to set
	 */
	public void setTasaDgr(Float tasaDgr) {
		this.tasaDgr = tasaDgr;
	}
	/**
	 * @return the provincia
	 */
	public Provincia getProvincia() {
		return provincia;
	}
	/**
	 * @param provincia the provincia to set
	 */
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	
	public Long getTipoCalculo() {
		return tipoCalculo;
	}
	public void setTipoCalculo(Long tipoCalculo) {
		this.tipoCalculo = tipoCalculo;
	}
	public Float getMontoMinimoImpuestoDgr() {
		return montoMinimoImpuestoDgr;
	}
	public void setMontoMinimoImpuestoDgr(Float montoMinimoImpuestoDgr) {
		this.montoMinimoImpuestoDgr = montoMinimoImpuestoDgr;
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
		if (!(obj instanceof CondicionRenta)) {
			return false;
		}
		CondicionRenta other = (CondicionRenta) obj;
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
		if (provincia == null) {
			if (other.provincia != null) {
				return false;
			}
		} else if (!provincia.equals(other.provincia)) {
			return false;
		}
		if (tasaDgr == null) {
			if (other.tasaDgr != null) {
				return false;
			}
		} else if (tasaDgr.compareTo(other.tasaDgr)!= 0) {
			return false;
		}
		return true;
	}
	
	
	/*public String getSnAplicaANc() {
		return snAplicaANc;
	}
	public void setSnAplicaANc(String snAplicaANc) {
		this.snAplicaANc = snAplicaANc;
	}*/
	
		
}
