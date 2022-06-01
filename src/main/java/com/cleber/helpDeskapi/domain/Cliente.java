package com.cleber.helpDeskapi.domain;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa{
	
	public List<Chamado> chamados = new ArrayList<>();

	public Cliente() {
		super();
	}

	public Cliente(Integer id, String nome, String email, String cpf, String senha) {
		super(id, nome, email, cpf, senha);
	}

}
