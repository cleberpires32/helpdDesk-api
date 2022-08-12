package com.cleber.helpDeskapi.service;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cleber.helpDeskapi.domain.Chamado;
import com.cleber.helpDeskapi.domain.Cliente;
import com.cleber.helpDeskapi.domain.ItensEstoque;
import com.cleber.helpDeskapi.domain.Servico;
import com.cleber.helpDeskapi.domain.Tecnico;
import com.cleber.helpDeskapi.domain.enums.Perfil;
import com.cleber.helpDeskapi.domain.enums.Prioridade;
import com.cleber.helpDeskapi.domain.enums.Status;
import com.cleber.helpDeskapi.repository.ChamadoRepository;
import com.cleber.helpDeskapi.repository.ClienteRepository;
import com.cleber.helpDeskapi.repository.ItensEstoqueRepository;
import com.cleber.helpDeskapi.repository.ServicoRepository;
import com.cleber.helpDeskapi.repository.TecnicoRepository;

@Service
public class DbService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;
	@Autowired
	private ItensEstoqueRepository itensEstoqueRepository;
	@Autowired
	private ServicoRepository servicoRepository;
	
	@Autowired
	private BCryptPasswordEncoder encode;
	
	public void instaciaDbService() {
		Tecnico tc0 = new Tecnico(null, "talis", "talis@gmail.com", "78882720020", "61991587610" ,encode.encode("123"));
		tc0.addPerfil(Perfil.ADMIN);
		
		Tecnico tc1 = new Tecnico(null, "cleber", "cleber@gmail.com", "11683800060", "61991587610" ,encode.encode("#Rebelc32"));
		tc1.addPerfil(Perfil.ADMIN);
		
		Tecnico tc2 = new Tecnico(null, "Joao Alves", "joao@gmail.com", "79471566460", "61991587610", encode.encode("12345"));
		Tecnico tc3 = new Tecnico(null, "Felipe", "felipe@gmail.com", "91405277602", "61991587610", encode.encode("12345"));
		Tecnico tc4 = new Tecnico(null, "Antonio", "antonio@gmail.com", "41354781589","61991587610",encode.encode("12345"));
		
		Cliente cl1 = new Cliente(null, "Sidney Alves", "sidney@gmail.com", "54811402090", "61991587610", encode.encode("12345"));
		Cliente cl2 = new Cliente(null, "Alberto Duran", "alberto@gmail.com", "64546306172", "61991587610", encode.encode("12345"));
		
		Chamado ch1 = new Chamado(null,  "Roçadeira Still","vulcan c55","586","Primeira carga", Prioridade.MEDIA, Status.ANDAMENTO, tc1, cl1);
		Chamado ch2 = new Chamado(null,  "Cortador de grama 2","yanamn rd" ,"587","Segunda carga", Prioridade.ALTA, Status.ABERTO, tc3, cl1);
		Chamado ch3 = new Chamado(null,  "Motor serra","ct 250","588", "Terceira carga", Prioridade.BAIXA, Status.CANCELADO, tc2, cl2);
		Chamado ch4 = new Chamado(null,  "Soprador de folha","Trapp 27","588", "Terceira carga", Prioridade.BAIXA, Status.ENCERRADO, tc2, cl2);
	
		ItensEstoque est = new ItensEstoque(null, "Carburador Still 55c", "234D3", BigDecimal.TEN,2);
		ItensEstoque est2 = new ItensEstoque(null, "Corrente de motoserra Still 55c", "234D3", BigDecimal.ONE,2);
		
		itensEstoqueRepository.saveAll(Arrays.asList(est,est2));
		tecnicoRepository.saveAll(Arrays.asList(tc0,tc1,tc2,tc3,tc4));
		
		tecnicoRepository.saveAll(Arrays.asList(tc0));
		
		clienteRepository.saveAll(Arrays.asList(cl1,cl2));
		chamadoRepository.saveAll(Arrays.asList(ch1,ch2,ch3,ch4));
		
		Servico s1 = new Servico(null,"Limpleza e regulagem do carburador", BigDecimal.TEN);
		Servico s2 = new Servico(null,"Mão de Obra Geral", BigDecimal.TEN);
		servicoRepository.saveAll(Arrays.asList(s1,s2));
		
		
	}
	
}
