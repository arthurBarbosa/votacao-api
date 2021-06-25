package com.arthurbarbosa.votacao.services.impl;

import com.arthurbarbosa.votacao.dto.SessionRequestDTO;
import com.arthurbarbosa.votacao.dto.SessionResponseDTO;
import com.arthurbarbosa.votacao.entities.Session;
import com.arthurbarbosa.votacao.repositories.ScheduleRepository;
import com.arthurbarbosa.votacao.repositories.SessionRepository;
import com.arthurbarbosa.votacao.services.SessionService;
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
            throw new RuntimeException("A duração da sessão não pode ser menor que 1 minuto");

        var session = new Session();
        session.setDuration(dto.getDuration());

        var schedule = scheduleRepository.findById(dto.getScheduleId())
                .orElseThrow(() -> new RuntimeException("Nenhuma pauta encontrada com id: " + dto.getScheduleId()));
        session.setSchedule(schedule);

        var sessionSave = sessionRepository.save(session);
        return new SessionResponseDTO(sessionSave);
    }

    @Override
    public SessionResponseDTO findById(Long id) {
        var dto = new SessionResponseDTO();
        var session = sessionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhuma sessão encontrado com id: " + id));

        return new SessionResponseDTO(session);
    }

    @Override
    public List<SessionResponseDTO> findAll() {
        List<Session> sessions = sessionRepository.findAll();
        return sessions.stream().map(SessionResponseDTO::new).collect(Collectors.toList());
    }
}
