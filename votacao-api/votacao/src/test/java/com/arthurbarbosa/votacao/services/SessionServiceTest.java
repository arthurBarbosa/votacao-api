package com.arthurbarbosa.votacao.services;

import com.arthurbarbosa.votacao.dto.SessionRequestDTO;
import com.arthurbarbosa.votacao.entities.Schedule;
import com.arthurbarbosa.votacao.entities.Session;
import com.arthurbarbosa.votacao.repositories.ScheduleRepository;
import com.arthurbarbosa.votacao.repositories.SessionRepository;
import com.arthurbarbosa.votacao.services.impl.SessionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class SessionServiceTest {

    private SessionService sessionService;

    @MockBean
    private SessionRepository sessionRepository;

    @MockBean
    private ScheduleRepository scheduleRepository;

    private ModelMapper modelMapper;

    private VotationService votationService;

    @BeforeEach
    public void setUp(){
        this.sessionService = new SessionServiceImpl(scheduleRepository, sessionRepository, modelMapper, votationService);
    }

    @Test
    public void should_save_session(){
        Long id = 1L;
        var schedule = Schedule.builder().description("Taxa de juros").build();
        var savedSchedule = Schedule.builder().id(1L).description("Taxa de juros").build();

        Mockito.when(scheduleRepository.save(schedule)).thenReturn(savedSchedule);
        Mockito.when(scheduleRepository.findById(1L)).thenReturn(Optional.of(schedule));

        var session = Session.builder().isOpen(false).duration(1L).schedule(schedule).build();
        var dto = SessionRequestDTO.builder().duration(1L).scheduleId(1L).build();
        var savedSession = Session.builder().id(1L).isOpen(false).duration(1L).schedule(savedSchedule).build();

        Mockito.when(sessionRepository.save(session))
                .thenReturn(savedSession);

        sessionService.save(dto);

        assertThat(savedSession.getId()).isNotNull();
        assertThat(savedSession.getSchedule()).isEqualTo(savedSchedule);
        assertThat(savedSession.getDuration()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void should_find_session_by_id(){
        Long id = 1L;
        var schedule = Schedule.builder().description("Taxa de juros").build();
        var savedSchedule = Schedule.builder().id(1L).description("Taxa de juros").build();

        Mockito.when(scheduleRepository.save(schedule)).thenReturn(savedSchedule);
        Mockito.when(scheduleRepository.findById(1L)).thenReturn(Optional.of(savedSchedule));

        var session = Session.builder().isOpen(false).duration(1L).schedule(savedSchedule).build();

        Mockito.when(sessionRepository.findById(id)).thenReturn(Optional.of(session));

        var dto = sessionService.findById(id);

        assertThat(dto.getDuration()).isEqualTo(session.getDuration());
        assertThat(dto.getScheduleId()).isEqualTo(session.getSchedule().getId());
    }
}
