package com.cleber.helpDeskapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cleber.helpDeskapi.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{

	Optional<Pessoa> findByCpf(String cpf);
	Optional<Pessoa> findByEmail(String email);
}
