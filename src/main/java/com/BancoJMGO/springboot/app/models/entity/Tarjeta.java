package com.BancoJMGO.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name="tarjetas")
public class Tarjeta implements Serializable{

	private static final long serialVersionUID = 2564315752569370407L;
	
	@Column(name="id_tarjeta")
	@NonNull
	private String idTarjeta;
	
	@Column(name="numero_tarjeta", length=16)
	@NonNull
	private String numeroTarjeta;
	
	@Column(name="numero_de_cuenta")
	@NonNull
	private String numeroDeCuenta;
	
	@Column(length=3)
	@NonNull
	private String icv;
	
	@Column
	@NonNull
	private String nombre;

	public String getIdTarjeta() {
		return idTarjeta;
	}

	public void setIdTarjeta(String idTarjeta) {
		this.idTarjeta = idTarjeta;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public String getNumeroDeCuenta() {
		return numeroDeCuenta;
	}

	public void setNumeroDeCuenta(String numeroDeCuenta) {
		this.numeroDeCuenta = numeroDeCuenta;
	}

	public String getIcv() {
		return icv;
	}

	public void setIcv(String icv) {
		this.icv = icv;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}