/**
 * 
 */
package com.vgmsistemas.vgmweb.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

@Entity
@Table( name = "v_listas_precio_articulos" )
@Where(clause = "pr_siva IS NOT NULL AND pr_final IS NOT NULL")
public class ListaPrecioDetalle implements Serializable {
	
	private static final SimpleDateFormat formatTimestampSqlite = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	public static final String BUSQUEDA_POR_ID = "ID";
	public static final String BUSQUEDA_POR_CODBARRA = "CB";
	public static final String BUSQUEDA_POR_IDEMPRESA = "IE";

	private static final long serialVersionUID = -3096719252632833846L;
	
	@EmbeddedId
	private PkListaPrecioDetalle id;	
	@Column(name = "pr_siva")
	private Double precioSinIva;	
	@Column(name = "pr_final")
	private Double precioConIva;
	@Column(name = "ca_lista")
	private Float cantidadPorLista;
	@Column(name = "ca_vendido")
	private Float cantidadVendida;	
	@Column(name = "sn_palm")
	private String snMovil;
	@Column(name = "fe_vigencia_desde")
	private Date fechaVigenciaDesde;
	@Column(name = "fe_vigencia_hasta")
	private Date fechaVigenciaHasta;	
	@Transient
	private String fechaVigenciaDesdeSqlite;	
	@Transient
	private String fechaVigenciaHastaSqlite;	
	@Column(name = "sn_pedido_web")
	private String snPedidoWeb;

	@ManyToOne()
	@JoinColumn(name = "id_articulos", referencedColumnName= "id_articulos", insertable = false, updatable = false)
	private Articulo articulo;
	/*@Column (name = "ca_articulo_desde")
	private Integer caArticuloDesde;
	@Column (name = "ca_articulo_hasta")
	private Integer caArticuloHasta;*/
	
	@Transient
	private Float taDtoProveedor;	
	@Transient
	private Float taDtoCliente;	
	@Transient
	private Float taDto;
	@Transient
	private Double precioConIvaYDtos;
	@Transient
	private String srcImagen;
	

	public ListaPrecioDetalle(){
		
	}

	public ListaPrecioDetalle(PkListaPrecioDetalle id, Double precioSinIva, Double precioConIva, Float cantidadPorLista,
			Float cantidadVendida, String snMovil, Date fechaVigenciaDesde, Date fechaVigenciaHasta,
			String fechaVigenciaDesdeSqlite, String fechaVigenciaHastaSqlite, String snPedidoWeb) {
		super();
		this.id = id;
		this.precioSinIva = precioSinIva;
		this.precioConIva = precioConIva;
		this.cantidadPorLista = cantidadPorLista;
		this.cantidadVendida = cantidadVendida;
		this.snMovil = snMovil;
		this.fechaVigenciaDesde = fechaVigenciaDesde;
		this.fechaVigenciaHasta = fechaVigenciaHasta;
		this.fechaVigenciaDesdeSqlite = fechaVigenciaDesdeSqlite;
		this.fechaVigenciaHastaSqlite = fechaVigenciaHastaSqlite;
		this.snPedidoWeb = snPedidoWeb;
	}

	/**
	 * @param precioSinIva the precio to set
	 */
	public void setPrecioSinIva(Double precioSinIva) {
		this.precioSinIva = precioSinIva;
	}
	
	/**
	 * @return the precioSinIva
	 */
	public Double getPrecioSinIva() {
		return this.precioSinIva;
	}

	/**
	 * @return the id
	 */
	public PkListaPrecioDetalle getId() {
		return this.id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(PkListaPrecioDetalle id) {
		this.id = id;
	}

	/**
	 * @return the precioConIva
	 */
	public Double getPrecioConIva() {
		return precioConIva;
	}

	/**
	 * @param precioConIva the precioConIva to set
	 */
	public void setPrecioConIva(Double precioConIva) {
		this.precioConIva = precioConIva;
	}

	/**
	 * @return the cantidadPorLista
	 */
	public Float getCantidadPorLista() {
		return cantidadPorLista;
	}

	/**
	 * @param cantidadPorLista the cantidadPorLista to set
	 */
	public void setCantidadPorLista(Float cantidadPorLista) {
		this.cantidadPorLista = cantidadPorLista;
	}

	/**
	 * @return the cantidadVendida
	 */
	public Float getCantidadVendida() {
		return cantidadVendida;
	}

	/**
	 * @param cantidadVendida the cantidadVendida to set
	 */
	public void setCantidadVendida(Float cantidadVendida) {
		this.cantidadVendida = cantidadVendida;
	}

	public String getSnMovil() {
		return snMovil;
	}

	public void setSnMovil(String snMovil) {
		this.snMovil = snMovil;
	}

	public Date getFechaVigenciaDesde() {
		return fechaVigenciaDesde;
	}

	public void setFechaVigenciaDesde(Date fechaVigenciaDesde) {
		if(fechaVigenciaDesde != null){
			fechaVigenciaDesdeSqlite = formatTimestampSqlite.format(fechaVigenciaDesde);
			this.fechaVigenciaDesde = fechaVigenciaDesde;	
		}
	}

	public Date getFechaVigenciaHasta() {
		return fechaVigenciaHasta;
	}

	public void setFechaVigenciaHasta(Date fechaVigenciaHasta) {
		if(fechaVigenciaHasta != null){
			fechaVigenciaHastaSqlite = formatTimestampSqlite.format(fechaVigenciaHasta);
			this.fechaVigenciaHasta = fechaVigenciaHasta;	
		}
	}

	public String getFechaVigenciaDesdeSqlite() {
		return fechaVigenciaDesdeSqlite;
	}

	public void setFechaVigenciaDesdeSqlite(String fechaVigenciaDesdeSqlite) {
		this.fechaVigenciaDesdeSqlite = fechaVigenciaDesdeSqlite;
	}

	public String getFechaVigenciaHastaSqlite() {
		return fechaVigenciaHastaSqlite;
	}

	public void setFechaVigenciaHastaSqlite(String fechaVigenciaHastaSqlite) {
		this.fechaVigenciaHastaSqlite = fechaVigenciaHastaSqlite;
	}

	public String getSnPedidoWeb() {
		return snPedidoWeb;
	}

	public void setSnPedidoWeb(String snPedidoWeb) {
		this.snPedidoWeb = snPedidoWeb;
	}

	/*public Integer getCaArticuloDesde() {
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
	}*/

	
	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
	
	public Float getTaDtoProveedor() {
		return taDtoProveedor;
	}

	public void setTaDtoProveedor(Float taDtoProveedor) {
		this.taDtoProveedor = taDtoProveedor;
	}

	public Float getTaDtoCliente() {
		return taDtoCliente;
	}

	public void setTaDtoCliente(Float taDtoCliente) {
		this.taDtoCliente = taDtoCliente;
	}

	public Float getTaDto() {
		return taDto;
	}

	public void setTaDto(Float taDto) {
		this.taDto = taDto;
	}

	public Double getPrecioConIvaYDtos() {
		return precioConIvaYDtos;
	}

	public void setPrecioConIvaYDtos(Double precioConIvaYDtos) {
		this.precioConIvaYDtos = precioConIvaYDtos;
	}
	
	public String getSrcImagen() {
		return srcImagen;
	}

	public void setSrcImagen(String srcImagen) {
		this.srcImagen = srcImagen;
	}

}
