package com.arthurbarbosa.votacao.services;

import com.arthurbarbosa.votacao.dto.AssociateRequestDTO;
import com.arthurbarbosa.votacao.dto.AssociateResponseDTO;

import java.util.List;

public interface AssociateService {
    AssociateResponseDTO save(AssociateRequestDTO dto);

    AssociateResponseDTO findById(Long id);

    List<AssociateResponseDTO> findAll();
}
