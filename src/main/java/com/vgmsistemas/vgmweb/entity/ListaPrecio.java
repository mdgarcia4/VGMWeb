package com.vgmsistemas.vgmweb.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table( name = "listas" )
public class ListaPrecio implements Serializable{
	public final static int TIPO_LISTA_BASE = 1;
	public final static int TIPO_LISTA_COMBOS_COMUNES = 3;
	public static final int TIPO_LISTA_LIBRE = 5;
	public static final int TIPO_LISTA_BASE_X_ART_LIBRE = 7;
	public final static int TIPO_LISTA_COMBOS_ESPECIALES = 8;
	public static final int TIPO_LISTA_BASE_X_CANTIDAD = 9;
	
	private static final long serialVersionUID = -2608483363578337292L;
	
	@Id
	@GeneratedValue
	@Column(name = "id_lista")
	private Long id;
	
	@Column(name = "de_lista")
	private String descripcion;
	
	@Transient
	private List<ListaPrecioDetalle> detalles = new ArrayList<ListaPrecioDetalle>();	
	
	@Column(name = "ti_lista")
	private Long tipoLista;
	
	@Column(name = "id_lista_base")
	private Long listaBase;
	
	@Column(name = "id_origenprecio")
	private String idOrigenPrecio;
	
	@Column(name = "sn_pedido_web")
	private String snPedidoWeb;
	
	@Column(name = "sn_seleccionable_movil")
	private String snSeleccionableEnMovil;
	
	@Column(name = "sn_palm")
	private String snPalm;
	
	@Column(name = "fh_inicio")
	private Calendar fhInicio;
	
	@Column(name = "fh_vencimiento")
	private Calendar fhVencimiento;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
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
	 * @return the detalles
	 */
	public List<ListaPrecioDetalle> getDetalles() {
		return this.detalles;
	}
	/**
	 * @param detalles the detalles to set
	 */
	public void setDetalles(List<ListaPrecioDetalle> detalles) {
		this.detalles = detalles;
	}
	
	@Override
	public String toString() {
		return this.descripcion;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
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
		if (!(obj instanceof ListaPrecio)) {
			return false;
		}
		ListaPrecio other = (ListaPrecio) obj;
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
	/**
	 * @return the tipoLista
	 */
	public Long getTipoLista() {
		return tipoLista;
	}
	/**
	 * @param tipoLista the tipoLista to set
	 */
	public void setTipoLista(Long tipoLista) {
		this.tipoLista = tipoLista;
	}
	public Long getListaBase() {
		return listaBase;
	}
	public void setListaBase(Long listaBase) {
		this.listaBase = listaBase;
	}

	public String getIdOrigenPrecio() {
		return idOrigenPrecio;
	}
	public void setIdOrigenPrecio(String idOrigenPrecio) {
		this.idOrigenPrecio = idOrigenPrecio;
	}
	public String getSnPedidoWeb() {
		return snPedidoWeb;
	}
	public void setSnPedidoWeb(String snPedidoWeb) {
		this.snPedidoWeb = snPedidoWeb;
	}
	public String getSnSeleccionableEnMovil() {
		return snSeleccionableEnMovil;
	}
	public void setSnSeleccionableEnMovil(String snSeleccionableMovil) {
		this.snSeleccionableEnMovil = snSeleccionableMovil;
	}
	public String getSnPalm() {
		return snPalm;
	}
	public void setSnPalm(String snPalm) {
		this.snPalm = snPalm;
	}
	/**
	 * @return the fhInicio
	 */
	public Calendar getFhInicio() {
		return fhInicio;
	}
	/**
	 * @param serverDate the fhInicio to set
	 */
	public void setFhInicio(Calendar serverDate) {
		this.fhInicio = serverDate;
	}
	/**
	 * @return the fhVencimiento
	 */
	public Calendar getFhVencimiento() {
		return fhVencimiento;
	}
	/**
	 * @param fhVencimiento the fhVencimiento to set
	 */
	public void setFhVencimiento(Calendar fhVencimiento) {
		this.fhVencimiento = fhVencimiento;
	}
}
