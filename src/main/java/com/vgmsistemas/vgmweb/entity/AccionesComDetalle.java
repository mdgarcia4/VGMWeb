package com.vgmsistemas.vgmweb.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

@Entity
@Table(name = "acciones_com_detalle")
@SecondaryTable(name = "v_acciones_com_detalle_disponible", 
pkJoinColumns={@PrimaryKeyJoinColumn(name="id_acciones_com", referencedColumnName="id_acciones_com"),
				@PrimaryKeyJoinColumn(name="id_acciones_com_detalle", referencedColumnName="id_acciones_com_detalle")})
public class AccionesComDetalle {
	@EmbeddedId
	private PkAccionesComDetalle id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_acciones_com", insertable = false, updatable = false)
	private AccionesCom accionCom;
	@ManyToOne
	@JoinColumn(name="id_negocio", insertable = false, updatable = false)
	private Negocio negocio;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
        @JoinColumn(
            name = "id_segmento",
            referencedColumnName = "id_segmento", insertable = false, updatable = false),
        @JoinColumn(
            name = "id_negocio",
            referencedColumnName = "id_negocio", insertable = false, updatable = false)
    })
	private Rubro rubro;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
        @JoinColumn(
            name = "id_subrubro",
            referencedColumnName = "id_subrubro", insertable = false, updatable = false),
        @JoinColumn(
            name = "id_segmento",
            referencedColumnName = "id_segmento", insertable = false, updatable = false),
        @JoinColumn(
            name = "id_negocio",
            referencedColumnName = "id_negocio", insertable = false, updatable = false)
    })
	private Subrubro subrubro;
	
	@Column(name = "id_subrubro", insertable = false, updatable = false)
	private Long idSubrubro;
	@Column(name = "id_segmento", insertable = false, updatable = false)
	private Long idRubro;
	@Column(name = "id_negocio", insertable = false, updatable = false)
	private Long idNegocio;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "id_articulos")
	private Articulo articulo;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "id_linea")
	private Marca marca;	
	@Column(name = "ta_dto")
	private Float taDto;	
	@Column(name = "id_proveedor")
	private Long idProveedor;
	@Column(name = "ca_maxima")
	private Float caMaxima;
	@Column(table = "v_acciones_com_detalle_disponible", name = "ca_vendida", insertable = false, updatable = false)
	private Float caVendida;
	@Column(name = "ta_dto_bcerrado")
	private Float taDtoBcerrado;
	@Column(name = "sn_aviso_camax")
	private String snAvisoCamax;
	
	@Column(name = "rg_limite_inf")
	private Float rgLimiteInf;
	@Column(name = "rg_limite_sup")
	private Float rgLimiteSup;
	
	
	public PkAccionesComDetalle getId() {
		return id;
	}
	public void setId(PkAccionesComDetalle id) {
		this.id = id;
	}	
	public Float getTaDto() {
		return taDto;
	}
	public void setTaDto(Float taDto) {
		this.taDto = taDto;
	}
	public Negocio getNegocio() {
		return negocio;
	}
	public void setNegocio(Negocio negocio) {
		this.negocio = negocio;
	}
	public Rubro getRubro() {
		return rubro;
	}
	public void setRubro(Rubro rubro) {
		this.rubro = rubro;
	}
	public Subrubro getSubrubro() {
		return subrubro;
	}
	public void setSubrubro(Subrubro subrubro) {
		this.subrubro = subrubro;
	}
	public Articulo getArticulo() {
		return articulo;
	}
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
	public Marca getMarca() {
		return marca;
	}
	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	public Long getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(Long idProveedor) {
		this.idProveedor = idProveedor;
	}
	public AccionesCom getAccionCom() {
		return accionCom;
	}
	public void setAccionCom(AccionesCom accionCom) {
		this.accionCom = accionCom;
	}
	public Float getCaMaxima() {
		return caMaxima;
	}
	public void setCaMaxima(Float caMaxima) {
		this.caMaxima = caMaxima;
	}
	public Float getCaVendida() {
		return caVendida;
	}
	public void setCaVendida(Float caVendida) {
		this.caVendida = caVendida;
	}
	public Float getTaDtoBcerrado() {
		return taDtoBcerrado;
	}
	public void setTaDtoBcerrado(Float taDtoBcerrado) {
		this.taDtoBcerrado = taDtoBcerrado;
	}
	public String getSnAvisoCamax() {
		return snAvisoCamax;
	}
	public void setSnAvisoCamax(String snAvisoCamax) {
		this.snAvisoCamax = snAvisoCamax;
	}
	public Float getRgLimiteInf() {
		return rgLimiteInf;
	}
	public void setRgLimiteInf(Float rgLimiteInf) {
		this.rgLimiteInf = rgLimiteInf;
	}
	public Float getRgLimiteSup() {
		return rgLimiteSup;
	}
	public void setRgLimiteSup(Float rgLimiteSup) {
		this.rgLimiteSup = rgLimiteSup;
	}
	public Long getIdSubrubro() {
		return idSubrubro;
	}
	public void setIdSubrubro(Long idSubrubro) {
		this.idSubrubro = idSubrubro;
	}
	public Long getIdRubro() {
		return idRubro;
	}
	public void setIdRubro(Long idRubro) {
		this.idRubro = idRubro;
	}
	public Long getIdNegocio() {
		return idNegocio;
	}
	public void setIdNegocio(Long idNegocio) {
		this.idNegocio = idNegocio;
	}
}
