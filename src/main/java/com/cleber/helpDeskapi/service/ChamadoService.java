package com.cleber.helpDeskapi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cleber.helpDeskapi.domain.Chamado;
import com.cleber.helpDeskapi.dtos.ChamadoDto;
import com.cleber.helpDeskapi.repository.ChamadoRepository;
import com.cleber.helpDeskapi.service.exception.ObjectNotFoundException;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepository repository;
	
	public ChamadoDto findById(Integer id) {
		Optional<Chamado> op = repository.findById(id);
		op.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado com id: "+id));
		return new ChamadoDto(op.get());
	}

	public List<ChamadoDto> findByAll() {
		List<Chamado> listCh =  repository.findAll();
		List<ChamadoDto> lDto = listCh.stream().map(ch -> new ChamadoDto(ch)).collect(Collectors.toList());
		return lDto;
		
	}
}
