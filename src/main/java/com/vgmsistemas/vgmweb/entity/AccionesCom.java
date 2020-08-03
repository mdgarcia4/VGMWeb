package com.vgmsistemas.vgmweb.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

@Entity
@Table(name = "acciones_com")
@Where(clause="sn_con_codigos != 'S' and sn_con_codigos is not null")
public class AccionesCom {
	@Id
	@Column(name = "id_acciones_com")
	private int idAccionesCom;
	@Column(name = "sn_con_codigos")
	private String snConCodigos;
	@OneToMany(mappedBy = "accionCom", fetch=FetchType.LAZY)
	private List<AccionesComDetalle> accionesComDetalle;
	@Column(name = "fe_vigencia_ini")
	private Date feVigenciaIni;
	@Column(name = "fe_vigencia_fin")
	private Date feVigenciaFin;
	@Column(name = "id_tipo_acciones")
	private Integer idTipoAcciones;
	@Column(name = "ti_origen")
	private String tiOrigen;
	
	public static Integer TIPO_ACCIONES_MARCA = 5;
	public static Integer TIPO_ACCIONES_ARTICULO = 4;
	public static Integer TIPO_ACCIONES_SUBRUBRO = 3;
	public static Integer TIPO_ACCIONES_RUBRO = 2;
	public static Integer TIPO_ACCIONES_NEGOCIO = 1;
	
	public static String TI_ORIGEN_EMPRESA = "E";
    public static String TI_ORIGEN_PROVEEDOR = "P";
    public static String TI_ORIGEN_GLOBALES = "G";
    public static String TI_ORIGEN_CONJUNTAS = "C";
		
	public int getIdAccionesCom() {
		return idAccionesCom;
	}
	public void setIdAccionesCom(int idAccionesCom) {
		this.idAccionesCom = idAccionesCom;
	}
	public String getSnConCodigos() {
		return snConCodigos;
	}
	public void setSnConCodigos(String snConCodigos) {
		this.snConCodigos = snConCodigos;
	}
	public List<AccionesComDetalle> getAccionesComDetalle() {
		return accionesComDetalle;
	}
	public void setAccionesComDetalle(List<AccionesComDetalle> accionesComDetalle) {
		this.accionesComDetalle = accionesComDetalle;
	}
	public Date getFeVigenciaIni() {
		return feVigenciaIni;
	}
	public void setFeVigenciaIni(Date feVigenciaIni) {
		this.feVigenciaIni = feVigenciaIni;
	}
	public Date getFeVigenciaFin() {
		return feVigenciaFin;
	}
	public void setFeVigenciaFin(Date feVigenciaFin) {
		this.feVigenciaFin = feVigenciaFin;
	}
	public Integer getIdTipoAcciones() {
		return idTipoAcciones;
	}
	public void setIdTipoAcciones(Integer idTipoAcciones) {
		this.idTipoAcciones = idTipoAcciones;
	}
	public String getTiOrigen() {
		return tiOrigen;
	}
	public void setTiOrigen(String tiOrigen) {
		this.tiOrigen = tiOrigen;
	}
	
}
