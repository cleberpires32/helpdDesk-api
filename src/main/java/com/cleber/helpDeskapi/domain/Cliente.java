package com.cleber.helpDeskapi.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.cleber.helpDeskapi.domain.enums.Perfil;
import com.cleber.helpDeskapi.dtos.ClienteDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Cliente extends Pessoa{
	
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy = "cliente")
	@JsonIgnore //protege contra a serialização qd chamar pessoa dispensa a chamada do chamados.
	public List<Chamado> chamados = new ArrayList<>();

	public Cliente() {
		super();
		addPerfil(Perfil.CLIENTE);
	}

	public Cliente(Integer id, String nome, String email, String cpf, String telefone, String senha) {
		super(id, nome, email, cpf, telefone, senha);
		addPerfil(Perfil.CLIENTE);
	}

	public List<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}

	public Cliente(ClienteDto dto) {
		this.id = dto.getId();
		this.nome = dto.getNome();
		this.email = dto.getEmail();
		this.cpf = dto.getCpf();
		this.senha = dto.getSenha();
		this.telefone = dto.getTelefone();
	}
}
