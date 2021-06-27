package com.arthurbarbosa.votacao.services;

import com.arthurbarbosa.votacao.dto.ScheduleRequestDTO;
import com.arthurbarbosa.votacao.entities.Schedule;
import com.arthurbarbosa.votacao.repositories.ScheduleRepository;
import com.arthurbarbosa.votacao.services.impl.ServiceScheduleImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class ScheduleServiceTest {

    private ScheduleService scheduleService;

    @MockBean
    private ScheduleRepository scheduleRepository;

    @BeforeEach
    public void setUp(){
        this.scheduleService = new ServiceScheduleImpl(scheduleRepository);
    }

    @Test
    public void should_save_schedule(){
        var schedule = Schedule.builder().description("Aumento da taxa").build();
        var dto = ScheduleRequestDTO.builder().description("Aumento da taxa").build();
        var savedSchedule = Schedule.builder().id(1L).description("Aumento da taxa").build();

        Mockito.when(scheduleRepository.save(schedule))
                .thenReturn(savedSchedule);

        scheduleService.save(dto);

        assertThat(savedSchedule.getId()).isNotNull();
        assertThat(savedSchedule.getDescription()).isEqualTo("Aumento da taxa");
    }

    @Test
    public void should_find_schedule_by_id(){
        Long id = 1L;
        var schedule = Schedule.builder().id(id).description("Aumento da taxa").build();

        Mockito.when(scheduleRepository.findById(id)).thenReturn(Optional.of(schedule));

        var dto = scheduleService.findById(id);

        assertThat(dto.getDescription()).isEqualTo(schedule.getDescription());
    }
}
