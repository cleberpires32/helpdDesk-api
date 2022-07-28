package com.cleber.helpDeskapi.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.cleber.helpDeskapi.domain.Servico;

public class ServicoDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	@NotEmpty
	@Size(min = 5, max = 300)
	private String descricao;
	@NotNull
	private BigDecimal valor;

	public ServicoDto(Integer id, @NotBlank String descricao, @NotBlank BigDecimal valor) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
	}
	
	public ServicoDto(Servico servico) {
		super();
		this.id = servico.getId();
		this.descricao = servico.getDescricao();
		this.valor = servico.getValor();
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

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
