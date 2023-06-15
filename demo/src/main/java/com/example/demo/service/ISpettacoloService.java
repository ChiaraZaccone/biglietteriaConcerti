package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Spettacolo;

public interface ISpettacoloService {

	//Restituisce tutti gli spettacoli
	public List<Spettacolo> getAllSpettacoli();
	
	//Restituisce lo spettacolo corrispondente all'id inserito come parametro
	public Spettacolo getSpettacoloById(String idSpettacolo);
	
	//Restituisce se esiste lo spettacolo corrispondente all'id inserito come parametro 
	public boolean spettacoloExistsById(String idSpettacolo);
	
}
