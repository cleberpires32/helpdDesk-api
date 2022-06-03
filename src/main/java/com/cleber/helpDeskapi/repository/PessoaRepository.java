package com.cleber.helpDeskapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cleber.helpDeskapi.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{

}
