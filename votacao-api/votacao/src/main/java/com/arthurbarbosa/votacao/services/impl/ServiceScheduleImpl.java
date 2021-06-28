package com.arthurbarbosa.votacao.services.impl;

import com.arthurbarbosa.votacao.dto.ScheduleRequestDTO;
import com.arthurbarbosa.votacao.dto.ScheduleResponseDTO;
import com.arthurbarbosa.votacao.entities.Schedule;
import com.arthurbarbosa.votacao.repositories.ScheduleRepository;
import com.arthurbarbosa.votacao.resources.exceptions.ExceptionEnum;
import com.arthurbarbosa.votacao.services.ScheduleService;
import com.arthurbarbosa.votacao.services.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceScheduleImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ModelMapper modelMapper;

    public ServiceScheduleImpl(ScheduleRepository scheduleRepository, ModelMapper modelMapper) {
        this.scheduleRepository = scheduleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ScheduleResponseDTO save(ScheduleRequestDTO dto) {
        var schedule = Schedule.builder().description(dto.getDescription()).build();
        scheduleRepository.save(schedule);
        return modelMapper.map(schedule, ScheduleResponseDTO.class);
    }

    @Override
    public ScheduleResponseDTO findById(Long id) {
        var schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(ExceptionEnum.RESOURCE_NOT_FOUND.getDescription()));
        return new ScheduleResponseDTO(schedule);
    }

    @Override
    public List<ScheduleResponseDTO> findAll() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return schedules.stream().map(ScheduleResponseDTO::new).collect(Collectors.toList());
    }
}
