package com.vgmsistemas.vgmweb.dto;

public class DailyOrderDetailWsDto {
	//private static final long serialVersionUID = -2136474004948994068L;
	
	private ItemWsDto articulo;
	private String idMovil;
	private Float cantidad;
	private Float unidades;
	private Integer bultos;
	
	public ItemWsDto getArticulo() {
		return articulo;
	}
	public void setArticulo(ItemWsDto articulo) {
		this.articulo = articulo;
	}
	public String getIdMovil() {
		return idMovil;
	}
	public void setIdMovil(String idMovil) {
		this.idMovil = idMovil;
	}
	public Float getCantidad() {
		return cantidad;
	}
	public void setCantidad(Float cantidad) {
		this.cantidad = cantidad;
	}
	public Float getUnidades() {
		return unidades;
	}
	public void setUnidades(Float unidades) {
		this.unidades = unidades;
	}
	public Integer getBultos() {
		return bultos;
	}
	public void setBultos(Integer bultos) {
		this.bultos = bultos;
	}
}
