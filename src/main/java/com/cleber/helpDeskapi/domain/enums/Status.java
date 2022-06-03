package com.cleber.helpDeskapi.domain.enums;

public enum Status {

	ABERTO(0, "ABERTO"), ANDAMENTO(1, "ANDAMENTO"), CANCELADO(2, "CANCELADO"), ENCERRADO(3, "ENCERRADO");

	private Integer codigo;
	private String descricao;

	private Status(Integer codigo, String descricao) {
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

	public static Status toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}

		for (Status x : Status.values()) {
			if (x.getCodigo().equals(cod)) {
				return x;
			}
		}

		throw new IllegalArgumentException("Status inválido.");
	}
}
