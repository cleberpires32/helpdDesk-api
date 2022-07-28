package com.cleber.helpDeskapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cleber.helpDeskapi.domain.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer>{

}
