package com.cleber.helpDeskapi.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
