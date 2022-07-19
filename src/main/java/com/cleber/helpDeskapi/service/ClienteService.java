package com.cleber.helpDeskapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cleber.helpDeskapi.domain.Cliente;
import com.cleber.helpDeskapi.domain.Pessoa;
import com.cleber.helpDeskapi.dtos.ClienteDto;
import com.cleber.helpDeskapi.repository.ClienteRepository;
import com.cleber.helpDeskapi.repository.PessoaRepository;
import com.cleber.helpDeskapi.service.exception.DataIntegrityViolationException;
import com.cleber.helpDeskapi.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository ClienteRepository;
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	public Cliente findById(Integer id) {
		Optional<Cliente> cliente = ClienteRepository.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado com id: " + id));
	}

	public List<Cliente> findAll() {
		return ClienteRepository.findAll();
	}

	public Cliente create(ClienteDto clienteDto) {
		clienteDto.setId(null);
		validaPorCpfeEmail(clienteDto);
		clienteDto.setSenha(encoder.encode(clienteDto.getSenha()));
		Cliente cliente = new Cliente(clienteDto);
		return ClienteRepository.save(cliente);
	}

	private void validaPorCpfeEmail(ClienteDto clienteDto) {
		Optional<Pessoa> pessoaComCpf = pessoaRepository.findByCpf(clienteDto.getCpf());

		if (pessoaComCpf.isPresent() && pessoaComCpf.get().getId() != clienteDto.getId()) {

			throw new DataIntegrityViolationException("CPF já cadastrado no sistema.");
		}

		Optional<Pessoa> pessoaComEmail = pessoaRepository.findByEmail(clienteDto.getEmail());

		if (pessoaComEmail.isPresent() && pessoaComEmail.get().getId() != clienteDto.getId()) {
			throw new DataIntegrityViolationException("Email já cadastrado no sistema");
		}

	}

	public Cliente update(Integer id, ClienteDto objDto) {
		Cliente cliente = findById(id);
		if(!cliente.getSenha().equals(objDto.getSenha())) {
			objDto.setSenha(encoder.encode(objDto.getSenha()));
		}
		validaPorCpfeEmail(objDto);
		objDto.setId(id);
		Cliente cli = new Cliente(objDto);
		return ClienteRepository.save(cli);
	}

	public void delete(Integer id) {
		Cliente cli = findById(id);
		if (cli.getChamados().size() > 0) {
			throw new DataIntegrityViolationException(
					"O técnico não pode ser removido, pois existe(m) chamado(s) associado(s)");
		}
		ClienteRepository.delete(cli);
	}
}