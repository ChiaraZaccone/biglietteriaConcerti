package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.entity.Biglietto;
import com.example.demo.entity.Spettacolo;
import com.example.demo.errors.ResourceNotFound;
import com.example.demo.errors.ResourceUnavalaible;
import com.example.demo.info.BigliettoInfo;
import com.example.demo.service.IBigliettoService;
import com.example.demo.service.ISpettacoloService;

@RestController
@RequestMapping(value="biglietteriaspettacoli")
@CrossOrigin
public class BigliettoController {
	
	@Autowired
	private IBigliettoService bigliettoService;
	
	@Autowired
	private ISpettacoloService spettacoloService;
	
	@GetMapping(value="biglietti",produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<BigliettoInfo>> getAllBigliettiList(){
		
		List<Biglietto> biglietti = new ArrayList<>();
		biglietti = bigliettoService.getAllBiglietti();
		
		List<BigliettoInfo> bigliettiInfo = new ArrayList<>();
		
		for(int i=0; i<biglietti.size();i++) {
			BigliettoInfo bInfo = new BigliettoInfo();
			BeanUtils.copyProperties(biglietti.get(i), bInfo);
			bigliettiInfo.add(bInfo);
		}
		
		return new ResponseEntity<List<BigliettoInfo>>(bigliettiInfo, HttpStatus.OK);
		
	}
	
	
	@GetMapping(value="biglietto/{codCliente}", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity <List<BigliettoInfo>> getAllBigliettiByCodCliente(@PathVariable ("codCliente") String codCliente){
		List<Biglietto> listBS = new ArrayList<>();
		List<BigliettoInfo> listI = new ArrayList<>();
		listBS = bigliettoService.getAllBigliettiByCodCliente(codCliente);
		if(listBS.size()==0) {
			throw new ResourceNotFound("Non hai ancora effettuato gli ordini");
		}
		
		for (int i = 0; i < listBS.size(); i++) {
			BigliettoInfo ob = new BigliettoInfo();
			Spettacolo spettacolo = new Spettacolo();
			spettacolo = spettacoloService.getSpettacoloById(listBS.get(i).getIdSpettacolo());
			double prezzoUnit = spettacolo.getPrezzo();    
			Date data = spettacolo.getDataSpettacolo();
			String artista = spettacolo.getArtista();
			String citta = spettacolo.getCitta();
			String luogo = spettacolo.getLuogo();
			ob.setPrezzo(prezzoUnit);
			ob.setData(data);
			ob.setArtista(artista);
			ob.setCitta(citta);
			ob.setLuogo(luogo);
			BeanUtils.copyProperties(listBS.get(i), ob);
			listI.add(ob);
		}
		return new ResponseEntity<List<BigliettoInfo>>(listI, HttpStatus.OK);
	}
	 
	
	@GetMapping(value="biglietti/{codOperazione}", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<BigliettoInfo> getBigliettoByCodOperazione(@PathVariable("codOperazione") int codOperazione){
		
		Biglietto biglietto = new Biglietto();
		
		//se non esiste
		if(!bigliettoService.existsBigliettoByCodOperazione(codOperazione)) {
			throw new ResourceNotFound("Biglietto "+codOperazione+" non presente!");
		}
		biglietto = bigliettoService.getBigliettoByCodOperazione(codOperazione);
		
		BigliettoInfo bInfo = new BigliettoInfo();
		BeanUtils.copyProperties(biglietto, bInfo);
		
		return new ResponseEntity<BigliettoInfo>(bInfo, HttpStatus.OK);
	}
	
	@PostMapping(value="biglietto", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Void> addBiglietto(@RequestBody BigliettoInfo bigliettoInfo, UriComponentsBuilder builder){
		//se la quantità dei biglietti inserita è uguale a 0 o negativa
		if(bigliettoInfo.getQtBiglietti() <= 0) {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
//controllo sui posti disponibili
		int postiDispTot = spettacoloService.getSpettacoloById(bigliettoInfo.getIdSpettacolo())
				                                                                       .getPostiDisp();
		int bigliettiComprati = bigliettoService.qtBigliettiByIdSpettacolo(bigliettoInfo.getIdSpettacolo());
		
		int bigliettiDaComprare = bigliettoInfo.getQtBiglietti();
		//se non sono più disponibili
		if(postiDispTot == bigliettiComprati) {
			throw new ResourceUnavalaible("Biglietti non più disponibili");
		} 
		//se i biglietti comprati + i biglietti richiesti sono più dei biglietti disponibili
		if((bigliettiComprati+bigliettiDaComprare) > postiDispTot) {
			 throw new ResourceUnavalaible("Biglietti disponibili: "+(postiDispTot-bigliettiComprati));
		}
		
		Biglietto biglietto = new Biglietto();
		BeanUtils.copyProperties(bigliettoInfo, biglietto);
		bigliettoService.addBiglietto(biglietto);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("biglietto/{codOperazione}").buildAndExpand(biglietto.getCodOperazione()).toUri());
		return new ResponseEntity<>(headers,HttpStatus.CREATED);
		
	}
	
	
	@PutMapping(value="biglietto", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<BigliettoInfo> updateBiglietto(@RequestBody BigliettoInfo bigliettoInfo, UriComponentsBuilder builder){
		    //se la quantità dei biglietti inserita è uguale a 0 o negativa
				if(bigliettoInfo.getQtBiglietti() <= 0) {
					return new ResponseEntity<>(HttpStatus.FORBIDDEN);
				}
				
		        if(!bigliettoService.existsBigliettoByCodOperazione(bigliettoInfo.getCodOperazione())) {
		        	 throw new ResourceNotFound("Codice operazione non trovato");
		         }
		//controllo sui posti disponibili
				int postiDispTot = spettacoloService.getSpettacoloById(bigliettoInfo.getIdSpettacolo())
						                                                                       .getPostiDisp();
				int bigliettiComprati = bigliettoService.qtBigliettiByIdSpettacolo(bigliettoInfo.getIdSpettacolo());
				
				int bigliettiDaComprare = bigliettoInfo.getQtBiglietti();
				//biglietti comprati per quel codOperazione
				int nBigliettiperCodOperazione = bigliettoService.getBigliettoByCodOperazione(bigliettoInfo.getCodOperazione()).getQtBiglietti();
				//se i biglietti da comprare sono più di quelli già segnati per quel codOperazione
				if(bigliettiDaComprare > nBigliettiperCodOperazione) {
					//se non sono più disponibili
					if(postiDispTot == bigliettiComprati) {
						throw new ResourceUnavalaible("Biglietti non più disponibili");
					} 
				//se i biglietti comprati + i biglietti richiesti sono più dei biglietti disponibili
				if((bigliettiComprati+bigliettiDaComprare) > postiDispTot) {
					 throw new ResourceUnavalaible("Biglietti disponibili: "+(postiDispTot-bigliettiComprati));
				 }
				}
				Biglietto biglietto = new Biglietto();
				BeanUtils.copyProperties(bigliettoInfo, biglietto);
				bigliettoService.updateBiglietto(biglietto);
				
				HttpHeaders headers = new HttpHeaders();
				headers.setLocation(builder.path("biglietto/{codOperazione}").buildAndExpand(biglietto.getCodOperazione()).toUri());
				return new ResponseEntity<BigliettoInfo>(bigliettoInfo,HttpStatus.OK);

}

}
