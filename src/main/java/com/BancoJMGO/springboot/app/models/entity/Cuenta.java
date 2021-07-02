package com.BancoJMGO.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name="cuentas")
public class Cuenta implements Serializable{

	private static final long serialVersionUID = 845809032924929098L;

	@Column(name="numero_de_cuenta")
	@NonNull
	private String numeroDeCuenta;
	
	@Column(name="id_cuenta")
	@NonNull
	private String idCuenta;
	
	@Column(name="monto_minimo")
	@NonNull
	private double montoMinimo;
	
	@Column(name="saldo_actual")
	@NonNull
	private double saldoActual;
	
	@Column
	@NonNull
	private float porcentaje;
	
	@Column
	@NonNull
	private Tarjeta[] tarjetas;
	
	@Column
	@NonNull
	private CasaInversionista oferta;

	public String getNumeroDeCuenta() {
		return numeroDeCuenta;
	}

	public void setNumeroDeCuenta(String numeroDeCuenta) {
		this.numeroDeCuenta = numeroDeCuenta;
	}

	public String getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(String idCuenta) {
		this.idCuenta = idCuenta;
	}

	public double getMontoMinimo() {
		return montoMinimo;
	}

	public void setMontoMinimo(double montoMinimo) {
		this.montoMinimo = montoMinimo;
	}

	public double getSaldoActual() {
		return saldoActual;
	}

	public void setSaldoActual(double saldoActual) {
		this.saldoActual = saldoActual;
	}

	public float getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(float porcentaje) {
		this.porcentaje = porcentaje;
	}

	public Tarjeta[] getTarjetas() {
		return tarjetas;
	}

	public void setTarjetas(Tarjeta[] tarjetas) {
		this.tarjetas = tarjetas;
	}

	public CasaInversionista getOferta() {
		return oferta;
	}

	public void setOferta(CasaInversionista oferta) {
		this.oferta = oferta;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
