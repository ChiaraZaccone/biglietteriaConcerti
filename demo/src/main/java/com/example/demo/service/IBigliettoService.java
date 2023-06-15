package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Biglietto;

public interface IBigliettoService {

	public List<Biglietto> getAllBiglietti();
	
	public Biglietto getBigliettoByCodOperazione(int codOperazione);
	
	public boolean existsBigliettoByCodOperazione(int codOperazione);
	
	public boolean addBiglietto(Biglietto biglietto);
	
	public boolean updateBiglietto(Biglietto biglietto);
	
	//Restituisce quanti biglietti sono stati comprati per uno spettacolo
	public int qtBigliettiByIdSpettacolo(String idSpettacolo);
	
	public List<Biglietto> getAllBigliettiByCodCliente(String codCliente);
}
