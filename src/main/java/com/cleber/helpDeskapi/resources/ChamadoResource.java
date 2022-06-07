package com.cleber.helpDeskapi.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cleber.helpDeskapi.dtos.ChamadoDto;
import com.cleber.helpDeskapi.service.ChamadoService;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoResource {

	@Autowired
	private ChamadoService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ChamadoDto> findById(@PathVariable Integer id){
		ChamadoDto dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@GetMapping
	public ResponseEntity<List<ChamadoDto>> findByAll(){
		List<ChamadoDto> list = service.findByAll();
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<ChamadoDto> create(@Valid @RequestBody ChamadoDto dto){
		ChamadoDto chamadoDto = service.create(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(chamadoDto.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
}
