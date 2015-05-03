package com.unbosque.info.entidad;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the tratamiento database table.
 * 
 */
@Entity
@Table (name = "tratamiento")
@NamedQuery(name="Tratamiento.findAll", query="SELECT t FROM Tratamiento t")
public class Tratamiento implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	
	private String descripcion;

	private String estado;

	public Tratamiento() {
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}