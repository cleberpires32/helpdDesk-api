package com.cleber.helpDeskapi.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@GetMapping
	public ResponseEntity<List<ItensEstoqueDto>> findAll(){
		List<ItensEstoque> listaEstoque  = itensService.findAll();
		List<ItensEstoqueDto> listDto = listaEstoque.stream().map(estoque -> new ItensEstoqueDto(estoque)).collect(Collectors.toList());
	return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ItensEstoqueDto> findById(@PathVariable Integer id){
		ItensEstoque iten= itensService.findById(id);
		ItensEstoqueDto dto = new ItensEstoqueDto(iten);
		return ResponseEntity.ok().body(dto);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping
	public ResponseEntity<ItensEstoqueDto> update(@PathVariable Integer id, @RequestBody ItensEstoqueDto dto){
		ItensEstoque itensAlterada = itensService.update(dto, id);
		ItensEstoqueDto itens = new ItensEstoqueDto(itensAlterada);
		return ResponseEntity.ok().body(itens);
	}
}
