package com.formacionspringboot.apirest.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "productos")
public class Producto implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long clave;
	
	@Column(nullable = false)
	private String nombre;
	
	private String descripcion;
	
	@Column(nullable = false)
	private double precioUnitario;
	
	@Column(nullable = false)
	private int existencias;

	//Campos autocalculados
	public Long getClave() {
		return clave;
	}

	public void setClave(Long clave) {
		this.clave = clave;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public int getExistencias() {
		return existencias;
	}

	public void setExistencias(int existencias) {
		this.existencias = existencias;
	}
	
	

}
