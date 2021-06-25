package com.arthurbarbosa.votacao.services;

import com.arthurbarbosa.votacao.dto.ScheduleRequestDTO;
import com.arthurbarbosa.votacao.dto.ScheduleResponseDTO;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDTO save(ScheduleRequestDTO dto);

    ScheduleResponseDTO findById(Long id);

    List<ScheduleResponseDTO> findAll();
}
