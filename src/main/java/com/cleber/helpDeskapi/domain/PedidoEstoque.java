package com.cleber.helpDeskapi.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.validation.constraints.NotEmpty;

@Entity
public class PedidoEstoque {

	@EmbeddedId
	private PedidoEstoquePK pk = new PedidoEstoquePK();
	
	@MapsId("chamadoId")
    @ManyToOne
    @JoinColumn(name="chamadoId", referencedColumnName="id")
	private Chamado chamado;
	
	@MapsId("itensEstoqueId")
	@ManyToOne
	@JoinColumn(name = "itensEstoqueId", referencedColumnName = "id")
	private ItensEstoque itensEstoque = new ItensEstoque();
	
	@Column
	private Integer quantidadeSolicitada;

	public Chamado getChamado() {
		return chamado;
	}

	public void setChamado(Chamado chamado) {
		this.chamado = chamado;
	}

	public Integer getQuantidadeSolicitada() {
		return quantidadeSolicitada;
	}

	public void setQuantidadeSolicitada(Integer quantidadeSolicitada) {
		this.quantidadeSolicitada = quantidadeSolicitada;
	}
	
	public ItensEstoque getItensEstoque() {
		return itensEstoque;
	}

	public void setItensEstoque(ItensEstoque itensEstoque) {
		this.itensEstoque = itensEstoque;
	}
	
	public PedidoEstoquePK getPk() {
		return pk;
	}

	public void setPk(PedidoEstoquePK pk) {
		this.pk = pk;
	}

	@Override
	public int hashCode() {
		return Objects.hash(chamado, itensEstoque);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PedidoEstoque other = (PedidoEstoque) obj;
		return Objects.equals(chamado, other.chamado) && Objects.equals(itensEstoque, other.itensEstoque);
	}
	



}
