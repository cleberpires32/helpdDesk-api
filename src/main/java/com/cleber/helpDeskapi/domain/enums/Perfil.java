package com.cleber.helpDeskapi.domain.enums;

public enum Perfil {

	ADMIN(0, "ROLE_ADMIN"), TECNICO(1, "ROLE_TECNICO"), CLIENTE(2, "ROLE_CLIENTE");

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
