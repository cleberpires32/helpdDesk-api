package com.cleber.helpDeskapi.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cleber.helpDeskapi.domain.ItensEstoque;
import com.cleber.helpDeskapi.dtos.ItensEstoqueDto;
import com.cleber.helpDeskapi.service.ItensEstoqueService;

@RestController
@RequestMapping(value = "/itensEstoques")
public class ItensEstoqueResource {
	
	@Autowired
	private ItensEstoqueService itensService;

	@PostMapping
	public ResponseEntity<ItensEstoqueDto> create(@Valid @RequestBody ItensEstoqueDto itensDto){
		ItensEstoque itens = itensService.create(itensDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(itens.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
}
