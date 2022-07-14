package com.cleber.helpDeskapi.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.cleber.helpDeskapi.domain.Tecnico;
import com.cleber.helpDeskapi.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class TecnicoDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	protected Integer id;
	@NotNull
	protected String nome;
	@NotNull
	protected String email;
	@NotNull
	protected String cpf;
	protected String telefone;
	protected String senha;
	protected Set<Integer> perfis = new HashSet<>();
	@JsonIgnore
	@DateTimeFormat(iso = ISO.DATE_TIME,pattern = "yyyy-MM-dd HH:mm:ss" )
	protected LocalDateTime dataCriacao = LocalDateTime.now();

	public TecnicoDto() {
	}


	public TecnicoDto(Tecnico tec) {
		super();
		this.id = tec.getId();
		this.nome = tec.getNome();
		this.email = tec.getEmail();
		this.cpf = tec.getCpf();
		this.telefone = tec.getTelefone();
		this.senha = tec.getSenha();
		this.perfis = tec.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
		this.dataCriacao = tec.getDataCriacao();
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}


	public void addPerfis(Perfil perfil) {
		this.perfis.add(perfil.getCodigo());
	}


	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}


	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	
}
