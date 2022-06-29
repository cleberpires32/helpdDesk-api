package com.cleber.helpDeskapi.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.cleber.helpDeskapi.domain.Chamado;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class ChamadoDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	@JsonIgnore
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataAbertura = LocalDateTime.now();
	@JsonIgnore
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataFechamento;
	@NotNull(message = "O campo Status é requerido")
	private Integer status;
	@NotNull(message = "O campo Prioridade é requerido")
	private Integer prioridade;
	@NotNull(message = "O campo Titulo é requerido")
	private String titulo;
	@NotNull(message = "O campo Observação é requerido")
	private String observacoes;
	@NotNull(message = "O campo Cliente é requerido")
	private Integer cliente;
	private String nomeCliente;
	@NotNull(message = "O campo Tecnico é requerido")
	private Integer tecnico;
	private String nomeTecnico;
	
	public ChamadoDto() {
		super();
	}

	public ChamadoDto(Integer id, LocalDateTime dataAbertura, LocalDateTime dataFechamento, Integer status,
			Integer prioridade, String titulo, String observacoes, Integer cliente, String nomeCliente, Integer tecnico,
			String nomeTecnico) {
		super();
		this.id = id;
		this.dataAbertura = dataAbertura;
		this.dataFechamento = dataFechamento;
		this.status = status;
		this.prioridade = prioridade;
		this.titulo = titulo;
		this.observacoes = observacoes;
		this.cliente = cliente;
		this.nomeCliente = nomeCliente;
		this.tecnico = tecnico;
		this.nomeTecnico = nomeTecnico;
	}

	public ChamadoDto(Chamado ch) {

		this.id = ch.getId();
		this.dataAbertura = ch.getDataAbertura();
		this.dataFechamento = ch.getDataFechamento();
		this.status = ch.getStatus().getCodigo();
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
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
