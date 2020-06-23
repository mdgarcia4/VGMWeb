package com.vgmsistemas.vgmweb.dto;

import java.io.Serializable;

public class CustomerWsDto implements Serializable {
	
	private static final long serialVersionUID = 2L;
	private PkCustomerWsDto id;
	
	public PkCustomerWsDto getId() {
		return id;
	}
	public void setId(PkCustomerWsDto id) {
		this.id = id;
	}
}
