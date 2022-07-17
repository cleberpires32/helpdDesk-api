package com.cleber.helpDeskapi.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cleber.helpDeskapi.service.PedidoEstoqueService;

@RestController
@RequestMapping(value = "/pedidosEstoques")
public class PedidoEstoqueResource {
	
	@Autowired
	private PedidoEstoqueService pedidoEstoqueService;
	
	@DeleteMapping(value = "/{chamadoId}/{itensEstoqueId}")
	public ResponseEntity<Void> delete(@PathVariable Integer chamadoId, @PathVariable Integer[] itensEstoqueId){
		pedidoEstoqueService.delete(chamadoId, itensEstoqueId);;
		return ResponseEntity.noContent().build();
		
	}

}
