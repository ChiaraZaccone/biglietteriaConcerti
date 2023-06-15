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

import com.example.demo.entity.Cliente;
import com.example.demo.info.ClienteInfo;
import com.example.demo.service.IClienteService;

@RestController
@RequestMapping(value="biglietteriaspettacoli")
@CrossOrigin

public class ClienteController {
	@Autowired
	private IClienteService clientservice;
	
	
	@GetMapping(value="cliente", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity <List<ClienteInfo>> getAllCliente(){
		List <Cliente> clienti= new ArrayList<>();
		List <ClienteInfo> listInfo= new ArrayList <>();
		clienti= clientservice.getAllCliente();
		for(int i=0; i<clienti.size();i++) {
			ClienteInfo clienteInfo= new ClienteInfo();
			BeanUtils.copyProperties(clienti.get(i), clienteInfo);
			listInfo.add(clienteInfo);
		}
		
		return new ResponseEntity<List<ClienteInfo>>(listInfo,HttpStatus.OK);
		
	}
	
	@GetMapping(value="cliente/{codCliente}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ClienteInfo> getClienteByCodCliente(@PathVariable("codCliente")String codCliente){
		
		Cliente cliente= new Cliente();
		if(!clientservice.existsClienteByCodCliente(codCliente)) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		cliente= clientservice.getClienteByCodCliente(codCliente);
		ClienteInfo cInfo= new ClienteInfo();
		cInfo.setMessage("ok");
		BeanUtils.copyProperties(cliente, cInfo);
		
		return new ResponseEntity<ClienteInfo>(cInfo,HttpStatus.OK);
}
	
	@GetMapping(value="cliente/{codCliente}/exists", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Boolean> existsClienteByCodCliente(@PathVariable("codCliente")String codCliente){
		boolean exists= clientservice.existsClienteByCodCliente(codCliente);
		return new ResponseEntity<Boolean>(exists,HttpStatus.OK);
	}
	
	

}
