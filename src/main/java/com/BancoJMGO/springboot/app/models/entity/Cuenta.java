package com.BancoJMGO.springboot.app.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cuentas")
public class Cuenta implements Serializable {

	private static final long serialVersionUID = 845809032924929098L;

	@Column(name = "numero_de_cuenta")
	private String numeroDeCuenta;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "monto_minimo")
	private double montoMinimo;

	@Column(name = "saldo_actual")
	private double saldoActual;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cuenta", cascade = CascadeType.MERGE)
	private List<Tarjeta> tarjetas;

	@JoinColumn(name = "cliente", referencedColumnName = "id", nullable = true)
	@OneToOne(optional = false, fetch = FetchType.LAZY)
	private Cliente cliente;

	@Column
	private long idClienteAux;

	@JoinColumn(name = "casa_inversionista", referencedColumnName = "id", nullable = true)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private CasaInversionista casaInversionista;

	@Column
	private long idCasaInversionistaAux;

	public long getIdClienteAux() {
		return idClienteAux;
	}

	public void setIdClienteAux(long idClienteAux) {
		this.idClienteAux = idClienteAux;
	}

	public long getIdCasaInversionistaAux() {
		return idCasaInversionistaAux;
	}

	public void setIdCasaInversionistaAux(long idCasaInversionistaAux) {
		this.idCasaInversionistaAux = idCasaInversionistaAux;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public CasaInversionista getCasaInversionista() {
		return casaInversionista;
	}

	public void setCasaInversionista(CasaInversionista casaInversionista) {
		this.casaInversionista = casaInversionista;
	}

	public String getNumeroDeCuenta() {
		return numeroDeCuenta;
	}

	public void setNumeroDeCuenta(String numeroDeCuenta) {
		this.numeroDeCuenta = numeroDeCuenta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Tarjeta> getTarjetas() {
		return tarjetas;
	}

	public void setTarjetas(List<Tarjeta> tarjetas) {
		this.tarjetas = tarjetas;
	}

}
