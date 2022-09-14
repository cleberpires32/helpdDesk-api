package com.cleber.helpDeskapi.comm;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cleber.helpDeskapi.domain.PendenciaStatus;
import com.cleber.helpDeskapi.dtos.PendenciaStatusDto;

@Component
public class ModelMapperPendenciaAssenble {
	
	@Autowired
	private ModelMapper modalMapper;
	
	public PendenciaStatusDto toModel(PendenciaStatus entity) {
		return modalMapper.map(entity, PendenciaStatusDto.class);
	}
	
	public PendenciaStatus toEntity(PendenciaStatusDto modelDto) {
		return modalMapper.map(modelDto, PendenciaStatus.class);
	}
	
	public List<PendenciaStatusDto> toCollectionModel(List<PendenciaStatus> listPendencias){
		return listPendencias.stream().map(this::toModel).collect(Collectors.toList());
	}

}
