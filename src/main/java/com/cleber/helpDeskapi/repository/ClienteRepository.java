package com.cleber.helpDeskapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cleber.helpDeskapi.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
