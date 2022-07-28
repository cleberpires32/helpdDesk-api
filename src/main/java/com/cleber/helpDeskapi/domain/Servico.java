package com.cleber.helpDeskapi.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import com.cleber.helpDeskapi.dtos.ServicoDto;

@Entity
public class Servico implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	@NotBlank
	private String descricao;
	@Column
	private BigDecimal valor;
	
	public Servico() {}
	
	public Servico(Integer id, String descricao, BigDecimal valor) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
	}

	public Servico(ServicoDto dto) {
		super();
		this.id = dto.getId();
		this.descricao = dto.getDescricao();
		this.valor = dto.getValor();
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
		Servico other = (Servico) obj;
		return Objects.equals(id, other.id);
	}

}
