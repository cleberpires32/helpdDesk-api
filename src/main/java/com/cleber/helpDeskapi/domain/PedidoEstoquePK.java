package com.cleber.helpDeskapi.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class PedidoEstoquePK implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer chamadoId;
	private Integer itensEstoqueId;
	
	public Integer getChamadoId() {
		return chamadoId;
	}
	public void setChamadoId(Integer chamadoId) {
		this.chamadoId = chamadoId;
	}
	public Integer getItensEstoqueId() {
		return itensEstoqueId;
	}
	public void setItensEstoqueId(Integer itensEstoqueId) {
		this.itensEstoqueId = itensEstoqueId;
	}
}
