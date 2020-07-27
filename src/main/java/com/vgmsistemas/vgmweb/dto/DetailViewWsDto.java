package com.vgmsistemas.vgmweb.dto;

import java.util.ArrayList;
import java.util.List;

public class DetailViewWsDto {

	private List<DailyOrderDetailWsDto> VentaDetalle;
	
	public List<DailyOrderDetailWsDto> getVentaDetalle() {
		if(VentaDetalle == null){
			VentaDetalle = new ArrayList<DailyOrderDetailWsDto>();
		}
		return VentaDetalle;
	}
	public void setVentaDetalle(List<DailyOrderDetailWsDto> ventaDetalle) {
		this.VentaDetalle = ventaDetalle;
	}
}