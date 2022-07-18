package com.cleber.helpDeskapi.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cleber.helpDeskapi.domain.Cliente;
import com.cleber.helpDeskapi.dtos.ClienteDto;
import com.cleber.helpDeskapi.service.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDto> buscarPorId(@PathVariable Integer id) {
		Cliente Cliente = service.findById(id);
		ClienteDto dto = new ClienteDto(Cliente);
		return ResponseEntity.ok().body(dto);
	}

	@GetMapping
	public ResponseEntity<List<ClienteDto>> findAll() {

		List<Cliente> listCliente = service.findAll();
		List<ClienteDto> dto = listCliente.stream().map(tec -> new ClienteDto(tec)).collect(Collectors.toList());

		return ResponseEntity.ok().body(dto);

	}

	@PostMapping
	public ResponseEntity<ClienteDto> create(@RequestBody ClienteDto tecDto) {
		Cliente Cliente = service.create(tecDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(Cliente.getId())
				.toUri();
		return ResponseEntity.created(uri).build();

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ClienteDto> update(@PathVariable Integer id, 
			@Valid @RequestBody ClienteDto objDto){
		Cliente tec = service.update(id, objDto);
		return ResponseEntity.ok().body(new ClienteDto(tec));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
