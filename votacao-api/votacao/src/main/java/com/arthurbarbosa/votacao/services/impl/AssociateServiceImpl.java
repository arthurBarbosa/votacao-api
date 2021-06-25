package com.arthurbarbosa.votacao.services.impl;

import com.arthurbarbosa.votacao.dto.AssociateRequestDTO;
import com.arthurbarbosa.votacao.dto.AssociateResponseDTO;
import com.arthurbarbosa.votacao.entities.Associate;
import com.arthurbarbosa.votacao.repositories.AssociateRepository;
import com.arthurbarbosa.votacao.resources.exceptions.ExceptionEnum;
import com.arthurbarbosa.votacao.services.AssociateService;
import com.arthurbarbosa.votacao.services.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssociateServiceImpl implements AssociateService {

    private final AssociateRepository associateRepository;

    public AssociateServiceImpl(AssociateRepository associateRepository) {
        this.associateRepository = associateRepository;
    }

    @Override
    public AssociateResponseDTO save(AssociateRequestDTO dto) {
        if (associateRepository.existsByCpf(dto.getCpf()))
            throw new RuntimeException("CPF já existe na base de dados");
        var associate = new Associate(null, dto.getCpf(), dto.getName());
        var associateSave = associateRepository.save(associate);
        return new AssociateResponseDTO(associate);
    }

    @Override
    public AssociateResponseDTO findById(Long id) {
        var associate = associateRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(ExceptionEnum.RESOURCE_NOT_FOUND.getDescription()));
        return new AssociateResponseDTO(associate);
    }

    @Override
    public List<AssociateResponseDTO> findAll() {
        List<Associate> associates = associateRepository.findAll();
        return associates.stream().map(AssociateResponseDTO::new).collect(Collectors.toList());
    }
}
