package com.cleber.helpDeskapi.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

public class PedidoEstoqueDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotBlank
	private Integer chamadoId;
	@NotBlank
	private List<Integer> itensEstoqueId = new ArrayList<>();

	public PedidoEstoqueDto(Integer chamadoId, List<Integer> itensEstoqueId) {
		super();
		this.chamadoId = chamadoId;
		this.itensEstoqueId = itensEstoqueId;
	}
	
	public Integer getChamadoId() {
		return chamadoId;
	}
	public void setChamadoId(Integer chamadoId) {
		this.chamadoId = chamadoId;
	}
	public List<Integer> getItensEstoqueId() {
		return itensEstoqueId;
	}
	public void setItensEstoqueId(List<Integer> itensEstoqueId) {
		this.itensEstoqueId = itensEstoqueId;
	}


}
