package com.cleber.helpDeskapi.service;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cleber.helpDeskapi.domain.ItensEstoque;
import com.cleber.helpDeskapi.domain.PedidoEstoque;
import com.cleber.helpDeskapi.repository.ItensEstoqueRepository;
import com.cleber.helpDeskapi.repository.PedidoEstoqueRepository;

@Service
public class PedidoEstoqueService {

	@Autowired
	private PedidoEstoqueRepository repository;

	@Autowired
	private ItensEstoqueRepository itensEstoqueRepository;

	public void create(PedidoEstoque pedidoEstoque) {
		repository.save(pedidoEstoque);
	}

	@Transactional
	public void saveAndFlush(PedidoEstoque pedido) {
		repository.saveAndFlush(pedido);
	}
	
	@Transactional
	public void saveAllPedidos(List<PedidoEstoque> pedido) {
		repository.saveAll(pedido);
	}

	@Transactional
	public void delete(Integer chamadoId, Integer[] itensPedidos) {
		List<Integer> itens = Arrays.asList(itensPedidos);
		List<PedidoEstoque> pedidosEstoqueRecolhido = recolhePedidosEstoque(itens);

		repository.remover(chamadoId, itens);

		retornaQtItensToEstoque(chamadoId, itens, pedidosEstoqueRecolhido);
	}

	private List<PedidoEstoque> recolhePedidosEstoque(List<Integer> itens) {
		return repository.findByAllQuery(itens);
	}

	private void retornaQtItensToEstoque(Integer chamadoId, List<Integer> idsItens, List<PedidoEstoque> pedidos) {

		List<ItensEstoque> alteraItens = itensEstoqueRepository.findAllById(idsItens);
		for (ItensEstoque itensEstoque : alteraItens) {
			for (PedidoEstoque pedido : pedidos) {
				if (itensEstoque.getId() == pedido.getItensEstoque().getId()
						&& pedido.getChamado().getId() == chamadoId) {
					itensEstoque.setQuantidade(pedido.getQuantidadeSolicitada() + itensEstoque.getQuantidade());
				}
			}
		}

		itensEstoqueRepository.saveAll(alteraItens);
	}

	public List<PedidoEstoque> pedidosEstoquesPorChamado(Integer Id) {
		return repository.findByAllporChamado(Id);
	}
}
