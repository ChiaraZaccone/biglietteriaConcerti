package com.example.demo.info;

import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class SpettacoloInfo {

	@JsonInclude(Include.NON_NULL)
	private String idSpettacolo;
	
	@JsonInclude(Include.NON_NULL)
	private String artista;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	@JsonInclude(Include.NON_NULL)
	private Date dataSpettacolo;

	@JsonInclude(Include.NON_NULL)
	private String citta;
	
	@JsonInclude(Include.NON_NULL)
	private String luogo;
	
	@JsonInclude(Include.NON_NULL)
	private double prezzo;
	
	@JsonInclude(Include.NON_NULL)
	private int postiDisp;

	public String getIdSpettacolo() {
		return idSpettacolo;
	}

	public void setIdSpettacolo(String idSpettacolo) {
		this.idSpettacolo = idSpettacolo;
	}

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public Date getDataSpettacolo() {
		return dataSpettacolo;
	}

	public void setDataSpettacolo(Date dataSpettacolo) {
		this.dataSpettacolo = dataSpettacolo;
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

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public int getPostiDisp() {
		return postiDisp;
	}

	public void setPostiDisp(int postiDisp) {
		this.postiDisp = postiDisp;
	}

	@Override
	public int hashCode() {
		return Objects.hash(artista, citta, dataSpettacolo, idSpettacolo, luogo, postiDisp, prezzo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SpettacoloInfo other = (SpettacoloInfo) obj;
		return Objects.equals(artista, other.artista) && Objects.equals(citta, other.citta)
				&& Objects.equals(dataSpettacolo, other.dataSpettacolo)
				&& Objects.equals(idSpettacolo, other.idSpettacolo) && Objects.equals(luogo, other.luogo)
				&& postiDisp == other.postiDisp
				&& Double.doubleToLongBits(prezzo) == Double.doubleToLongBits(other.prezzo);
	}
	
	
}
