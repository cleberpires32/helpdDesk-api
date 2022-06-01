package com.cleber.helpDeskapi.domain.enums;

public enum Perfil {

	PESSOA(0, "ROLE_ADMIN"), CLIENTE(1, "ROLE_CLIENTE"), ADMIN(2, "ROLE_CLIENTE"), TECNICO(3, "ROLE_TECNICO");

	private Integer codigo;
	private String descricao;

	private Perfil(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static Perfil toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}

		for (Perfil x : Perfil.values()) {
			if (x.getCodigo().equals(cod)) {
				return x;
			}
		}

		throw new IllegalArgumentException("Perfil inválido.");
	}
}
