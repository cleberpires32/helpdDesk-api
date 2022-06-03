package com.cleber.helpDeskapi.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cleber.helpDeskapi.domain.Tecnico;
import com.cleber.helpDeskapi.service.TecnicoService;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

	@Autowired
	private TecnicoService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Tecnico> buscarPorId(@PathVariable Integer id){
		Tecnico tecnico = service.findById(id);
		return ResponseEntity.ok().body(tecnico);
	}
}
