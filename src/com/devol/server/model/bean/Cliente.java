package com.devol.server.model.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable
public class Cliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PrimaryKey
	@Persistent
	private Key idCliente;
	@Persistent
	private String dni;
	@Persistent
	private String nombre;
	@Persistent
	private String apellido;
	@Persistent
	private String telefono;
	@Persistent
	private String direccion;
	@Persistent
	private Usuario beanUsuario;
	@Persistent
	private Long version;
	@Persistent(mappedBy = "beanCliente") 
	private Set<Prestamo> listPrestamo=new HashSet<Prestamo>();	 
	@Persistent
	private String idUsuario;
	@NotPersistent
	private String operacion;
	@NotPersistent
	private String codCliente;
	@Persistent
	private Integer numPrestamo=0;
	
	public String getIdCliente() {
		return KeyFactory.keyToString(idCliente);
	}

	public void setIdCliente(String idUsuario) {
		Key keyUsuario = KeyFactory.stringToKey(idUsuario);
		this.idCliente = KeyFactory.createKey(keyUsuario,
		Cliente.class.getSimpleName(), java.util.UUID.randomUUID().toString());
		this.idUsuario = idUsuario;
	}					

	public String getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(String codCliente) {
		this.idCliente = KeyFactory.stringToKey(codCliente);
		this.codCliente = codCliente;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {		
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Usuario getBeanUsuario() {
		return beanUsuario;
	}

	public void setBeanUsuario(Usuario beanUsuario) {
		this.beanUsuario = beanUsuario;
	}

	public String getIdUsuario() {
		return idUsuario;
	}	
	
	

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idCliente == null) ? 0 : idCliente.hashCode());
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
		Cliente other = (Cliente) obj;
		if (idCliente == null) {
			if (other.idCliente != null)
				return false;
		} else if (!idCliente.equals(other.idCliente))
			return false;
		return true;
	}

	
	 public Set<Prestamo> getListPrestamo() { return listPrestamo; }
	 
	 public void setListPrestamo(Set<Prestamo> listPrestamo) {
	 this.listPrestamo = listPrestamo; }

	public Integer getNumPrestamo() {
		return numPrestamo;
	}

	public void setNumPrestamo(Integer numPrestamo) {
		this.numPrestamo = numPrestamo;
	}
	 
	 
	 

}
