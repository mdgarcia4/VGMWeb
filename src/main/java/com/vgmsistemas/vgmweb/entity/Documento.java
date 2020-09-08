package com.vgmsistemas.vgmweb.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

@Entity
@Table( name = "documentos" )
@Where(clause="(sn_estadistica='S' OR sn_movil = 'S' OR sn_preventa_web = 'S') "
		+ "AND (ti_documento='V' OR ti_documento='C' OR ti_documento = 'R' OR ti_documento = 'A' OR ti_documento = 'S')")
public class Documento{
	@EmbeddedId
	private PkDocumento id;
	@Column(name = "id_n1")
	private Integer idN1;
	
	public PkDocumento getId() {
		return id;
	}
	public void setId(PkDocumento id) {
		this.id = id;
	}
	public Integer getIdN1() {
		return idN1;
	}
	public void setIdN1(Integer idN1) {
		this.idN1 = idN1;
	}
}
