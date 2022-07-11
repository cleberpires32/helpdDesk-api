package com.cleber.helpDeskapi.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class PedidoEstoque {

	@Column
	private Integer chamado_id;
	@Column
	private Integer itensEstoque_id;
	@Column
	private Integer quantidade_solicitada;
	
	public PedidoEstoque() {
	}
	
	public PedidoEstoque(Integer chamado_id, Integer itensEstoque_id, Integer quantidade_solicitada) {
		super();
		this.chamado_id = chamado_id;
		this.itensEstoque_id = itensEstoque_id;
		this.quantidade_solicitada = quantidade_solicitada;
	}
	
	public Integer getChamado_id() {
		return chamado_id;
	}
	public void setChamado_id(Integer chamado_id) {
		this.chamado_id = chamado_id;
	}
	public Integer getItensEstoque_id() {
		return itensEstoque_id;
	}
	public void setItensEstoque_id(Integer itensEstoque_id) {
		this.itensEstoque_id = itensEstoque_id;
	}
	public Integer getQuantidade_solicitada() {
		return quantidade_solicitada;
	}
	public void setQuantidade_solicitada(Integer quantidade_solicitada) {
		this.quantidade_solicitada = quantidade_solicitada;
	}

	@Override
	public int hashCode() {
		return Objects.hash(chamado_id, itensEstoque_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PedidoEstoque other = (PedidoEstoque) obj;
		return Objects.equals(chamado_id, other.chamado_id) && Objects.equals(itensEstoque_id, other.itensEstoque_id);
	}


}
