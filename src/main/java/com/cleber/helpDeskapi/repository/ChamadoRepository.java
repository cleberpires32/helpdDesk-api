package com.cleber.helpDeskapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cleber.helpDeskapi.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer>{

}
