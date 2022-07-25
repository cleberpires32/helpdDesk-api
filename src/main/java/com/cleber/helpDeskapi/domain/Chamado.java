package com.cleber.helpDeskapi.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.cleber.helpDeskapi.domain.enums.Prioridade;
import com.cleber.helpDeskapi.domain.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Chamado implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataAbertura = LocalDateTime.now();
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataFechamento;
	private Status status;
	private Prioridade prioridade;
	private String titulo;
	private String observacoes;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "tecnico_id")
	private Tecnico tecnico;
/*	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "pedido_estoque",joinColumns = @JoinColumn(name = "chamado_id"),
	inverseJoinColumns = @JoinColumn(name = "itensEstoque_id"))
	private List<ItensEstoque> itensEstoque = new ArrayList<>();
*/
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "chamado", fetch = FetchType.LAZY)
	private Set<PedidoEstoque> pedidoEstoque = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "chamado", fetch = FetchType.LAZY)
	private Set<Servico> servicos = new HashSet<>();
	
	public Chamado() {}

	public Chamado(Integer id, String titulo, String observacoes, Prioridade prioridade, Status status, Tecnico tecnico,
			Cliente cliente) {

		this.id = id;
		this.titulo = titulo;
		this.observacoes = observacoes;
		this.status = status;
		this.prioridade = prioridade;
		this.tecnico = tecnico;
		this.cliente = cliente;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Prioridade getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Prioridade prioridade) {
		this.prioridade = prioridade;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}
/*	
	public List<ItensEstoque> getItensEstoque() {
		return itensEstoque;
	}

	public void setItensEstoque(List<ItensEstoque> itensEstoque) {
		this.itensEstoque = itensEstoque;
	}
*/
	
	public Set<PedidoEstoque> getPedidoEstoque() {
		return pedidoEstoque;
	}

	public void setPedidoEstoque(Set<PedidoEstoque> pedidoEstoque) {
		this.pedidoEstoque = pedidoEstoque;
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
		Chamado other = (Chamado) obj;
		return Objects.equals(id, other.id);
	}


}
