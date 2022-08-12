package com.cleber.helpDeskapi.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cleber.helpDeskapi.domain.Chamado;
import com.cleber.helpDeskapi.domain.Cliente;
import com.cleber.helpDeskapi.domain.ItensEstoque;
import com.cleber.helpDeskapi.domain.PedidoEstoque;
import com.cleber.helpDeskapi.domain.Servico;
import com.cleber.helpDeskapi.domain.Tecnico;
import com.cleber.helpDeskapi.domain.enums.Prioridade;
import com.cleber.helpDeskapi.domain.enums.Status;
import com.cleber.helpDeskapi.dtos.ChamadoDto;
import com.cleber.helpDeskapi.dtos.ServicoDto;
import com.cleber.helpDeskapi.repository.ChamadoRepository;
import com.cleber.helpDeskapi.repository.ItensEstoqueRepository;
import com.cleber.helpDeskapi.repository.PedidoEstoqueRepository;
import com.cleber.helpDeskapi.service.exception.DataIntegrityViolationException;
import com.cleber.helpDeskapi.service.exception.ObjectNotFoundException;

@Service
public class ChamadoService {

	@Autowired
	private ClienteService clienteService;
	@Autowired
	private TecnicoService tecnicoService;
	@Autowired
	private PedidoEstoqueService pedidoEstoqueService;

	@Autowired
	private PedidoEstoqueRepository pedidoEstoqueRepository;

	@Autowired
	private ChamadoRepository repository;
	@Autowired
	private ItensEstoqueRepository itensEstoqueRepository;

	public ChamadoDto findById(Integer id) {
		Optional<Chamado> op = repository.findById(id);
		ChamadoDto dto = new ChamadoDto(op.get());
		atualizarQtSolicitada(op);
		op.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado com id: " + id));
		if (op.isPresent()) {
			dto = new ChamadoDto(op.get());
		}
		return dto;
	}

	private void atualizarQtSolicitada(Optional<Chamado> op) {
		if (!op.get().getPedidoEstoque().isEmpty()) {
			op.get().getPedidoEstoque().forEach(p -> {
				p.getItensEstoque().setQuantidadeSolicitada(p.getQuantidadeSolicitada());
			});
		}
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
		Optional<Chamado> ch = dto.getId() != null ? repository.findById(dto.getId())
				: Optional.ofNullable(new Chamado());

		if (dto.getStatus().equals(Status.ENCERRADO.getCodigo())) {
			ch.get().setDataFechamento(LocalDateTime.now());
		}

		ch.get().setStatus(Status.toEnum(dto.getStatus()));
		ch.get().setPrioridade(Prioridade.toEnum(dto.getPrioridade()));
		ch.get().setTitulo(dto.getTitulo());
		ch.get().setModelo(dto.getModelo());
		ch.get().setRecibo(dto.getRecibo());
		ch.get().setObservacoes(dto.getObservacoes());
		ch.get().setCliente(cliente);
		ch.get().setTecnico(tecnico);
		ch.get().setServicos(dto.getServicos().size() == 0 ? ch.get().getServicos()
				: getServicos(ch.get().getServicos(), dto.getServicos()));

		toUpdate(dto, ch.get());

		return ch.get();
	}

	private List<Servico> getServicos(List<Servico> bd, List<Servico> dto) {
		for (Servico servicoDto : dto) {
			if (!bd.contains(servicoDto)) {
				bd.add(servicoDto);
			}
		}
		return bd;
	}

	@Transactional
	private void toUpdate(ChamadoDto dto, Chamado ch) {
		PedidoEstoque pedido = new PedidoEstoque();

		if (dto.getId() != null) {
			ch.setId(dto.getId());
	
			dto.getItensEstoque().stream().forEach(itendto -> {
				pedido.setChamado(ch);
				pedido.setItensEstoque(itendto);
				pedido.setQuantidadeSolicitada(itendto.getQuantidadeSolicitada());
				pedido.getPk().setChamadoId(ch.getId());
				pedido.getPk().setItensEstoqueId(itendto.getId());
				// pedido.setPk(new PedidoEstoquePK());
				pedidoEstoqueService.saveAndFlush(pedido);
			});
			if (dto.getAdicionarIteno()) {
				alteraQuantidadeEstoque(dto);
			}

		}
	}

	private Boolean cadastroEstoqueOuServico(ChamadoDto dto) {
		Optional<Chamado> c = repository.findById(dto.getId());
		Boolean temPedidos = existePedidoCadastrado(c, dto);
		Boolean temServico = existeServicoCadastrado(c, dto);
		return temPedidos || temServico;
	}

	private Boolean existePedidoCadastrado(Optional<Chamado> c, ChamadoDto dto) {
		List<PedidoEstoque> l = pedidoEstoqueRepository.listaPedidoPorChamadoComItens(c.get(), dto.getItensEstoque());
		return l.isEmpty() ? false : true;
	}

	private Boolean existeServicoCadastrado(Optional<Chamado> c, ChamadoDto dto) {
		Boolean temServico = false;
		for (Servico i : dto.getServicos()) {
			temServico = c.get().getServicos().contains(i);
		}
		return temServico;
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
	}

	public ChamadoDto updateServico(Integer id, List<ServicoDto> servicos) {
		Optional<Chamado> ch = repository.findById(id);
		List<Servico> servicosdb = servicos.stream().map(s -> new Servico(s)).collect(Collectors.toList());
		ch.get().getServicos().removeAll(servicosdb);

		repository.save(ch.get());
		return new ChamadoDto(ch.get());
	}

}
