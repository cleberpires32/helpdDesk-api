package com.cleber.helpDeskapi.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cleber.helpDeskapi.domain.Chamado;
import com.cleber.helpDeskapi.domain.Cliente;
import com.cleber.helpDeskapi.domain.ItensEstoque;
import com.cleber.helpDeskapi.domain.PedidoEstoque;
import com.cleber.helpDeskapi.domain.Tecnico;
import com.cleber.helpDeskapi.domain.enums.Prioridade;
import com.cleber.helpDeskapi.domain.enums.Status;
import com.cleber.helpDeskapi.dtos.ChamadoDto;
import com.cleber.helpDeskapi.repository.ChamadoRepository;
import com.cleber.helpDeskapi.repository.ItensEstoqueRepository;
import com.cleber.helpDeskapi.service.exception.ObjectNotFoundException;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepository repository;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private TecnicoService tecnicoService;
	@Autowired
	private PedidoEstoqueService pedidoEstoqueService;
	@Autowired
	private ItensEstoqueService itensEstoqueService;
	@Autowired
	private ItensEstoqueRepository itensEstoqueRepository;

	public ChamadoDto findById(Integer id) {
		Optional<Chamado> op = repository.findById(id);
		op.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado com id: " + id));
		return new ChamadoDto(op.get());
	}

	public List<ChamadoDto> findByAll() {
		List<Chamado> listCh = repository.findAll();
		List<ChamadoDto> lDto = listCh.stream().map(ch -> new ChamadoDto(ch)).collect(Collectors.toList());
		return lDto;

	}

	public ChamadoDto update(Integer id, @Valid ChamadoDto dto) {
		Chamado c = new Chamado();
		dto.setId(id);
		findById(dto.getId());
		Chamado chamado = newChamado(dto);
		c = repository.save(chamado);
		return new ChamadoDto(c);
	}

	public ChamadoDto create(@Valid ChamadoDto dto) {
		Chamado chamado = repository.save(newChamado(dto));
		return new ChamadoDto(chamado);
	}

	private Chamado newChamado(ChamadoDto dto) {
		Cliente cliente = clienteService.findById(dto.getCliente());
		Tecnico tecnico = tecnicoService.findById(dto.getTecnico());

		Chamado ch = new Chamado();

		if (dto.getStatus().equals(Status.ENCERRADO.getCodigo())) {
			ch.setDataFechamento(LocalDateTime.now());
		}

		ch.setStatus(Status.toEnum(dto.getStatus()));
		ch.setPrioridade(Prioridade.toEnum(dto.getPrioridade()));
		ch.setTitulo(dto.getTitulo());
		ch.setObservacoes(dto.getObservacoes());
		ch.setCliente(cliente);
		ch.setTecnico(tecnico);

		toUpdate(dto, ch);

		return ch;
	}

	private void toUpdate(ChamadoDto dto, Chamado ch) {
		PedidoEstoque pedido = new PedidoEstoque();
		if (dto.getId() != null) {
			ch.setId(dto.getId());
			pedido.setChamado(ch);
			dto.getItensEstoque().stream().forEach(itendto -> {
				pedido.setItensEstoque(itendto);
				pedido.setQuantidadeSolicitada(itendto.getQuantidadeSolicitada());
			});
			
			pedidoEstoqueService.create(pedido);			
			alteraQuantidadeEstoque(dto);
		}
	}
	
	private void alteraQuantidadeEstoque(ChamadoDto chamadoDto) {
		List<ItensEstoque> listaQuantidadeAlterada = new ArrayList<>();
		chamadoDto.getItensEstoque().stream().forEach(iDto -> {
			Optional<ItensEstoque> itensBd = itensEstoqueRepository.findById(iDto.getId());
			if (itensBd.isPresent()) {
				itensBd.get().setQuantidade(itensBd.get().getQuantidade() - iDto.getQuantidadeSolicitada());
				listaQuantidadeAlterada.add(itensBd.get());
			}
		});

		itensEstoqueRepository.saveAll(listaQuantidadeAlterada);
	}

}
