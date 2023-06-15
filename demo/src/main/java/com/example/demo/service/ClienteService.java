package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Cliente;
import com.example.demo.repository.ClienteRepository;

@Service
public class ClienteService implements IClienteService {

	@Autowired
	private ClienteRepository clientrepo;

	@Override
	public List<Cliente> getAllCliente() {
		List<Cliente> cList = new ArrayList<>();
		clientrepo.findAll().forEach(c -> cList.add(c));
		return cList;
	}

	@Override
	public Cliente getClienteByCodCliente(String codCliente) {
		Cliente cliente = new Cliente();
		cliente = clientrepo.findClienteByCodCliente(codCliente);
		return cliente;
	}

	@Override
	public boolean existsClienteByCodCliente(String codCliente) {

		boolean exists =false;

		if (clientrepo.findClienteByCodCliente(codCliente) == null) {
			exists = false;
		} else {
			exists=true;
		}
		return exists;

	}
}
