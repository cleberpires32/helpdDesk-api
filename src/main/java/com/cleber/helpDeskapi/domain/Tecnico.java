package com.cleber.helpDeskapi.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.cleber.helpDeskapi.domain.enums.Perfil;
import com.cleber.helpDeskapi.dtos.TecnicoDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Tecnico extends Pessoa {

	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy = "tecnico")
	@JsonIgnore //Anotetion que ignora este campo quando buscar o tecnico no db para não causar loop inifinit
	private  List<Chamado> chamados = new ArrayList<>();

	public Tecnico() {
		super();
		addPerfil(Perfil.CLIENTE);
	}

	public Tecnico(Integer id, String nome, String email, String cpf, String telefone, String senha) {
		super(id, nome, email, cpf, telefone, senha);
		addPerfil(Perfil.CLIENTE);
	}

	public List<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}
	
	public Tecnico(TecnicoDto dto) {
		this.id = dto.getId();
		this.nome = dto.getNome();
		this.email = dto.getEmail();
		this.cpf = dto.getCpf();
		this.telefone = dto.getTelefone();
		this.senha = dto.getSenha();
		dto.getPerfis().forEach(f ->{
			this.addPerfil(f);
		});
	}

}
