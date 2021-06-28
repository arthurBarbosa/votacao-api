package com.arthurbarbosa.votacao.services;

import com.arthurbarbosa.votacao.dto.ScheduleRequestDTO;
import com.arthurbarbosa.votacao.dto.ScheduleResponseDTO;
import com.arthurbarbosa.votacao.entities.Schedule;
import com.arthurbarbosa.votacao.repositories.ScheduleRepository;
import com.arthurbarbosa.votacao.resources.exceptions.ExceptionEnum;
import com.arthurbarbosa.votacao.services.exception.ObjectNotFoundException;
import com.arthurbarbosa.votacao.services.impl.ServiceScheduleImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class ScheduleServiceTest {

    private ScheduleService scheduleService;

    @MockBean
    private ScheduleRepository scheduleRepository;

    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp(){
        this.scheduleService = new ServiceScheduleImpl(scheduleRepository, modelMapper);
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

    @Test
    public void should_return_exception_when_non_existing_schedule(){
        Long id = 1L;

        Exception exception = org.junit.jupiter.api.Assertions.assertThrows(ObjectNotFoundException.class, () -> scheduleService.findById(id));
        String expectedMessage = ExceptionEnum.RESOURCE_NOT_FOUND.getDescription();
        String currentMessage = exception.getMessage();

        assertThat(expectedMessage).isEqualTo(currentMessage);
    }

    @Test
    public void should_return_list_schedule(){
        var schedule1 = Schedule.builder().id(1L).description("Taxa ICMS").build();
        var schedule2 = Schedule.builder().id(2L).description("Taxa ICS").build();
        List<Schedule> schedules = new ArrayList<>();
        schedules.add(schedule1);
        schedules.add(schedule2);

        Mockito.when(scheduleRepository.findAll()).thenReturn(schedules);

        List<ScheduleResponseDTO> dtos = scheduleService.findAll();
        assertThat(dtos.size()).isEqualTo(2);
    }
}
