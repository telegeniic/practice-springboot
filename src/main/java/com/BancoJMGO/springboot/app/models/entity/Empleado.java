package com.BancoJMGO.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.ManyToOne;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "empleados")
public class Empleado implements Serializable {

	private static final long serialVersionUID = 6063399286153691001L;

	@Id
	@Column(name = "id_empleado")
	private String idEmpleado;

	@Column
	@NonNull
	private String nombre;

	@Column
	@NonNull
	private String apellido;

	@Column
	@NonNull
	private String puesto;

	@Column
	@NonNull
	private int antiguedad;

	@JoinColumn(name = "banco", referencedColumnName = "id_banco", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Banco banco;

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public String getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(String idEmpleado) {
		this.idEmpleado = idEmpleado;
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

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public int getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(int antiguedad) {
		this.antiguedad = antiguedad;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
