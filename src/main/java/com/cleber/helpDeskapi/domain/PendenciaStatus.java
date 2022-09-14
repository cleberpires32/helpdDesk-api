package com.cleber.helpDeskapi.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.cleber.helpDeskapi.domain.enums.Status;
import com.cleber.helpDeskapi.dtos.PendenciaStatusDto;

@Entity
public class PendenciaStatus implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descricao;
	private LocalDate dataCriacao;
	@Enumerated(EnumType.ORDINAL)
	private Status status;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "chamado")
	private Chamado chamado;

	
	public PendenciaStatus() {
	}
	
	public PendenciaStatus(Long id, String descricao, LocalDate dataCriacao, Status status, Chamado chamado) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.dataCriacao = dataCriacao;
		this.status = status;
		this.chamado = chamado;
	}
	
	public PendenciaStatus(PendenciaStatusDto dto) {
		super();
		this.descricao = dto.getDescricao();
		this.dataCriacao = LocalDate.now();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(String dataCriacao) {
		this.dataCriacao = LocalDate.parse(dataCriacao, DateTimeFormatter.ofPattern("dd/MM/yyyy"));;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Chamado getChamado() {
		return chamado;
	}

	public void setChamado(Chamado chamado) {
		this.chamado = chamado;
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
		PendenciaStatus other = (PendenciaStatus) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "PendenciaStatus [id=" + id + ", descricao=" + descricao + ", dataCriacao=" + dataCriacao + ", status="
				+ status + ", chamado=" + chamado + "]";
	}

	
}
