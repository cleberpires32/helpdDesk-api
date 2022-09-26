package com.cleber.helpDeskapi.comm;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cleber.helpDeskapi.domain.Servico;
import com.cleber.helpDeskapi.dtos.ServicoDto;

@Component
public class ModelMapperServicoDto {

	@Autowired
	private ModelMapper modelMapper;
	
	public ServicoDto toModel(Servico servico) {
		return modelMapper.map(servico, ServicoDto.class);
	}
	
	public List<ServicoDto> toCollectioModel(List<Servico> servicos) {
		return servicos.stream().map(this::toModel).collect(Collectors.toList());
	}
}
