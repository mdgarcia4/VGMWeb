package com.vgmsistemas.vgmweb.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String usuario;
	@Column
	private String clave;	
	@Column(name="id_sucursal")
	private Integer idSucursal;	
	@Column(name="id_cliente")
	private Integer idCliente;	
	@Column(name="id_comercio")
	private Integer idComercio;		
	@Column
	private String nombre;	
	@Column(name="id_cuit")
	private String idCuit;	
	@Column(name="id_nrodoc")
	private Integer idNroDoc;
	
	@Column
	private String email;
	
	@Column
	private String telefono;

	@Column
	private boolean activo;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "roles_usuarios", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_rol"))
	private Set<Rol> roles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String username) {
		this.usuario = username;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String password) {
		this.clave = password;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean enabled) {
		this.activo = enabled;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public Integer getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(Integer idSucursal) {
		this.idSucursal = idSucursal;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Integer getIdComercio() {
		return idComercio;
	}

	public void setIdComercio(Integer idComercio) {
		this.idComercio = idComercio;
	}
	public String getIdCuit() {
		return idCuit;
	}

	public void setIdCuit(String idCuit) {
		this.idCuit = idCuit;
	}

	public Integer getIdNroDoc() {
		return idNroDoc;
	}

	public void setIdNroDoc(Integer idNroDoc) {
		this.idNroDoc = idNroDoc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", usuario=" + usuario + ", clave=" + clave + "]";
	}
}