package com.cleber.helpDeskapi.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.cleber.helpDeskapi.dtos.ItensEstoqueDto;

@Entity
public class ItensEstoque {

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
	
	@ManyToMany(mappedBy = "itensEstoque")
	private List<Chamado> listaChamado = new ArrayList<>();

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

	public List<Chamado> getListaChamado() {
		return listaChamado;
	}

	public void setListaChamado(List<Chamado> listaChamado) {
		this.listaChamado = listaChamado;
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
