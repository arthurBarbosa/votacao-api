package com.arthurbarbosa.votacao.repositories;

import com.arthurbarbosa.votacao.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
