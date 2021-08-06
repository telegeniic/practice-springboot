package com.BancoJMGO.springboot.app.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.CascadeType;

@Entity
@Table(name = "bancos")
public class Banco implements Serializable {

	private static final long serialVersionUID = 8979615319274357131L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String sucursal;

	@Column
	private String ubicacion;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "banco", cascade = CascadeType.MERGE)
	private List<Cliente> clientes;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "banco", cascade = CascadeType.MERGE)
	private List<Empleado> empleados;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

}
