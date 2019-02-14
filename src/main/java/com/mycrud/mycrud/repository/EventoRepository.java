package com.mycrud.mycrud.repository;

import org.springframework.data.repository.CrudRepository;

import com.mycrud.mycrud.models.Evento;

public interface EventoRepository extends CrudRepository<Evento, String>{
	public Evento findById(Long id);
}
