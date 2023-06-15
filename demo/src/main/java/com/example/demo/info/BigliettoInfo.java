package com.example.demo.info;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class BigliettoInfo {

	@JsonInclude(Include.NON_NULL)
	private int codOperazione;
	
	@JsonInclude(Include.NON_NULL)
	private String idSpettacolo;
	
	@JsonInclude(Include.NON_NULL)
	private String codCliente;
	
	@JsonInclude(Include.NON_NULL)
	private String tipoPagamento;
	
	@JsonInclude(Include.NON_NULL)
	private int qtBiglietti;
	
	@JsonInclude(Include.NON_NULL)
    private double prezzo;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	@JsonInclude(Include.NON_NULL)
    private Date data;
	
	@JsonInclude(Include.NON_NULL)
    private String citta;
	
	@JsonInclude(Include.NON_NULL)
    private String luogo;
	
	@JsonInclude(Include.NON_NULL)
    private String artista;
	

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getLuogo() {
		return luogo;
	}

	public void setLuogo(String luogo) {
		this.luogo = luogo;
	}

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

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
	
	
}
