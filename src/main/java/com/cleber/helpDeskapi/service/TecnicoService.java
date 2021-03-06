package com.cleber.helpDeskapi.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cleber.helpDeskapi.domain.Pessoa;
import com.cleber.helpDeskapi.domain.Tecnico;
import com.cleber.helpDeskapi.dtos.TecnicoDto;
import com.cleber.helpDeskapi.repository.PessoaRepository;
import com.cleber.helpDeskapi.repository.TecnicoRepository;
import com.cleber.helpDeskapi.service.exception.ConstraintViolationException;
import com.cleber.helpDeskapi.service.exception.DataIntegrityViolationException;
import com.cleber.helpDeskapi.service.exception.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> tecnico = tecnicoRepository.findById(id);

		return tecnico.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado com id: " + id));
	}

	public List<Tecnico> findAll() {
		return tecnicoRepository.findAll();
	}

	public Tecnico create(TecnicoDto tecnicoDto) {
		tecnicoDto.setId(null);
		validaPorCpfeEmail(tecnicoDto);
		tecnicoDto.setSenha(encoder.encode(tecnicoDto.getSenha()));
		Tecnico tecnico = new Tecnico(tecnicoDto);
		tecnico.setDataCriacao(LocalDateTime.now());
		try {
		return	tecnicoRepository.save(tecnico);
		} catch (Exception e) {
			throw new ConstraintViolationException(e.getLocalizedMessage());
		}		
	}

	private void validaPorCpfeEmail(TecnicoDto tecnico) {
		Optional<Pessoa> pessoaComCpf = pessoaRepository.findByCpf(tecnico.getCpf());

		if (pessoaComCpf.isPresent() && pessoaComCpf.get().getId() != tecnico.getId()) {

			throw new DataIntegrityViolationException("CPF já cadastrado no sistema.");
		}

		Optional<Pessoa> pessoaComEmail = pessoaRepository.findByEmail(tecnico.getEmail());

		if (pessoaComEmail.isPresent() && pessoaComEmail.get().getId() != tecnico.getId()) {
			throw new DataIntegrityViolationException("Email já cadastrado no sistema");
		}

	}

	public Tecnico update(Integer id, @Valid TecnicoDto objDto) {
		Tecnico tec = findById(id);
		if(!tec.getSenha().equals(objDto.getSenha())) {
			objDto.setSenha(encoder.encode(objDto.getSenha()));
		}
		validaPorCpfeEmail(objDto);
		objDto.setId(id);
		Tecnico tecAlterado = new Tecnico(objDto);
		return tecnicoRepository.save(tecAlterado);
	}

	public void delete(Integer id) {
		Tecnico tec = findById(id);
		if (tec.getChamados().size() > 0) {
			throw new DataIntegrityViolationException(
					"O técnico não pode ser removido, pois existe(m) chamado(s) associado(s)");
		}
		tecnicoRepository.delete(tec);
	}
}