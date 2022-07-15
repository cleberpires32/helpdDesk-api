package com.cleber.helpDeskapi.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cleber.helpDeskapi.domain.PedidoEstoque;
import com.cleber.helpDeskapi.repository.PedidoEstoqueRepository;

@Service
public class PedidoEstoqueService {

	@Autowired
	private PedidoEstoqueRepository repository;

	public void create(PedidoEstoque pedidoEstoque) {
		repository.save(pedidoEstoque);
	}

	@Transactional
	public void saveAndFlush(PedidoEstoque pedido) {
		repository.saveAndFlush(pedido);
	}
}
