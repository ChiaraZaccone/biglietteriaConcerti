package com.example.demo.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="biglietti")
public class Biglietto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cod_operazione")
	private int codOperazione;
	
	@Column(name="id_spettacolo")
	private String idSpettacolo;
	
	@Column(name="cod_cliente")
	private String codCliente;
	
	@Column(name="tipo_pagamento")
	private String tipoPagamento;
	
	@Column(name="qt_biglietti")
	private int qtBiglietti;

	public int getCodOperazione() {
		return codOperazione;
	}

	public void setCodOperazione(int codOperazione) {
		this.codOperazione = codOperazione;
	}

	public String getIdSpettacolo() {
		return idSpettacolo;
	}

	public void setIdSpettacolo(String idSpettacolo) {
		this.idSpettacolo = idSpettacolo;
	}

	public String getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}

	public String getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public int getQtBiglietti() {
		return qtBiglietti;
	}

	public void setQtBiglietti(int qtBiglietti) {
		this.qtBiglietti = qtBiglietti;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codCliente, codOperazione, idSpettacolo, qtBiglietti, tipoPagamento);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Biglietto other = (Biglietto) obj;
		return Objects.equals(codCliente, other.codCliente) 
				&& Objects.equals(idSpettacolo, other.idSpettacolo) && qtBiglietti == other.qtBiglietti
				&& Objects.equals(tipoPagamento, other.tipoPagamento);
	}
	
	
	
}
