package com.arthurbarbosa.votacao.services.impl;

import com.arthurbarbosa.votacao.dto.AssociateRequestDTO;
import com.arthurbarbosa.votacao.dto.AssociateResponseDTO;
import com.arthurbarbosa.votacao.entities.Associate;
import com.arthurbarbosa.votacao.repositories.AssociateRepository;
import com.arthurbarbosa.votacao.resources.exceptions.ExceptionEnum;
import com.arthurbarbosa.votacao.services.AssociateService;
import com.arthurbarbosa.votacao.services.exception.DuplicateCPFException;
import com.arthurbarbosa.votacao.services.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssociateServiceImpl implements AssociateService {

    private final AssociateRepository associateRepository;
    private final ModelMapper modelMapper;

    public AssociateServiceImpl(AssociateRepository associateRepository, ModelMapper modelMapper) {
        this.associateRepository = associateRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AssociateResponseDTO save(AssociateRequestDTO dto) {
        if (associateRepository.existsByCpf(dto.getCpf()))
            throw new DuplicateCPFException("CPF já existe na base de dados");
        var associate = Associate.builder().cpf(dto.getCpf()).name(dto.getName()).build();
        associateRepository.save(associate);
        return modelMapper.map(associate, AssociateResponseDTO.class);
    }

    @Override
    public AssociateResponseDTO findById(Long id) {
        var associate = associateRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(ExceptionEnum.RESOURCE_NOT_FOUND.getDescription()));
        return modelMapper.map(associate, AssociateResponseDTO.class);
    }

    @Override
    public List<AssociateResponseDTO> findAll() {
        return associateRepository.findAll().stream().map(AssociateResponseDTO::new).collect(Collectors.toList());
    }
}
