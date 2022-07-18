package com.cleber.helpDeskapi.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.cleber.helpDeskapi.dtos.ItensEstoqueDto;

@Entity
public class ItensEstoque implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String descricao;
	@Column
	private String codigo;
	@Column
	private BigDecimal valor;
	@Column
	private Integer quantidade;
	
	@Transient
	private Integer quantidadeSolicitada;

	public ItensEstoque() {
	}

	public ItensEstoque(Integer id, String descricao, String codigo, BigDecimal valor, Integer quantidade) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.codigo = codigo;
		this.valor = valor;
		this.quantidade = quantidade;
	}
	
	public ItensEstoque(ItensEstoqueDto itens) {
		super();
		this.id = itens.getId();
		this.descricao = itens.getDescricao();
		this.codigo = itens.getCodigo();
		this.quantidade = itens.getQuantidade();
		this.valor = itens.getValor();
		this.setQuantidadeSolicitada(itens.getQuantidadeSolicitada());
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

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public Integer getQuantidadeSolicitada() {
		return quantidadeSolicitada;
	}

	public void setQuantidadeSolicitada(Integer quantidadeSolicitada) {
		this.quantidadeSolicitada = quantidadeSolicitada;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItensEstoque other = (ItensEstoque) obj;
		return Objects.equals(id, other.id);
	}


}
