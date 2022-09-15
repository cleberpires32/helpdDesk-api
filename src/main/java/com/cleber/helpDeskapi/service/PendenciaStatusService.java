package com.cleber.helpDeskapi.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cleber.helpDeskapi.domain.Chamado;
import com.cleber.helpDeskapi.domain.Cliente;
import com.cleber.helpDeskapi.domain.PendenciaStatus;
import com.cleber.helpDeskapi.domain.enums.Status;
import com.cleber.helpDeskapi.dtos.EncerraChamadoDto;
import com.cleber.helpDeskapi.dtos.PendenciaStatusDto;
import com.cleber.helpDeskapi.repository.ChamadoRepository;
import com.cleber.helpDeskapi.repository.PendenciaStatusRepository;
import com.cleber.helpDeskapi.service.exception.DataIntegrityViolationException;
import com.cleber.helpDeskapi.service.exception.ObjectNotFoundException;

@Service
public class PendenciaStatusService {

	@Autowired
	private PendenciaStatusRepository repository;
	@Autowired
	private ChamadoRepository chamadoRepository;

	@Transactional
	public PendenciaStatus adicionar(PendenciaStatusDto dto) {
		Chamado chamado = chamadoRepository.findById(dto.getChamadoId().getId()).get();
		PendenciaStatus entity = new PendenciaStatus(dto);
		entity.setChamado(chamado);
		entity.setStatus(chamado.getStatus());
		return repository.save(entity);
	}

	public List<PendenciaStatus> findByIdChamado(Integer idChamado) {
		List<PendenciaStatus> pendencias = new ArrayList<>();
		pendencias = repository.findAllByChamado(idChamado);
		return pendencias;

	}

	public void remmove(Long idPendencia) {

		Optional<PendenciaStatus> pen = repository.findById(idPendencia);
		if (!pen.isPresent()) {
			throw new DataIntegrityViolationException(
					"A Pendência não pode ser removido, pois não existe na base de dados.");
		}
		repository.delete(pen.get());
	}

	public void finalizaChamado(EncerraChamadoDto dto) {
		LocalDateTime encerramento = dto.getDataEncerramento().atStartOfDay();
		LocalDateTime entrega = dto.getDataEntrega() ==null ?null : dto.getDataEntrega().atStartOfDay();
		Optional<Chamado> chamado = chamadoRepository.findById(dto.getIdChamado());
		chamado.orElseThrow(
				() -> new ObjectNotFoundException("Objecto (Chamado): " + dto.getIdChamado() + " não encontrado."));
		if (chamado.isPresent()) {
			chamado.get().setDataFechamento(encerramento);
			chamado.get().setStatus(Status.ENCERRADO);
			chamado.get().setDataEntrega(entrega);
		}
		chamadoRepository.save(chamado.get());
	}

}
