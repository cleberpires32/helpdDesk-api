package com.cleber.helpDeskapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cleber.helpDeskapi.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer>{

}
