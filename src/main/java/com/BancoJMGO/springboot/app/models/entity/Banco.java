package com.BancoJMGO.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.lang.NonNull;


@Entity
@Table(name="bancos")
public class Banco implements Serializable{

	private static final long serialVersionUID = 8979615319274357131L;
	
	@Column
	@NonNull
	private String sucursal;
	
	@Column
	@NonNull
	private String id;
	
	@Column
	@NonNull
	private String ubicacion;
	
	private Empleado[] cantidadDeEmpleados;
	
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Empleado[] getCantidadDeEmpleados() {
		return cantidadDeEmpleados;
	}

	public void setCantidadDeEmpleados(Empleado[] cantidadDeEmpleados) {
		this.cantidadDeEmpleados = cantidadDeEmpleados;
	}

}
