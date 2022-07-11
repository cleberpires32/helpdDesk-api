package com.cleber.helpDeskapi.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import com.cleber.helpDeskapi.domain.ItensEstoque;

public class ItensEstoqueDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	@NotBlank
	private String descricao;
	@NotBlank
	private String codigo;

	private Integer quantidade;
	
	private BigDecimal valor;
	
	private Boolean vinculoComChamado;
	
	public ItensEstoqueDto() {}
	
	public ItensEstoqueDto(ItensEstoque itens) {
		super();
		this.id = itens.getId();
		this.descricao = itens.getDescricao();
		this.codigo = itens.getCodigo();
		this.quantidade = itens.getQuantidade();
		this.valor = itens.getValor();
		this.vinculoComChamado = false;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Boolean getVinculoComChamado() {
		return vinculoComChamado;
	}

	public void setVinculoComChamado(Boolean vinculoComChamado) {
		this.vinculoComChamado = vinculoComChamado;
	}

}
