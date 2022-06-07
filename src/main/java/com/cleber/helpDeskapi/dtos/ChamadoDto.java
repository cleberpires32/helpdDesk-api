package com.cleber.helpDeskapi.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.cleber.helpDeskapi.domain.Chamado;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ChamadoDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataAbertura = LocalDateTime.now();
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataFechamento;
	private String status;
	private Integer prioridade;
	private String titulo;
	private String observacoes;
	private Integer cliente;
	private String nomeCliente;
	private Integer tecnico;
	private String nomeTecnico;
	
	public ChamadoDto(Chamado ch) {

		this.id = ch.getId();
		this.dataAbertura = ch.getDataAbertura();
		this.dataFechamento = ch.getDataFechamento();
		this.status = ch.getStatus().getDescricao();
		this.prioridade = ch.getPrioridade().getCodigo();
		this.titulo = ch.getTitulo();
		this.observacoes = ch.getObservacoes();
		this.cliente = ch.getCliente().getId();
		this.nomeCliente = ch.getCliente().getNome();
		this.tecnico = ch.getTecnico().getId();
		this.nomeTecnico = ch.getTecnico().getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public LocalDateTime getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(LocalDateTime dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public Integer getTecnico() {
		return tecnico;
	}

	public void setTecnico(Integer tecnico) {
		this.tecnico = tecnico;
	}

	public String getNomeTecnico() {
		return nomeTecnico;
	}

	public void setNomeTecnico(String nomeTecnico) {
		this.nomeTecnico = nomeTecnico;
	}
}
