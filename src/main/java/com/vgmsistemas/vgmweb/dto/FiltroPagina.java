package com.vgmsistemas.vgmweb.dto;

public class FiltroPagina {
	String filtroId;
	String filtroName;
	String descripcionLarga;

	public FiltroPagina() {
	};

	public FiltroPagina(String filtroId, String filtroName) {
		super();
		this.filtroId = filtroId;
		this.filtroName = filtroName;
		this.descripcionLarga = filtroId + ": " + filtroName;
	}

	public String getFiltroId() {
		return filtroId;
	}

	public void setFiltroId(String filtroId) {
		this.filtroId = filtroId;
	}

	public String getFiltroName() {
		return filtroName;
	}

	public void setFiltroName(String filtroName) {
		this.filtroName = filtroName;
	}

	public String getDescripcionLarga() {
		return descripcionLarga;
	}

	public void setDescripcionLarga(String descripcionLarga) {
		this.descripcionLarga = descripcionLarga;
	}

}
