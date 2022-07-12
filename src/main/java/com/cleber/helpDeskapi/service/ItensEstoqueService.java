package com.cleber.helpDeskapi.service;

import java.util.List;
import java.util.Optional;

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
	
	public ItensEstoque update(ItensEstoqueDto itens,Integer id ) {
		ItensEstoque itenDb = findById(id);
		itenDb.setCodigo(itens.getCodigo());
		itenDb.setDescricao(itens.getDescricao());
		itenDb.setQuantidade(itens.getQuantidade());
		repository.save(itenDb);
		return itenDb;
	}

	public List<ItensEstoque> findAll() {
		return repository.findAll();
	}
	
	public ItensEstoque findById(Integer id) {
		Optional<ItensEstoque> itens = repository.findById(id);
		return itens.get();
	}

}
