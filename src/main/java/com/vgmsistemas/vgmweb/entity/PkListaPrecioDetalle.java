/**
 * 
 */
package com.vgmsistemas.vgmweb.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * @author pablo
 *
 */
@Embeddable
public class PkListaPrecioDetalle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "id_lista")
	private Long idLista;
	
	@Column(name = "id_articulos")
	private Long idArticulo;
	
	@Column(name = "id_sucursal")
	private Long idSucursal;
	
	@Column(name = "ca_articulo_desde")
	private Integer caArticuloDesde;
	
	@Column(name = "ca_articulo_hasta")
	private Integer caArticuloHasta;
	
	/**
	 * @return the idLista
	 */
	public Long getIdLista() {
		return this.idLista;
	}
	/**
	 * @param idLista the idLista to set
	 */
	public void setIdLista(Long idLista) {
		this.idLista = idLista;
	}
	/**
	 * @return the idArticulo
	 */
	public Long getIdArticulo() {
		return this.idArticulo;
	}
	/**
	 * @param idArticulo the idArticulo to set
	 */
	public void setIdArticulo(Long idArticulo) {
		this.idArticulo = idArticulo;
	}
	public Long getIdSucursal() {
		return idSucursal;
	}
	public void setIdSucursal(Long idSucursal) {
		this.idSucursal = idSucursal;
	}
	
	public Integer getCaArticuloDesde() {
		return caArticuloDesde;
	}
	public void setCaArticuloDesde(Integer caArticuloDesde) {
		this.caArticuloDesde = caArticuloDesde;
	}
	public Integer getCaArticuloHasta() {
		return caArticuloHasta;
	}
	public void setCaArticuloHasta(Integer caArticuloHasta) {
		this.caArticuloHasta = caArticuloHasta;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idArticulo == null) ? 0 : idArticulo.hashCode());
		result = prime * result + ((idLista == null) ? 0 : idLista.hashCode());
		result = prime * result
				+ ((idSucursal == null) ? 0 : idSucursal.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PkListaPrecioDetalle other = (PkListaPrecioDetalle) obj;
		if (idArticulo == null) {
			if (other.idArticulo != null)
				return false;
		} else if (!idArticulo.equals(other.idArticulo))
			return false;
		if (idLista == null) {
			if (other.idLista != null)
				return false;
		} else if (!idLista.equals(other.idLista))
			return false;
		if (idSucursal == null) {
			if (other.idSucursal != null)
				return false;
		} else if (!idSucursal.equals(other.idSucursal))
			return false;
		return true;
	}
	
}
