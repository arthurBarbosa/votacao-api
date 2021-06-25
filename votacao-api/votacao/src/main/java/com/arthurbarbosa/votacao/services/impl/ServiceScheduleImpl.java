package com.arthurbarbosa.votacao.services.impl;

import com.arthurbarbosa.votacao.dto.ScheduleRequestDTO;
import com.arthurbarbosa.votacao.dto.ScheduleResponseDTO;
import com.arthurbarbosa.votacao.entities.Schedule;
import com.arthurbarbosa.votacao.repositories.ScheduleRepository;
import com.arthurbarbosa.votacao.services.ScheduleService;
import org.springframework.stereotype.Service;

@Service
public class ServiceScheduleImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ServiceScheduleImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleResponseDTO save(ScheduleRequestDTO dto) {
        var schedule = new Schedule(null, dto.getDescription());
        scheduleRepository.save(schedule);
        return new ScheduleResponseDTO(schedule);
    }
}
