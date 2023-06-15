package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Cliente;

public interface ClienteRepository extends CrudRepository <Cliente,String>{
	
	Cliente findClienteByCodCliente (String codCliente);

}
