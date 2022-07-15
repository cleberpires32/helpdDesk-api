package com.cleber.helpDeskapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cleber.helpDeskapi.domain.Chamado;
import com.cleber.helpDeskapi.domain.ItensEstoque;
import com.cleber.helpDeskapi.domain.PedidoEstoque;

public interface PedidoEstoqueRepository extends JpaRepository<PedidoEstoque, Integer>{

	@Query(value = "SELECT p FROM PedidoEstoque p where p.chamado = ?1 and p.itensEstoque = ?2 ")
	public List<PedidoEstoque> findPedidoEstoqueByChamadoAndItenEstoque (Chamado chamado_id, ItensEstoque itens_estoque_id) ;
 
}