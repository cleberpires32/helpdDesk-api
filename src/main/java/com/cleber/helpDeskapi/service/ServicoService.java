package com.cleber.helpDeskapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cleber.helpDeskapi.domain.Servico;
import com.cleber.helpDeskapi.dtos.ServicoDto;
import com.cleber.helpDeskapi.repository.ServicoRepository;

@Service
public class ServicoService {

	@Autowired
	private ServicoRepository servicoRepository;
	
	public Servico savar(ServicoDto servicoDto) {
		Servico servico = new Servico(servicoDto);
		return servicoRepository.save(servico);
	}

}
