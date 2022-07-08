package com.cleber.helpDeskapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cleber.helpDeskapi.domain.ItensEstoque;
import com.cleber.helpDeskapi.dtos.ItensEstoqueDto;
import com.cleber.helpDeskapi.repository.ItensEstoqueRepository;

@Service
public class ItensEstoqueService {

	@Autowired
	private ItensEstoqueRepository repository;

	public ItensEstoque create(ItensEstoqueDto dto) {
		dto.setId(null);
		ItensEstoque iten = new ItensEstoque(dto);
		return repository.save(iten);
	}

}
