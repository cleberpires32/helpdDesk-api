package com.cleber.helpDeskapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cleber.helpDeskapi.domain.Tecnico;
import com.cleber.helpDeskapi.repository.TecnicoRepository;
import com.cleber.helpDeskapi.service.exception.ObjectNotFoundException;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;

	public Tecnico findById(Integer id) {
		Optional<Tecnico> tecnico = tecnicoRepository.findById(id);
		
		return tecnico.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado com id: "+ id));
	}
	
	public List<Tecnico> findAll(){
		return tecnicoRepository.findAll();
	}
}