package com.cleber.helpDeskapi.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cleber.helpDeskapi.domain.Tecnico;
import com.cleber.helpDeskapi.dtos.TecnicoDto;
import com.cleber.helpDeskapi.service.TecnicoService;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

	@Autowired
	private TecnicoService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDto> buscarPorId(@PathVariable Integer id) {
		Tecnico tecnico = service.findById(id);
		TecnicoDto dto = new TecnicoDto(tecnico);
		return ResponseEntity.ok().body(dto);
	}

	@GetMapping
	public ResponseEntity<List<TecnicoDto>> findAll() {

		List<Tecnico> listTecnico = service.findAll();
		List<TecnicoDto> dto = listTecnico.stream().map(tec -> new TecnicoDto(tec)).collect(Collectors.toList());

		return ResponseEntity.ok().body(dto);

	}

	@PostMapping
	public ResponseEntity<TecnicoDto> create(@Valid @RequestBody TecnicoDto tecDto) {
		Tecnico tecnico = service.create(tecDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tecnico.getId())
				.toUri();
		return ResponseEntity.created(uri).build();

	}
}
