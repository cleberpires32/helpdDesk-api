package com.cleber.helpDeskapi.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cleber.helpDeskapi.domain.Servico;
import com.cleber.helpDeskapi.dtos.ServicoDto;
import com.cleber.helpDeskapi.repository.ServicoRepository;
import com.cleber.helpDeskapi.service.ServicoService;

@RestController
@RequestMapping(value = "/servicos")
public class ServicoResource {
	
	@Autowired
	private ServicoService servicoService;
	@Autowired
	private ServicoRepository servicoRepository;

	@PostMapping
	public ResponseEntity<ServicoDto> create(@Valid @RequestBody ServicoDto servicoDto){
		Servico servico  = servicoService.savar(servicoDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(servico.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	@GetMapping
	public ResponseEntity<List<ServicoDto>> listServicos(){
		List<Servico> listaServicos = servicoRepository.findAll();
		List<ServicoDto> listaServicosDto = listaServicos.stream().map(servico -> new ServicoDto(servico)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaServicosDto);
	}
}
