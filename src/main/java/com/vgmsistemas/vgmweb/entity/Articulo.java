/**
 * 
 */
package com.vgmsistemas.vgmweb.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table( name = "articulos")
@Where(clause = "(sn_activo='S' and sn_web='S')")
public class Articulo{

	@Id
	@Column(name = "id_articulos")	
	private Long id;
	@Column(name = "de_articulos")
	private String descripcion;
	@Column(name = "de_extra")
	private String descripcionLarga;
	@Column(name = "ca_unidxbulto")
	private Integer unidadPorBulto;
	@Transient
	private Float stock;
	@Formula("(SELECT ssf.ca_stkdeposito "+
		   			"FROM stock_fecha sf , stock_sucursal_fecha ssf , empresas e , sucursales s "+
					"WHERE (sf.id_deposito = ssf.id_deposito) " +
					"AND (sf.fe_stock_inicio = ssf.fe_stock_inicio) "+
					"AND (e.id_sucactiva = s.id_sucursal ) "+
					"AND (ssf.id_deposito = s.id_deposito ) "+
					"AND (e.sn_activada = 'S') "+
					"AND (ssf.id_articulos = id_articulos))")
	private Float stockInicial;
	@Formula("(SELECT SUM(sm.ca_articulos * sm.ti_aplica_stock) " +
	         			"FROM stock_movimientos sm, stock_fecha sf , empresas e, sucursales s " +
	         			"WHERE (sm.id_deposito = sf.id_deposito) " +
	         			"AND (sm.fe_movimiento >= sf.fe_stock_inicio) " + 
	         			"AND (e.id_sucactiva = s.id_sucursal) " +
	         			"AND (sm.id_deposito = s.id_deposito) " +
	         			"AND (e.sn_activada = 'S') " +
	         			"AND (sm.id_articulos = id_articulos))")
	private Float stockMovimientos;
	@Column(name = "ta_iva")
	private Float tasaIva;
	@Column(name = "ta_imp_interno")
	private Float tasaImpuestoInterno;
	@Column(name = "pr_imp_interno")
	private Float impuestoInterno;	
	@Column(name = "id_empresa")
	private String codigoEmpresa;
	
	@ManyToOne(fetch = FetchType.LAZY)
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_linea")
	private Marca marca;
	
	@OneToMany(/*mappedBy = "articulo",*/ fetch=FetchType.LAZY)
	@JoinColumn(name = "id_articulos", referencedColumnName= "id_articulos", insertable = false, updatable = false)
	private List<ListaPrecioDetalle> listaPrecios;
	@Column(name = "pr_arcor_a")
	private Float precioBase;
	
	/*@OneToMany(mappedBy = "id.idPromocion", fetch=FetchType.LAZY)
	private List<ArticuloPromocion> articulosPromocion;
	
	@Transient
	@OneToOne(fetch=FetchType.LAZY)
	private RequisitoPromocion requisitoPromocion;*/
	@Column(name = "sn_promo")
	private String snPromocion;
	@Column(name = "sn_activo")
	private String snActivo;
	
	//Cantidad vendida para aquellos articulos que son Combo
	@Column(name = "id_proveedor")
	private Long idProveedor;	
	@Column(name = "sn_pesable")
	private String snPesable;	
	@Column(name = "sn_unidad")
	private String snUnidad;
	@Transient
	private float stockMovimientoPedido = 0f;
	@Transient
	private float precioConIva = 0f;
	@Transient
	private float precioSinIva = 0f;
	
	@Column(name="sn_generico")
	private String isGenerico;
	
	@Column(name="fe_publicacion_web")
	private Date fePublicacion;
	
	@Column(name="id_codigobarras")
	private String idCodigoBarras;
	
	@Column(name="sn_web")
	private String snWeb;

	//private static final String PESABLE = "S";
	//private static final String NO_PESABLE = "N";
	
	public Articulo(){
		
	}
	
	public Articulo(Articulo a, float precioConIva, float precioSinIva){
		this.id = a.id;
		this.precioConIva = precioConIva;
		this.precioSinIva = precioSinIva;
		this.marca = a.marca;
		this.subrubro = a.subrubro;
		this.descripcion = a.descripcion;
		//this.articulosPromocion = a.articulosPromocion;
		this.snPromocion = a.snPromocion;
		this.isGenerico = a.isGenerico;
	}

	public Articulo(long idArt, float precioConIva, float precioSinIva, long idMarca, long idSegmento, long idNegocio, long idSubrubro, String descripcion, 
					//Object artsProm, 
					String snPromocion, String isGenerico,String deExtra){

		this.id = idArt;
		this.precioConIva = precioConIva;
		this.precioSinIva = precioSinIva;
		Marca marca = new Marca();
		marca.setId(idMarca);
		this.marca = marca;
		
		Subrubro subrubro = new Subrubro();
		PkSubrubro pksubrubro = new PkSubrubro();
		pksubrubro.setIdSubrubro(idSubrubro);
		pksubrubro.setIdRubro(idSegmento);
		pksubrubro.setIdNegocio(idNegocio);
		subrubro.setId(pksubrubro);
		this.subrubro = subrubro;
		
		this.descripcion = descripcion;
		//this.articulosPromocion = (List<ArticuloPromocion>) artsProm;
		this.snPromocion = snPromocion;
		this.isGenerico = isGenerico;
		this.descripcionLarga = deExtra;
	}
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
	//@Column(name = "de_articulos")
	public String getDescripcion() {
		return this.descripcion;
	}

	/**
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the unidadPorBulto
	 */
	public Integer getUnidadPorBulto() {
		if(unidadPorBulto == null){
			unidadPorBulto = 0;
		}
		return this.unidadPorBulto;
	}

	/**
	 * @param unidadPorBulto
	 *            the unidadPorBulto to set
	 */
	public void setUnidadPorBulto(Integer unidadPorBulto) {
		this.unidadPorBulto = unidadPorBulto;
	}

	/**
	 * @return the stock
	 
	*/
	public Float getStock() {
		Float retorno = null;
		this.stock = this.getStockInicial() + this.getStockMovimientos();
		if(this.stock != null){
			if (this.stock < 0){
				retorno = 0F;
			}else{
				retorno = this.stock;
			}	
		}
		return retorno;
	} 

	/**
	 * @param stock
	 *            the stock to set
	 */
	public void setStock(Float stock) {
		this.stock = stock;
	}

	/**
	 * @return the tasaIva
	 */
	public Float getTasaIva() {
		return this.tasaIva;
	}

	/**
	 * @param tasaIva
	 *            the tasaIva to set
	 */
	public void setTasaIva(Float tasaIva) {
		this.tasaIva = tasaIva;
	}

	public Float getImpuestoInterno() {
		return impuestoInterno;
	}

	public void setImpuestoInterno(Float impuestoInterno) {
		this.impuestoInterno = impuestoInterno;
	}

	/**
	 * @return the subrubro
	 */
	public Subrubro getSubrubro() {
		return this.subrubro;
	}

	/**
	 * @param subrubro
	 *            the subrubro to set
	 */
	public void setSubrubro(Subrubro subrubro) {
		this.subrubro = subrubro;
	}

	/**
	 * @return the marca
	 */
	public Marca getMarca() {
		return this.marca;
	}

	/**
	 * @param marca
	 *            the marca to set
	 */
	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	/**
	 * @return the listaPrecios
	 */
	public List<ListaPrecioDetalle> getListaPrecios() {
		if(listaPrecios == null){
			listaPrecios  = new ArrayList<ListaPrecioDetalle>();
		}
		return this.listaPrecios;
	}

	/**
	 * @param listaPrecios
	 *            the listaPrecios to set
	 */
	public void setListaPrecios(List<ListaPrecioDetalle> listaPrecios) {
		this.listaPrecios = listaPrecios;
	}

	/**
	 * @return the codigoEmpresa
	 */
	public String getCodigoEmpresa() {
		return this.codigoEmpresa;
	}

	/**
	 * @param codigoEmpresa
	 *            the codigoEmpresa to set
	 */
	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}

	/**
	 * @return the precioCosto
	 */
	/*public Float getPrecioCosto() {
		return precioCosto;
	}*/

	/**
	 * @param precioCosto the precioCosto to set
	 */
	/*public void setPrecioCosto(Float precioCosto) {
		this.precioCosto = precioCosto;
	}*/



	/**
	 * @return the precioBase
	 */
	public Float getPrecioBase() {
		return precioBase;
	}

	/**
	 * @param precioBase the precioBase to set
	 */
	public void setPrecioBase(Float precioBase) {
		this.precioBase = precioBase;
	}

	
	
	public String getSnPromocion() {
		return snPromocion;
	}

	public void setSnPromocion(String snPromocion) {
		this.snPromocion = snPromocion;
	}

	public void setSnActivo(String snActivo) {
		this.snActivo = snActivo;
	}

	public String getSnActivo() {
		return snActivo;
	}

	

	public void setStockInicial(Float stockInicial) {
		this.stockInicial = stockInicial;
	}

	public Float getStockInicial() {
		if(stockInicial == null){
			stockInicial = new Float(0);
		}
		return stockInicial;
	}

	public void setStockMovimientos(Float stockMovimientos) {
		this.stockMovimientos = stockMovimientos;
	}
	
	public void setStockMovimientos(Float stockMovimientos, float cantidad) {
		setStockMovimientos(stockMovimientos - cantidad);
		stockMovimientoPedido = cantidad;
	}

	public Float getStockMovimientos() {
		if(stockMovimientos == null){
			stockMovimientos = new Float(0);
		}
		return stockMovimientos;
	}

	/*@OneToOne
	public RequisitoPromocion getRequisitoPromocion() {
		return requisitoPromocion;
	}

	public void setRequisitoPromocion(RequisitoPromocion requisitoPromocion) {
		this.requisitoPromocion = requisitoPromocion;
	}*/

	

	public Long getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(Long idProveedor) {
		this.idProveedor = idProveedor;
	}

	public Float getTasaImpuestoInterno() {
		return tasaImpuestoInterno;
	}

	public void setTasaImpuestoInterno(Float tasaImpuestoInterno) {
		this.tasaImpuestoInterno = tasaImpuestoInterno;
	}

	public String getSnPesable() {
		return snPesable;
	}

	public void setSnPesable(String snPesable) {
		this.snPesable = snPesable;
	}

	public String getSnUnidad() {
		return snUnidad;
	}

	public void setSnUnidad(String snUnidad) {
		this.snUnidad = snUnidad;
	}

	

	public float getStockMovimientoPedido() {
		return stockMovimientoPedido;
	}

	/*public Float getTaCosto() {
		return taCosto;
	}

	public void setTaCosto(Float taCosto) {
		this.taCosto = taCosto;
	}*/

	public float getPrecioConIva() {
		return precioConIva;
	}

	public void setPrecioConIva(float precioConIva) {
		this.precioConIva = precioConIva;
	}

	public float getPrecioSinIva() {
		return precioSinIva;
	}

	public void setPrecioSinIva(float precioSinIva) {
		this.precioSinIva = precioSinIva;
	}

	public String getDescripcionLarga() {
		return descripcionLarga;
	}

	public void setDescripcionLarga(String descripcionLarga) {
		this.descripcionLarga = descripcionLarga;
	}

	public String getIsGenerico() {
		return isGenerico;
	}

	public void setIsGenerico(String isGenerico) {
		this.isGenerico = isGenerico;
	}

	public String getIdCodigoBarras() {
		return idCodigoBarras;
	}

	public void setIdCodigoBarras(String idCodigoBarras) {
		this.idCodigoBarras = idCodigoBarras;
	}

	public String getSnWeb() {
		return snWeb;
	}

	public void setSnWeb(String snWeb) {
		this.snWeb = snWeb;
	}	
	
}
