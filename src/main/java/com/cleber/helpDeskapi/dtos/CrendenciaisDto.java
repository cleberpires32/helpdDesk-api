package com.cleber.helpDeskapi.dtos;

/**
 * class serve para fazer a conversão por que virá na requisição de usuario do
 * login
 **/
public class CrendenciaisDto {

	private String senha;
	private String email;

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
