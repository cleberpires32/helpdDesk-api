package com.cleber.helpDeskapi.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cleber.helpDeskapi.domain.Tecnico;
import com.cleber.helpDeskapi.dtos.TecnicoDto;
import com.cleber.helpDeskapi.service.TecnicoService;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

	@Autowired
	private TecnicoService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDto> buscarPorId(@PathVariable Integer id){
		Tecnico tecnico = service.findById(id);
		TecnicoDto dto = new TecnicoDto(tecnico);
		return ResponseEntity.ok().body(dto);
	}
}
