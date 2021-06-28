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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessionServiceImpl implements SessionService {

    private final ScheduleRepository scheduleRepository;
    private final SessionRepository sessionRepository;

    public SessionServiceImpl(ScheduleRepository scheduleRepository, SessionRepository sessionRepository) {
        this.scheduleRepository = scheduleRepository;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public SessionResponseDTO save(SessionRequestDTO dto) {
        if (dto.getDuration() < 1)
            throw new InvalidSessionDurationException(ExceptionEnum.INVALID_SESSION_DURATION.getDescription());

        var session = new Session();
        session.setDuration(dto.getDuration());

        var schedule = scheduleRepository.findById(dto.getScheduleId())
                .orElseThrow(() -> new ObjectNotFoundException(ExceptionEnum.RESOURCE_NOT_FOUND.getDescription()));
        session.setSchedule(schedule);

        var sessionSave = sessionRepository.save(session);
        return new SessionResponseDTO(sessionSave);
    }

    @Override
    public SessionResponseDTO findById(Long id) {
        var session = sessionRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(ExceptionEnum.RESOURCE_NOT_FOUND.getDescription()));

        return SessionResponseDTO.builder()
                .id(session.getId())
                .duration(session.getDuration())
                .isOpen(session.isOpen())
                .scheduleId(session.getSchedule().getId()).build();
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
        return SessionResponseDTO.builder()
                .id(session.getId())
                .duration(session.getDuration())
                .isOpen(session.isOpen())
                .scheduleId(session.getSchedule().getId()).build();
    }
}
