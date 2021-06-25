package com.arthurbarbosa.votacao.services;

import com.arthurbarbosa.votacao.dto.SessionRequestDTO;
import com.arthurbarbosa.votacao.dto.SessionResponseDTO;

public interface SessionService {
    SessionResponseDTO save(SessionRequestDTO dto);

    SessionResponseDTO findById(Long id);
}
