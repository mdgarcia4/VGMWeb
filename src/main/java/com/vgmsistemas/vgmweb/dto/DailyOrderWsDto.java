package com.vgmsistemas.vgmweb.dto;

//import java.io.Serializable;
//import java.util.ArrayList;
import java.util.List;

public class DailyOrderWsDto /*implements Serializable*/{
	//private static final long serialVersionUID = 1074853576498884937L;
	private CustomerWsDto cliente;
	private List<DailyOrderDetailWsDto> VentaDetalle;// = new ArrayList<DailyOrderDetailWsDto>();
	private String idMovil;
	private String pie;
	
	public CustomerWsDto getCliente() {
		return cliente;
	}
	public void setCliente(CustomerWsDto cliente) {
		this.cliente = cliente;
	}
	public List<DailyOrderDetailWsDto> getVentaDetalle() {
		return VentaDetalle;
	}
	public void setVentaDetalle(List<DailyOrderDetailWsDto> ventaDetalle) {
		this.VentaDetalle = ventaDetalle;
	}
	public String getIdMovil() {
		return idMovil;
	}
	public void setIdMovil(String idMovil) {
		this.idMovil = idMovil;
	}
	public String getPie() {
		return pie;
	}
	public void setPie(String pie) {
		this.pie = pie;
	}

}
