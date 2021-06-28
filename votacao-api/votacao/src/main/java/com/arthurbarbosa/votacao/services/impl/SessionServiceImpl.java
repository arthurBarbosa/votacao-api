package com.arthurbarbosa.votacao.services.impl;

import com.arthurbarbosa.votacao.dto.SessionRequestDTO;
import com.arthurbarbosa.votacao.dto.SessionResponseDTO;
import com.arthurbarbosa.votacao.entities.Session;
import com.arthurbarbosa.votacao.repositories.ScheduleRepository;
import com.arthurbarbosa.votacao.repositories.SessionRepository;
import com.arthurbarbosa.votacao.resources.exceptions.ExceptionEnum;
import com.arthurbarbosa.votacao.services.SessionService;
import com.arthurbarbosa.votacao.services.exception.InvalidSessionDurationException;
import com.arthurbarbosa.votacao.services.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessionServiceImpl implements SessionService {

    private final ScheduleRepository scheduleRepository;
    private final SessionRepository sessionRepository;
    private final ModelMapper modelMapper;

    public SessionServiceImpl(ScheduleRepository scheduleRepository, SessionRepository sessionRepository, ModelMapper modelMapper) {
        this.scheduleRepository = scheduleRepository;
        this.sessionRepository = sessionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public SessionResponseDTO save(SessionRequestDTO dto) {
        if (dto.getDuration() < 1)
            throw new InvalidSessionDurationException(ExceptionEnum.INVALID_SESSION_DURATION.getDescription());

        var session = Session.builder().build();
        session.setDuration(dto.getDuration());

        var schedule = scheduleRepository.findById(dto.getScheduleId())
                .orElseThrow(() -> new ObjectNotFoundException(ExceptionEnum.RESOURCE_NOT_FOUND.getDescription()));
        session.setSchedule(schedule);

        sessionRepository.save(session);
        return modelMapper.map(session, SessionResponseDTO.class);
    }

    @Override
    public SessionResponseDTO findById(Long id) {
        var session = sessionRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(ExceptionEnum.RESOURCE_NOT_FOUND.getDescription()));
        return modelMapper.map(session, SessionResponseDTO.class);
    }

    @Override
    public List<SessionResponseDTO> findAll() {
        return sessionRepository.findAll().stream().map(SessionResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public SessionResponseDTO openSession(Long id) {
        var session = sessionRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(ExceptionEnum.RESOURCE_NOT_FOUND.getDescription()));
        session.setOpen(true);
        sessionRepository.save(session);
        return modelMapper.map(session, SessionResponseDTO.class);
    }
}
