package com.cleber.helpDeskapi.dtos;

import java.io.Serializable;

import com.cleber.helpDeskapi.domain.PendenciaStatus;


public class PendenciaStatusDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private String descricao;
	private ChamadoDto chamado;

	public PendenciaStatusDto(String descricao, String dataCriacao, ChamadoDto chamado, Long id) {
		super();
		this.descricao = descricao;
		this.chamado = chamado;
		this.id = id;
	}
	public PendenciaStatusDto(PendenciaStatus entity) {
		super();
		this.descricao = entity.getDescricao();
		this.chamado = new ChamadoDto(entity.getChamado());
		this.id = entity.getId();
	}

	public PendenciaStatusDto() {}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public ChamadoDto getChamadoId() {
		return chamado;
	}

	public void setChamadoId(ChamadoDto chamado) {
		this.chamado = chamado;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}


}
