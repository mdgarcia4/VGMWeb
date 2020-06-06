/**
 * 
 */
package com.vgmsistemas.vgmweb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/*@XmlRootElement(name = "CondicionVenta")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "CondicionVenta", propOrder = { "id", "descripcion",
		"cuentaCorriente", "tasaDescuento" })*/
@Entity
@Table( name = "condvta" )
public class CondicionVenta {

	//@XmlElement(required = true)
	@Id
	@GeneratedValue
	@Column(name = "id_condvta")
	private String id;
	//@XmlElement(required = true)
	@Column(name = "de_convta")
	private String descripcion;
	//@XmlElement(required = true)
	@Column(name = "sn_controlfiado")
	private String cuentaCorriente;
	@Column(name = "nu_diasatraso")
	private Integer diasAtraso;
	//@XmlElement(required = true)
	@Column(name = "ta_var1")
	private float tasaDescuento;
	@Column(name = "nu_cuotas")
	private Integer nuCuotas;

	/**
	 * Gets the value of the id property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the value of the id property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setId(String value) {
		this.id = value;
	}

	/**
	 * Gets the value of the descripcion property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Sets the value of the descripcion property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDescripcion(String value) {
		this.descripcion = value;
	}

	/**
	 * Gets the value of the cuentaCorriente property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCuentaCorriente() {
		return cuentaCorriente;
	}

	/**
	 * Sets the value of the cuentaCorriente property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCuentaCorriente(String value) {
		this.cuentaCorriente = value;
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
		if (!(obj instanceof CondicionVenta)) {
			return false;
		}
		CondicionVenta other = (CondicionVenta) obj;
		if (cuentaCorriente == null) {
			if (other.cuentaCorriente != null) {
				return false;
			}
		} else if (!cuentaCorriente.equals(other.cuentaCorriente)) {
			return false;
		}
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

	/**
	 * @return the diasAtraso
	 */
	public Integer getDiasAtraso() {
		return diasAtraso;
	}

	/**
	 * @param diasAtraso the diasAtraso to set
	 */
	public void setDiasAtraso(Integer diasAtraso) {
		this.diasAtraso = diasAtraso;
	}

	public float getTasaDescuento() {
		return tasaDescuento;
	}

	public void setTasaDescuento(float tasaDescuento) {
		this.tasaDescuento = tasaDescuento;
	}

	public Integer getNuCuotas() {
		return nuCuotas;
	}

	public void setNuCuotas(Integer nuCuotas) {
		this.nuCuotas = nuCuotas;
	}
	
	
}