package com.cleber.helpDeskapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cleber.helpDeskapi.domain.PendenciaStatus;

public interface PendenciaStatusRepository extends JpaRepository<PendenciaStatus, Long>{

	@Modifying
	@Query(value = "SELECT p FROM PendenciaStatus p where p.chamado.id = ?1")
	public List<PendenciaStatus> findAllByChamado(Integer idChamado);

}
