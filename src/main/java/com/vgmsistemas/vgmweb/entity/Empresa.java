package com.vgmsistemas.vgmweb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "empresas")
public class Empresa {

	public static long TIPO_EMPRESA_HACIENDA = 2;
	public static String CONTROL_DEPOSITO_X_COMPROBANTE = "C";
	public static String CONTROL_DEPOSITO_X_SUCURSAL = "S";
	public static String PERMITE = "S";
	public static String NO_PERMITE = "N";
	@Id
	@GeneratedValue
	@Column(name = "id_empresa")
	private Long id;
	@Column(name = "de_empresa")
	private String nombreEmpresa;
	@Column(name = "id_fcnc_anticipo")
	private String documentoAnticipo;
	@Column(name = "id_letra_anticipo")
	private String letraAnticipo;
	@Column(name = "nu_cuota_anticipo")
	private long numeroCuotaAnticipo;
	@Column(name = "sn_cobranza_estricta")
	private String isCobranzaEstrica;
	@Column(name = "id_fcnc_egreso")
	private String documentoEgreso;
	@Column(name = "sn_ingresar_pedidos")
	private String isIngresarPedidos;
	@Column(name = "sn_desdoble_articulo")
	private String isDesdobleArticulos;
	@Column(name = "sn_descuento")
	private String isDescuentoActivoMovil;
	@Column(name = "sn_descuento_cliente")
	private String isDecuentoActivoCliente;
	@Column(name = "id_lista_defecto")
	private int idlistaPorDefecto;
	@Column(name = "id_postal_defecto")
	private int idPostalPorDefecto;
	@Column(name = "sn_registrar_localizacion")
	private String isRegistrarLocalizacion;
	@Column(name = "pr_monto_min_factura")
	private Float montoMinimoFactura;
	@Column(name = "pr_monto_min_dto_factura")
	private Float importeACubrirParaDescuento;
	@Column(name = "ta_maxarticulocritico")
	private Float tasaMaximaArticulosCriticos;
	@Column(name = "nu_tolerancia")
	private Integer segundosTolerancia;
	@Column(name = "ta_ivanoinscripto")
	private Float tasaIvaNoCategorizado;
	@Column(name = "sn_ctrlstockxbulto")
	private String isControlStockPorBulto;
	@Column(name = "sn_ctacte_movil")
	private String isReciboAfectaCuentaCorriente;
	@Column(name = "sn_pedido_dif")
	private String isPedidoDiferido;
	@Column(name = "sn_multiempresa")
	private String isMultiEmpresa;
	@Column(name = "sn_control_deposito")
	private String isControlDesposito;
	@Column(name = "ti_empresa")
	private Integer tipoEmpresa;
	@Column(name = "ti_stock_neg")
	private String tiStockNeg;
	@Column(name = "sn_turno")
	private String isManejoTurno;
	@Column(name = "sn_control_lim_disp")
	private String isControlLimiteDisponibilidad;
	@Column(name = "sn_recibo_provisorio")
	private String snReciboProvisorio;
	@Column(name = "sn_movil_recibo_dto")
	private String snMovilReciboDto;
	@Column(name = "sn_clienteunico")
	private String snClienteUnico;
	@Column(name = "id_sucactiva")
	private Long idSucactiva;
	@Transient
	private boolean anticipoHabilitado;
	@Column(name = "sn_sum_iva_rep_movil")
	private String snSumaIvaReporteMovil;
	@Transient
	private int categoriasEmpRepartidor;
	@Column(name = "sn_activada")
	private String snActiva;
	@Column(name = "sn_imprimir_id_empresa")
	private String snImprimirIdEmpresa;
	@Column(name = "sn_stock_por_vista")
	private String snConsultaStockPorVista;
	@Column(name = "sn_ctacte_completa")
	private String snCtaCteCompleta;
	@Column(name = "sn_sinc_recibos")
	private String snSincRecibos;
	@Column(name = "sn_imp_resumen_cobranza")
	private String snImpResumenCobranza;
	
	@Column(name = "sn_modif_cd_movil")
	private String snModifCdMovil;
	@Column(name = "sn_acciones_com")
	private String snAccionesCom;

	public int getCategoriasEmpRepartidor() {
		return categoriasEmpRepartidor;
	}

	public void setCategoriasEmpRepartidor(int categoriasEmpRepartidor) {
		this.categoriasEmpRepartidor = categoriasEmpRepartidor;
	}

	public String getSnSumaIvaReporteMovil() {
		return snSumaIvaReporteMovil;
	}

	public void setSnSumaIvaReporteMovil(String snSumaIvaReporteMovil) {
		this.snSumaIvaReporteMovil = snSumaIvaReporteMovil;
	}

	public String getSnMovilReciboDto() {
		return snMovilReciboDto;
	}

	public void setSnMovilReciboDto(String snMovilReciboDto) {
		this.snMovilReciboDto = snMovilReciboDto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public String getDocumentoAnticipo() {
		return documentoAnticipo;
	}

	public void setDocumentoAnticipo(String documentoAnticipo) {
		this.documentoAnticipo = documentoAnticipo;
	}

	public String getLetraAnticipo() {
		return letraAnticipo;
	}

	public void setLetraAnticipo(String letraAnticipo) {
		this.letraAnticipo = letraAnticipo;
	}

	public long getNumeroCuotaAnticipo() {
		return numeroCuotaAnticipo;
	}

	public void setNumeroCuotaAnticipo(long numeroCuotaAnticipo) {
		this.numeroCuotaAnticipo = numeroCuotaAnticipo;
	}

	public void setIsCobranzaEstrica(String isCobranzaEstrica) {
		this.isCobranzaEstrica = isCobranzaEstrica;
	}

	public String getIsCobranzaEstrica() {
		return isCobranzaEstrica;
	}

	public String getDocumentoEgreso() {
		return documentoEgreso;
	}

	public void setDocumentoEgreso(String documentoEgreso) {
		this.documentoEgreso = documentoEgreso;
	}

	public String getIsIngresarPedidos() {
		return isIngresarPedidos;
	}

	public void setIsIngresarPedidos(String isIngresarPedidos) {
		this.isIngresarPedidos = isIngresarPedidos;
	}

	public String getIsDesdobleArticulos() {
		return isDesdobleArticulos;
	}

	public void setIsDesdobleArticulos(String isDesdobleArticulos) {
		this.isDesdobleArticulos = isDesdobleArticulos;
	}

	public String getIsDescuentoActivoMovil() {
		return isDescuentoActivoMovil;
	}

	public void setIsDescuentoActivoMovil(String isDescuentoActivoMovil) {
		this.isDescuentoActivoMovil = isDescuentoActivoMovil;
	}

	public int getIdlistaPorDefecto() {
		return idlistaPorDefecto;
	}

	public void setIdlistaPorDefecto(int idlistaPorDefecto) {
		this.idlistaPorDefecto = idlistaPorDefecto;
	}

	public int getIdPostalPorDefecto() {
		return idPostalPorDefecto;
	}

	public void setIdPostalPorDefecto(int idPostalPorDefecto) {
		this.idPostalPorDefecto = idPostalPorDefecto;
	}

	public String getIsRegistrarLocalizacion() {
		return isRegistrarLocalizacion;
	}

	public void setIsRegistrarLocalizacion(String isRegistrarLocalizacion) {
		this.isRegistrarLocalizacion = isRegistrarLocalizacion;
	}

	public Float getMontoMinimoFactura() {
		return montoMinimoFactura;
	}

	public void setMontoMinimoFactura(Float montoMinimoFactura) {
		this.montoMinimoFactura = montoMinimoFactura;
	}

	public Float getImporteACubrirParaDescuento() {
		return importeACubrirParaDescuento;
	}

	public void setImporteACubrirParaDescuento(Float importeACubrirParaDescuento) {
		this.importeACubrirParaDescuento = importeACubrirParaDescuento;
	}

	public Float getTasaMaximaArticulosCriticos() {
		return tasaMaximaArticulosCriticos;
	}

	public void setTasaMaximaArticulosCriticos(Float tasaMaximaArticulosCriticos) {
		this.tasaMaximaArticulosCriticos = tasaMaximaArticulosCriticos;
	}

	public Integer getSegundosTolerancia() {
		return segundosTolerancia;
	}

	public void setSegundosTolerancia(Integer segundosTolerancia) {
		this.segundosTolerancia = segundosTolerancia;
	}

	public Float getTasaIvaNoCategorizado() {
		return tasaIvaNoCategorizado;
	}

	public void setTasaIvaNoCategorizado(Float tasaIvaNoCategorizado) {
		this.tasaIvaNoCategorizado = tasaIvaNoCategorizado;
	}

	public String getIsDecuentoActivoCliente() {
		return isDecuentoActivoCliente;
	}

	public void setIsDecuentoActivoCliente(String isDecuentoActivoCliente) {
		this.isDecuentoActivoCliente = isDecuentoActivoCliente;
	}

	public String getIsControlStockPorBulto() {
		return isControlStockPorBulto;
	}

	public void setIsControlStockPorBulto(String isControlStockPorBulto) {
		this.isControlStockPorBulto = isControlStockPorBulto;
	}

	public String getIsReciboAfectaCuentaCorriente() {
		return isReciboAfectaCuentaCorriente;
	}

	public void setIsReciboAfectaCuentaCorriente(String isReciboAfectaCuentaCorriente) {
		this.isReciboAfectaCuentaCorriente = isReciboAfectaCuentaCorriente;
	}

	public String getIsPedidoDiferido() {
		return isPedidoDiferido;
	}

	public void setIsPedidoDiferido(String isPedidoDiferido) {
		this.isPedidoDiferido = isPedidoDiferido;
	}

	public String getIsMultiEmpresa() {
		return isMultiEmpresa;
	}

	public void setIsMultiEmpresa(String isMultiEmpresa) {
		this.isMultiEmpresa = isMultiEmpresa;
	}

	public Integer getTipoEmpresa() {
		return tipoEmpresa;
	}

	public void setTipoEmpresa(Integer tipoEmpresa) {
		this.tipoEmpresa = tipoEmpresa;
	}

	public String getTiStockNeg() {
		return tiStockNeg;
	}

	public void setTiStockNeg(String tiStockNeg) {
		this.tiStockNeg = tiStockNeg;
	}

	public String getIsControlDesposito() {
		if (isControlDesposito == null) {
			return "N";
		}
		return isControlDesposito;
	}

	public void setIsControlDesposito(String isControlDesposito) {
		this.isControlDesposito = isControlDesposito;
	}

	public String getIsManejoTurno() {
		if (isManejoTurno == null) {
			return "N";
		}
		return isManejoTurno;
	}

	public void setIsManejoTurno(String isManejoTurno) {
		this.isManejoTurno = isManejoTurno;
	}

	public String getIsControlLimiteDisponibilidad() {
		return isControlLimiteDisponibilidad;
	}

	public void setIsControlLimiteDisponibilidad(String isControlLimiteDisponibilidad) {
		this.isControlLimiteDisponibilidad = isControlLimiteDisponibilidad;
	}

	public String getSnReciboProvisorio() {
		return snReciboProvisorio;
	}

	public void setSnReciboProvisorio(String snReciboProvisorio) {
		this.snReciboProvisorio = snReciboProvisorio;
	}

	public String getSnClienteUnico() {
		return snClienteUnico;
	}

	public void setSnClienteUnico(String snClienteUnico) {
		this.snClienteUnico = snClienteUnico;
	}

	public boolean isAnticipoHabilitado() {
		return anticipoHabilitado;
	}

	public void setAnticipoHabilitado(boolean anticipoHabilitado) {
		this.anticipoHabilitado = anticipoHabilitado;
	}

	public Long getIdSucactiva() {
		return idSucactiva;
	}

	public void setIdSucactiva(Long idSucactiva) {
		this.idSucactiva = idSucactiva;
	}

	public String getSnActiva() {
		return snActiva;
	}

	public void setSnActiva(String snActiva) {
		this.snActiva = snActiva;
	}

	public String getSnImprimirIdEmpresa() {
		return snImprimirIdEmpresa;
	}

	public void setSnImprimirIdEmpresa(String snImprimirIdEmpresa) {
		this.snImprimirIdEmpresa = snImprimirIdEmpresa;
	}

	public String getSnConsultaStockPorVista() {
		return snConsultaStockPorVista;
	}

	public void setSnConsultaStockPorVista(String snConsultaStockPorVista) {
		this.snConsultaStockPorVista = snConsultaStockPorVista;
	}

	public String getSnCtaCteCompleta() {
		return snCtaCteCompleta;
	}

	public void setSnCtaCteCompleta(String snCtaCteCompleta) {
		this.snCtaCteCompleta = snCtaCteCompleta;
	}

	public String getSnSincRecibos() {
		return snSincRecibos;
	}

	public void setSnSincRecibos(String snSincRecibos) {
		this.snSincRecibos = snSincRecibos;
	}

	public String getSnImpResumenCobranza() {
		return snImpResumenCobranza;
	}

	public void setSnImpResumenCobranza(String snImpResumenCobranza) {
		this.snImpResumenCobranza = snImpResumenCobranza;
	}

	
	public String getsnModifCdMovil() {
		return snModifCdMovil;
	}

	public void setsnModifCdMovil(String snModifCdMovil) {
		this.snModifCdMovil = snModifCdMovil;
	}

	public String getSnModifCdMovil() {
		return snModifCdMovil;
	}

	public void setSnModifCdMovil(String snModifCdMovil) {
		this.snModifCdMovil = snModifCdMovil;
	}

	public String getSnAccionesCom() {
		if (snAccionesCom == null) {
			return "N";
		}
		return snAccionesCom;
	}

	public void setSnAccionesCom(String snAccionesCom) {
		this.snAccionesCom = snAccionesCom;
	}
}
