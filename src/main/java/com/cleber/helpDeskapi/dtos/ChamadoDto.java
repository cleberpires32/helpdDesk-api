package com.cleber.helpDeskapi.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;

import com.cleber.helpDeskapi.domain.Chamado;
import com.cleber.helpDeskapi.domain.ItensEstoque;
import com.cleber.helpDeskapi.domain.Servico;
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
	@JsonIgnore
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataEntrega;
	@NotNull(message = "O campo Status é requerido")
	private Integer status;
	@NotNull(message = "O campo Prioridade é requerido")
	private Integer prioridade;
	@NotNull(message = "O campo Titulo é requerido")
	private String titulo;
	@NotNull(message = "O campo Modelo é requerido")
	private String modelo;
	@NotNull(message = "O campo Recibo é requerido")
	private String recibo;
	@NotNull(message = "O campo Observação é requerido")
	private String observacoes;
	private String telefoneCliente;
	@NotNull(message = "O campo Cliente é requerido")
	private Integer cliente;
	private String nomeCliente;
	@NotNull(message = "O campo Tecnico é requerido")
	private Integer tecnico;
	private String nomeTecnico;
	private Set<ItensEstoque> itensEstoque = new HashSet<>();
	private List<Servico> servicos = new ArrayList<>();
	private boolean adicionarIten;

	public ChamadoDto() {
		super();
	}

	public ChamadoDto(Integer id, LocalDateTime dataAbertura, LocalDateTime dataFechamento, LocalDateTime dataEntrega,Integer status,
			Integer prioridade, String titulo, String modelo,String recibo,  String observacoes, String telefoneCliente, Integer cliente, String nomeCliente, Integer tecnico,
			String nomeTecnico, List<Servico> servicos, boolean adicionarIten) {
		super();
		this.id = id;
		this.dataAbertura = dataAbertura;
		this.dataFechamento = dataFechamento;
		this.dataEntrega = dataEntrega;
		this.status = status;
		this.prioridade = prioridade;
		this.titulo = titulo;
		this.modelo = modelo;
		this.recibo = recibo;
		this.observacoes = observacoes;
		this.telefoneCliente = telefoneCliente;
		this.cliente = cliente;
		this.nomeCliente = nomeCliente;
		this.tecnico = tecnico;
		this.nomeTecnico = nomeTecnico;
		this.servicos.addAll(servicos);
		this.adicionarIten = adicionarIten;
	}

	public ChamadoDto(Chamado ch) {

		this.id = ch.getId();
		this.dataAbertura = ch.getDataAbertura();
		this.dataFechamento = ch.getDataFechamento();
		this.dataEntrega = ch.getDataEntrega();
		this.status = ch.getStatus().getCodigo();
		this.prioridade = ch.getPrioridade().getCodigo();
		this.titulo = ch.getTitulo();
		this.modelo = ch.getModelo();
		this.recibo = ch.getRecibo();
		this.observacoes = ch.getObservacoes();
		this.telefoneCliente = ch.getCliente().getTelefone();
		this.cliente = ch.getCliente().getId();
		this.nomeCliente = ch.getCliente().getNome();
		this.tecnico = ch.getTecnico().getId();
		this.nomeTecnico = ch.getTecnico().getNome();
		ch.getPedidoEstoque().forEach(i ->{
			this.itensEstoque.add(i.getItensEstoque());
		});
		this.servicos.addAll(ch.getServicos());
		
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

	public LocalDateTime getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDateTime dataEntrega) {
		this.dataEntrega = dataEntrega;
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

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getRecibo() {
		return recibo;
	}

	public void setRecibo(String recibo) {
		this.recibo = recibo;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public String getTelefoneCliente() {
		return telefoneCliente;
	}

	public void setTelefoneCliente(String telefoneCliente) {
		this.telefoneCliente = telefoneCliente;
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

	public Set<ItensEstoque> getItensEstoque() {
		return itensEstoque;
	}

	public void setItensEstoque(Set<ItensEstoque> itensEstoque) {
		this.itensEstoque = itensEstoque;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public Boolean getAdicionarIteno() {
		return adicionarIten;
	}

	public void setAdicionarIten(Boolean adicionarIten) {
		this.adicionarIten = adicionarIten;
	}

}
