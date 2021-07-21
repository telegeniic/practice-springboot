package com.BancoJMGO.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.ManyToOne;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

	private static final long serialVersionUID = -5578178149734565815L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;

	@Column
	@NonNull
	private String nombre;

	@Column
	@NonNull
	private String apellido;

	@Column(name = "numero_telefonico")
	@NonNull
	private String numeroTelefonico;

	@Column
	@NonNull
	private String email;

	@Column(name = "numero_de_cuenta")
	@NonNull
	private String numeroDeCuenta;

	@JoinColumn(name = "banco", referencedColumnName = "id_banco", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Banco banco;

	@JoinColumn(name = "cuenta", referencedColumnName = "id_cuenta", nullable = false)
	@OneToOne(optional = false, fetch = FetchType.LAZY)
	private Cuenta cuenta;

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getNumeroTelefonico() {
		return numeroTelefonico;
	}

	public void setNumeroTelefonico(String numeroTelefonico) {
		this.numeroTelefonico = numeroTelefonico;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumeroDeCuenta() {
		return numeroDeCuenta;
	}

	public void setNumeroDeCuenta(String numeroDeCuenta) {
		this.numeroDeCuenta = numeroDeCuenta;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
