package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Spettacolo;
import com.example.demo.repository.ISpettacoloRepository;

@Service
public class SpettacoloService implements ISpettacoloService{

	@Autowired
	private ISpettacoloRepository spettrepo;
	
	
	@Override
	public List<Spettacolo> getAllSpettacoli() {
		List<Spettacolo> spettacoli = new ArrayList<>();
		spettrepo.findAll().forEach(s->spettacoli.add(s));
		return spettacoli;
	}

	@Override
	public Spettacolo getSpettacoloById(String idSpettacolo) {
		Spettacolo spettacolo = new Spettacolo();
		spettacolo = spettrepo.findById(idSpettacolo).get();
		return spettacolo;
	}

	@Override
	public boolean spettacoloExistsById(String idSpettacolo) {
		
		return spettrepo.existsById(idSpettacolo);
	}

}
