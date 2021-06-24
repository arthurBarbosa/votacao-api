package com.arthurbarbosa.votacao.repositories;

import com.arthurbarbosa.votacao.entities.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PautaRepository extends JpaRepository<Pauta, Long> {
}
