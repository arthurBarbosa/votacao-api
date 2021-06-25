package com.arthurbarbosa.votacao.services.impl;

import com.arthurbarbosa.votacao.dto.ScheduleRequestDTO;
import com.arthurbarbosa.votacao.dto.ScheduleResponseDTO;
import com.arthurbarbosa.votacao.entities.Schedule;
import com.arthurbarbosa.votacao.repositories.ScheduleRepository;
import com.arthurbarbosa.votacao.services.ScheduleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public ScheduleResponseDTO findById(Long id) {
        var schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhum pauta encontrada com id " + id));
        return new ScheduleResponseDTO(schedule);
    }

    @Override
    public List<ScheduleResponseDTO> findAll() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return schedules.stream().map(ScheduleResponseDTO::new).collect(Collectors.toList());
    }
}
