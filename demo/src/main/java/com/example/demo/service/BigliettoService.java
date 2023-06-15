package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Biglietto;

import com.example.demo.repository.IBigliettoRepository;

@Service
public class BigliettoService implements IBigliettoService{

	@Autowired
	private IBigliettoRepository biglRepo;
	
	@Override
	public List<Biglietto> getAllBiglietti() {
		List<Biglietto> biglietti = new ArrayList<>();
		biglRepo.findAll().forEach(b->biglietti.add(b));
		return biglietti;
	}

	@Override
	public Biglietto getBigliettoByCodOperazione(int codOperazione) {
		Biglietto biglietto = new Biglietto();
		biglietto = biglRepo.findById(codOperazione).get();
		return biglietto;
	}

	@Override
	public boolean existsBigliettoByCodOperazione(int codOperazione) {
		
		return biglRepo.existsById(codOperazione);
	}

	//da testare!!
	@Override
	public boolean addBiglietto(Biglietto biglietto) {
		List<Biglietto> biglietti = new ArrayList<>();
		biglRepo.findAll().forEach(b->biglietti.add(b));
		for (Biglietto bigl : biglietti) {
			if(bigl.equals(biglietto)) {
				return false;
				
			}
			
		}
		biglRepo.save(biglietto);
		return true;
	}

	@Override
	public boolean updateBiglietto(Biglietto biglietto) {
		if(biglRepo.existsById(biglietto.getCodOperazione())) {
			biglRepo.save(biglietto);
			return true;
		}
		return false;
	}

	@Override
	public int qtBigliettiByIdSpettacolo(String idSpettacolo) {
	   List<Biglietto> biglietti = new ArrayList<>();
	   biglietti = biglRepo.findByidSpettacolo(idSpettacolo);
	   int bigliettiComprati = 0;
	  for (Biglietto biglietto : biglietti) {
		bigliettiComprati += biglietto.getQtBiglietti();
	}
		return bigliettiComprati;
	}

	@Override
	public List<Biglietto> getAllBigliettiByCodCliente(String codCliente) {
		List<Biglietto> lst= new ArrayList<>();
		lst= biglRepo.findBycodCliente(codCliente);
		return lst;
	}

	
		
	}


