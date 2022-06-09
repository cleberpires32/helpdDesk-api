package com.cleber.helpDeskapi.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cleber.helpDeskapi.domain.Chamado;
import com.cleber.helpDeskapi.domain.Cliente;
import com.cleber.helpDeskapi.domain.Tecnico;
import com.cleber.helpDeskapi.domain.enums.Perfil;
import com.cleber.helpDeskapi.domain.enums.Prioridade;
import com.cleber.helpDeskapi.domain.enums.Status;
import com.cleber.helpDeskapi.repository.ChamadoRepository;
import com.cleber.helpDeskapi.repository.ClienteRepository;
import com.cleber.helpDeskapi.repository.TecnicoRepository;

@Service
public class DbService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	public void instaciaDbService() {
		Tecnico tc1 = new Tecnico(null, "Cleber Pires", "cleber@gmail.com", "11683800060", "123585adm");
		tc1.addPerfil(Perfil.ADMIN);
		Tecnico tc2 = new Tecnico(null, "Joao Alves", "joao@gmail.com", "79471566460", "123585adm");
		Tecnico tc3 = new Tecnico(null, "Felipe", "felipe@gmail.com", "91405277602", "123585adm");
		Tecnico tc4 = new Tecnico(null, "Antonio", "antonio@gmail.com", "41354781589", "123585adm");
		
		Cliente cl1 = new Cliente(null, "Sidney Alves", "sidney@gmail.com", "54811402090", "5644563cli");
		
		Chamado ch1 = new Chamado(null, "Primeira carga", "Chamado 1", Prioridade.MEDIA, Status.ANDAMENTO, tc1, cl1);
	
		tecnicoRepository.saveAll(Arrays.asList(tc1,tc2,tc3,tc4));
		clienteRepository.saveAll(Arrays.asList(cl1));
		chamadoRepository.saveAll(Arrays.asList(ch1));
		
	}
	
}
