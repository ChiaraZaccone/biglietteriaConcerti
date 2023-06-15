package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Cliente;

public interface IClienteService {
	
	public List<Cliente>getAllCliente();
	
	public Cliente getClienteByCodCliente (String codCliente);
	
	public boolean existsClienteByCodCliente (String codCliente);

}
