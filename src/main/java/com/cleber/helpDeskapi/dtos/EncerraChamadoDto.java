package com.cleber.helpDeskapi.dtos;

import java.io.Serializable;
import java.time.LocalDate;

public class EncerraChamadoDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer idChamado;
	private LocalDate dataEncerramento;
	
	public EncerraChamadoDto() {}
	
	public EncerraChamadoDto(Integer idChamado, LocalDate dataEncerramento) {
		super();
		this.idChamado = idChamado;
		this.dataEncerramento = dataEncerramento;
	}

	
	public Integer getIdChamado() {
		return idChamado;
	}
	public void setIdChamado(Integer idChamado) {
		this.idChamado = idChamado;
	}
	public LocalDate getDataEncerramento() {
		return dataEncerramento;
	}
	public void setDataEncerramento(LocalDate dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}


}
