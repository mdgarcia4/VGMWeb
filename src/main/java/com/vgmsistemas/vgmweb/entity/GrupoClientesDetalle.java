package com.vgmsistemas.vgmweb.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumns;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "grupo_clientes_detalle")
public class GrupoClientesDetalle {
	@EmbeddedId
	private PkGrupoClientesDetalle id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
        @JoinColumn(
            name = "id_sucursal",
            referencedColumnName = "id_sucursal", insertable = false, updatable = false),
        @JoinColumn(
            name = "id_cliente",
            referencedColumnName = "id_cliente", insertable = false, updatable = false),
        @JoinColumn(
            name = "id_comercio",
            referencedColumnName = "id_comercio", insertable = false, updatable = false)
    })
	private Cliente cliente;
	
	public PkGrupoClientesDetalle getId() {
		return id;
	}
	public void setId(PkGrupoClientesDetalle id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
