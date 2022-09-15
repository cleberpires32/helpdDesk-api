package com.cleber.helpDeskapi.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cleber.helpDeskapi.domain.PendenciaStatus;
import com.cleber.helpDeskapi.dtos.EncerraChamadoDto;
import com.cleber.helpDeskapi.dtos.PendenciaStatusDto;
import com.cleber.helpDeskapi.service.PendenciaStatusService;

@RestController
@RequestMapping(value = "/pendencia")
public class PendenciaStatusResource {

	@Autowired
	private PendenciaStatusService service;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<PendenciaStatusDto> adicionar(@Valid @RequestBody PendenciaStatusDto dto) {

		PendenciaStatus pendencia = new PendenciaStatus();
		pendencia = service.adicionar(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pendencia.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping(value = "/chamado/{idChamado}")
	public ResponseEntity<List<PendenciaStatusDto>> findAllByIdChamado(@PathVariable Integer idChamado) {

		List<PendenciaStatus> lista = service.findByIdChamado(idChamado);
		List<PendenciaStatusDto> listaDto = lista.stream().map(entity -> new PendenciaStatusDto(entity))
				.collect(Collectors.toList());

		return ResponseEntity.ok().body(listaDto);

	}
	
	@DeleteMapping(value = "/{idPendencia}")
	public ResponseEntity<Void> delete(@PathVariable Long idPendencia){
		service.remmove(idPendencia);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/encerraChamado")
	public ResponseEntity<EncerraChamadoDto> finalizachamado(@RequestBody EncerraChamadoDto dto){
		service.finalizaChamado(dto);
		return null;
	}
}
