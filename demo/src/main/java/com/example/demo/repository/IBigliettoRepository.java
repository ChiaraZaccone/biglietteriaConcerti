package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Biglietto;

public interface IBigliettoRepository extends CrudRepository<Biglietto, Integer>{

	List<Biglietto> findByidSpettacolo(String id_spettacolo);
	List<Biglietto> findBycodCliente(String cod_cliente);
	
}
