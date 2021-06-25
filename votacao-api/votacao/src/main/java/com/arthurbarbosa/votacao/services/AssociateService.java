package com.arthurbarbosa.votacao.services;

import com.arthurbarbosa.votacao.dto.AssociateRequestDTO;
import com.arthurbarbosa.votacao.dto.AssociateResponseDTO;

public interface AssociateService {
    AssociateResponseDTO save(AssociateRequestDTO dto);
}
