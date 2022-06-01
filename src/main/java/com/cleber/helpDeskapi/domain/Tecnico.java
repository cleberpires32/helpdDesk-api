package com.cleber.helpDeskapi.domain;

import java.util.ArrayList;
import java.util.List;

public class Tecnico extends Pessoa {

	public List<Chamado> chamados = new ArrayList<>();

	public Tecnico() {
		super();
	}

	public Tecnico(Integer id, String nome, String email, String cpf, String senha) {
		super(id, nome, email, cpf, senha);
	}

}
