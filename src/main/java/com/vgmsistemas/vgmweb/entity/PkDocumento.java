package com.vgmsistemas.vgmweb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PkDocumento implements Serializable {

	private static final long serialVersionUID = 5966053595981650798L;
	@Column(name = "id_doc")
	private String idDocumento;
	@Column(name = "id_letra")
	private String idLetra;
	@Column(name = "id_ptovta")
	private long puntoVenta;

	public String getIdDocumento() {
		return this.idDocumento;
	}

	public void setIdDocumento(final String idDocumento) {
		this.idDocumento = idDocumento;
	}

	public String getIdLetra() {
		return this.idLetra;
	}

	public void setIdLetra(final String idLetra) {
		this.idLetra = idLetra;
	}

	public long getPuntoVenta() {
		return this.puntoVenta;
	}

	public void setPuntoVenta(long puntoVenta) {
		this.puntoVenta = puntoVenta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.idDocumento == null) ? 0 : this.idDocumento.hashCode());
		result = prime * result + ((this.idLetra == null) ? 0 : this.idLetra.hashCode());
		result = prime * result + (int) (this.puntoVenta ^ (this.puntoVenta >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof PkDocumento))
			return false;
		PkDocumento other = (PkDocumento) obj;
		if (this.idDocumento == null) {
			if (other.idDocumento != null)
				return false;
		} else if (!this.idDocumento.equals(other.idDocumento))
			return false;
		if (this.idLetra == null) {
			if (other.idLetra != null)
				return false;
		} else if (!this.idLetra.equals(other.idLetra))
			return false;
		if (this.puntoVenta != other.puntoVenta)
			return false;
		return true;
	}

}
