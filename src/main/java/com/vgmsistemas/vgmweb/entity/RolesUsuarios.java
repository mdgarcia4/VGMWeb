package com.vgmsistemas.vgmweb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RolesUsuarios {

	@Id
	private PkRolesUsuarios id;

	public PkRolesUsuarios getId() {
		return id;
	}

	public void setId(PkRolesUsuarios id) {
		this.id = id;
	}
}