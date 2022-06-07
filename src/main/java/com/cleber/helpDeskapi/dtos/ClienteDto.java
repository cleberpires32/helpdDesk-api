package com.cleber.helpDeskapi.dtos;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.cleber.helpDeskapi.domain.Cliente;
import com.cleber.helpDeskapi.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ClienteDto {

	protected Integer id;
	@NotNull
	protected String nome;
	@NotNull
	protected String email;
	@NotNull
	protected String cpf;
	protected String senha;
	protected Set<Integer> perfis = new HashSet<>();
	@JsonFormat(pattern = "dd/MM/yyyy") //Tras as datas do banco no formato prescrito o pattern
	protected LocalDateTime dataCriacao = LocalDateTime.now();

	public ClienteDto() {
	}


	public ClienteDto(Cliente cli) {
		super();
		this.id = cli.getId();
		this.nome = cli.getNome();
		this.email = cli.getEmail();
		this.cpf = cli.getCpf();
		this.senha = cli.getSenha();
		this.perfis = cli.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
		this.dataCriacao = cli.getDataCriacao();
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
