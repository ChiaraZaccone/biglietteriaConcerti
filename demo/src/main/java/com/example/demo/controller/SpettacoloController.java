package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Spettacolo;
import com.example.demo.errors.ResourceNotFound;
import com.example.demo.info.SpettacoloInfo;
import com.example.demo.service.ISpettacoloService;

@RestController
@RequestMapping(value="biglietteriaspettacoli")
@CrossOrigin
public class SpettacoloController {

	@Autowired
	private ISpettacoloService spettService;

	@GetMapping(value = "spettacoli", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<SpettacoloInfo>> getAllSpettacoliList() {
		List<Spettacolo> spettacoli = new ArrayList<>();
		spettacoli = spettService.getAllSpettacoli();

		if (spettacoli.size() == 0) {
			 throw new ResourceNotFound("Non ci sono spettacoli in programma!");
		}

		List<SpettacoloInfo> spettInfo = new ArrayList<>();

		for (int i = 0; i < spettacoli.size(); i++) {
			SpettacoloInfo sInfo = new SpettacoloInfo();
			BeanUtils.copyProperties(spettacoli.get(i), sInfo);
			spettInfo.add(sInfo);
		}

		return new ResponseEntity<List<SpettacoloInfo>>(spettInfo, HttpStatus.OK);
	}
	
	
	@GetMapping(value="spettacoli/{idSpettacolo}", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<SpettacoloInfo> getSpettacoloById(@PathVariable("idSpettacolo") String idSpettacolo){
		
		if(!spettService.spettacoloExistsById(idSpettacolo)) {
			 throw new ResourceNotFound("Lo spettacolo "+idSpettacolo+" non è presente!");
		}
		Spettacolo spettacolo = new Spettacolo();
		spettacolo = spettService.getSpettacoloById(idSpettacolo);
		SpettacoloInfo spettInfo = new SpettacoloInfo();
		BeanUtils.copyProperties(spettacolo, spettInfo);
		
		return new ResponseEntity<SpettacoloInfo>(spettInfo, HttpStatus.OK);
	}
	
	
}
