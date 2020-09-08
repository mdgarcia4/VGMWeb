package com.vgmsistemas.vgmweb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "v_stock")
public class Stock {
	@Id
	@GeneratedValue
	@Column(name = "id_articulos")	
    private Long idArticulos;
	@Column(name = "stock")
    private float stock;
	@Column(name = "id_deposito")
    private Long idDeposito;

    public float getStock() {
        return stock;
    }

    public void setStock(float stock) {
        this.stock = stock;
    }

	public Long getIdArticulos() {
		return idArticulos;
	}

	public void setIdArticulos(Long idArticulos) {
		this.idArticulos = idArticulos;
	}

	public Long getIdDeposito() {
		return idDeposito;
	}

	public void setIdDeposito(Long idDeposito) {
		this.idDeposito = idDeposito;
	}

}
