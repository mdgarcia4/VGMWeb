package com.vgmsistemas.vgmweb.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonView;


@Entity
@Table( name = "v_clientes_comercios" )
public class Cliente{
	
	@Id
	private PkCliente id;
	
	@Column(name = "de_organizacion")
	private String razonSocial;
	
	@Column(name = "de_direccion")
	private String domicilio;
	
	@Column(name = "id_cuit")
	private String cuit;
	
	@Column(name = "nu_telefono")
	private String telefono;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ti_contribuyente", insertable = false, updatable = false)
	private CategoriaFiscal categoriaFiscal;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ti_tadgr", insertable = false, updatable = false)
	private CondicionRenta condicionRenta;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_condvta", insertable = false, updatable = false)
	private CondicionVenta condicionVenta;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_lista", insertable = false, updatable = false)
	private ListaPrecio listaPrecio;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_repartidor", insertable = false, updatable = false)
	private Repartidor repartidor;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_postal", insertable = false, updatable = false)
	private Localidad localidad;
	
	@Column(name = "nu_latitud")
	private Double latitud;
	
	@Column(name = "nu_longitud")
	private Double longitud;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ti_dirsc", insertable = false, updatable = false)
	private CondicionDirsc condicionDirsc;
	
	@Column(name = "pr_limitecredito")
	private Float limiteCredito;
	
	@Column(name = "limite_disponibilidad")
	private String limiteDisponibilidad;
	
	@Column(name = "ta_dto_clie")
	private Float tasaDescuentoCliente;
	
	@Column(name = "de_rubro")
	private String descripcionRubro;
	
	@Column(name = "pr_venta_acum")
	private Float totalVentaAcumulada;
	
	@Transient
	private String contrasenaUltraweb="";
	
	@Column(name = "id_vendedor")
	private Integer idVendedor;
	
	@Column(name="email")
	private String email;

	/**
	 * @return the razonSocial
	 */
	public String getRazonSocial() {
		return this.razonSocial;
	}

	/**
	 * @param razonSocial
	 *            the razonSocial to set
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	/**
	 * @return the domicilio
	 */
	public String getDomicilio() {
		return this.domicilio;
	}

	/**
	 * @param domicilio
	 *            the domicilio to set
	 */
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	/**
	 * @return the condicionIva
	 */
	public CategoriaFiscal getCategoriaFiscal() {
		return this.categoriaFiscal;
	}

	/**
	 * @param condicionIva
	 *            the condicionIva to set
	 */
	public void setCategoriaFiscal(CategoriaFiscal categoriaFiscal) {
		this.categoriaFiscal = categoriaFiscal;
	}

	/**
	 * @return the condicionRenta
	 */
	public CondicionRenta getCondicionRenta() {
		return this.condicionRenta;
	}

	/**
	 * @param condicionRenta
	 *            the condicionRenta to set
	 */
	public void setCondicionRenta(CondicionRenta condicionRenta) {
		this.condicionRenta = condicionRenta;
	}

	/**
	 * @return the cuit
	 */
	public String getCuit() {
		return this.cuit;
	}

	/**
	 * @param cuit
	 *            the cuit to set
	 */
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	/**
	 * @return the condicionVenta
	 */
	public CondicionVenta getCondicionVenta() {
		return this.condicionVenta;
	}

	/**
	 * @param condicionVenta
	 *            the condicionVenta to set
	 */
	public void setCondicionVenta(CondicionVenta condicionVenta) {
		this.condicionVenta = condicionVenta;
	}

	/**
	 * @return the listaPrecio
	 */
	public ListaPrecio getListaPrecio() {
		return this.listaPrecio;
	}

	/**
	 * @param listaPrecio
	 *            the listaPrecio to set
	 */
	public void setListaPrecio(ListaPrecio listaPrecio) {
		this.listaPrecio = listaPrecio;
	}

	/**
	 * @return the repartidor
	 */
	public Repartidor getRepartidor() {
		return this.repartidor;
	}

	/**
	 * @param repartidor
	 *            the repartidor to set
	 */
	public void setRepartidor(Repartidor repartidor) {
		this.repartidor = repartidor;
	}

	/**
	 * @return the localidad
	 */
	public Localidad getLocalidad() {
		return this.localidad;
	}

	/**
	 * @param localidad
	 *            the localidad to set
	 */
	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	/**
	 * @param telefono
	 *            the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @return the id
	 */
	public PkCliente getId() {
		return this.id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(PkCliente id) {
		this.id = id;
	}

	/**
	 * @return the latitud
	 */
	public Double getLatitud() {
		return this.latitud;
	}

	/**
	 * @param latitud the latitud to set
	 */
	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	/**
	 * @return the longitud
	 */
	public Double getLongitud() {
		return this.longitud;
	}

	/**
	 * @param longitud the longitud to set
	 */
	public void setLongitud(Double longitud) {
		this.longitud = longitud;
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
		if (!(obj instanceof Cliente)) {
			return false;
		}
		Cliente other = (Cliente) obj;
		if (categoriaFiscal == null) {
			if (other.categoriaFiscal != null) {
				return false;
			}
		} else if (!categoriaFiscal.equals(other.categoriaFiscal)) {
			return false;
		}
		if (condicionRenta == null) {
			if (other.condicionRenta != null) {
				return false;
			}
		} else if (!condicionRenta.equals(other.condicionRenta)) {
			return false;
		}
		if (condicionVenta == null) {
			if (other.condicionVenta != null) {
				return false;
			}
		} else if (!condicionVenta.equals(other.condicionVenta)) {
			return false;
		}
		if (cuit == null) {
			if (other.cuit != null) {
				return false;
			}
		} else if (!cuit.equals(other.cuit)) {
			return false;
		}
		if (domicilio == null) {
			if (other.domicilio != null) {
				return false;
			}
		} else if (!domicilio.equals(other.domicilio)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (Double.doubleToLongBits(latitud) != Double
				.doubleToLongBits(other.latitud)) {
			return false;
		}
		if (listaPrecio == null) {
			if (other.listaPrecio != null) {
				return false;
			}
		} else if (!listaPrecio.equals(other.listaPrecio)) {
			return false;
		}
		if (localidad == null) {
			if (other.localidad != null) {
				return false;
			}
		} else if (!localidad.equals(other.localidad)) {
			return false;
		}
		if (Double.doubleToLongBits(longitud) != Double
				.doubleToLongBits(other.longitud)) {
			return false;
		}
		if (razonSocial == null) {
			if (other.razonSocial != null) {
				return false;
			}
		} else if (!razonSocial.equals(other.razonSocial)) {
			return false;
		}
		if (telefono == null) {
			if (other.telefono != null) {
				return false;
			}
		} else if (!telefono.equals(other.telefono)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the condicionDirsc
	 */
	public CondicionDirsc getCondicionDirsc() {
		return condicionDirsc;
	}

	/**
	 * @param condicionDirsc the condicionDirsc to set
	 */
	public void setCondicionDirsc(CondicionDirsc condicionDirsc) {
		this.condicionDirsc = condicionDirsc;
	}

	/**
	 * @return the limiteCredito
	 */
	public Float getLimiteCredito() {
		return limiteCredito;
	}

	/**
	 * @param limiteCredito the limiteCredito to set
	 */
	public void setLimiteCredito(Float limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	public void setLimiteDisponibilidad(String limiteDisponibilidad) {
		this.limiteDisponibilidad = limiteDisponibilidad;
		//this.limiteDisponibilidadSqlite = limiteDisponibilidad.toString();
	}

	public String getLimiteDisponibilidad() {
		if(limiteDisponibilidad != null ){
			return limiteDisponibilidad;
		} else {
			return "0";
		}
		
	}

	public Float getTasaDescuentoCliente() {
		return tasaDescuentoCliente;
	}

	public void setTasaDescuentoCliente(Float tasaDescuentoCliente) {
		this.tasaDescuentoCliente = tasaDescuentoCliente;
	}

	public String getDescripcionRubro() {
		return descripcionRubro;
	}

	public void setDescripcionRubro(String descripcionRubro) {
		this.descripcionRubro = descripcionRubro;
	}

	public Float getTotalVentaAcumulada() {
		return totalVentaAcumulada;
	}

	public void setTotalVentaAcumulada(Float totalVentaAcumulada) {
		this.totalVentaAcumulada = totalVentaAcumulada;
	}


	public String getContrasenaUltraweb() {
		return contrasenaUltraweb;
	}

	public void setContrasenaUltraweb(String contrasenaUltraweb) {
		this.contrasenaUltraweb = contrasenaUltraweb;
	}

	public Integer getIdVendedor() {
		return idVendedor;
	}

	public void setIdVendedor(Integer idVendedor) {
		this.idVendedor = idVendedor;
	}

	/*public String getLimiteDisponibilidadSqlite() {
		return limiteDisponibilidadSqlite;
	}

	public void setLimiteDisponibilidadSqlite(String limiteDisponibilidadSqlite) {
		this.limiteDisponibilidadSqlite = limiteDisponibilidadSqlite;
	}	*/
	
}
