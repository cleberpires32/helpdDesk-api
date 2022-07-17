package com.cleber.helpDeskapi.repository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cleber.helpDeskapi.domain.Chamado;
import com.cleber.helpDeskapi.domain.ItensEstoque;
import com.cleber.helpDeskapi.domain.PedidoEstoque;

@Repository
public interface PedidoEstoqueRepository extends JpaRepository<PedidoEstoque, Integer>{

	@Query(value = "SELECT p FROM PedidoEstoque p where p.chamado = ?1 and p.itensEstoque = ?2 ")
	public List<PedidoEstoque> findPedidoEstoqueByChamadoAndItenEstoque (Chamado chamado_id, ItensEstoque itens_estoque_id) ;
	
	@Modifying
	@Query(value = "DELETE FROM PedidoEstoque p WHERE p.chamado.id = :chamadoId and p.itensEstoque.id IN :itensPedidos")
	public void remover(@Param("chamadoId") Integer chamadoId, @Param("itensPedidos") Collection<Integer> itensPedidos);
 
}