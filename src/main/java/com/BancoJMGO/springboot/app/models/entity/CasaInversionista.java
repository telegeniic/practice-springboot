package com.BancoJMGO.springboot.app.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "casa_inversionista")
public class CasaInversionista implements Serializable {

	private static final long serialVersionUID = 2807010861862237240L;

	@Id
	@Column(name = "id_oferta")
	private String idOferta;

	@Column(name = "nombre_oferta")
	@NonNull
	private String nombreOferta;

	@Column
	@NonNull
	private float plazos;

	@Column(name = "porcentaje_retorno")
	@NonNull
	private float porcentajeRetorno;

	@Column(name = "monto_minimo")
	@NonNull
	private double montoMinimo;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "casaInversionista", cascade = CascadeType.MERGE)
	private List<Cuenta> cuentas;

	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	public String getIdOferta() {
		return idOferta;
	}

	public void setIdOferta(String idOferta) {
		this.idOferta = idOferta;
	}

	public String getNombreOferta() {
		return nombreOferta;
	}

	public void setNombreOferta(String nombreOferta) {
		this.nombreOferta = nombreOferta;
	}

	public float getPlazos() {
		return plazos;
	}

	public void setPlazos(float plazos) {
		this.plazos = plazos;
	}

	public float getPorcentajeRetorno() {
		return porcentajeRetorno;
	}

	public void setPorcentajeRetorno(float porcentajeRetorno) {
		this.porcentajeRetorno = porcentajeRetorno;
	}

	public double getMontoMinimo() {
		return montoMinimo;
	}

	public void setMontoMinimo(double montoMinimo) {
		this.montoMinimo = montoMinimo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
