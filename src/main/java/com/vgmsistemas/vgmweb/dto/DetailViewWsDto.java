package com.vgmsistemas.vgmweb.dto;

import java.util.ArrayList;
import java.util.List;

public class DetailViewWsDto {

	//private static final long serialVersionUID = 2191503143427478983L;
	private List<DailyOrderDetailWsDto> ventaDetalle;
	
	public List<DailyOrderDetailWsDto> getVentaDetalle() {
		if(ventaDetalle==null){
			ventaDetalle = new ArrayList<DailyOrderDetailWsDto>();
		}
		return ventaDetalle;
	}
	public void setVentaDetalle(List<DailyOrderDetailWsDto> ventaDetalle) {
		this.ventaDetalle = ventaDetalle;
	}
}