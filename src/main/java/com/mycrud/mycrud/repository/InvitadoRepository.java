package com.mycrud.mycrud.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mycrud.mycrud.models.Evento;
import com.mycrud.mycrud.models.Invitado;

public interface InvitadoRepository extends CrudRepository<Invitado, String>{
	public Iterable<Invitado> findByEvento(Evento evento);
	public Invitado findById(Long id);

}
