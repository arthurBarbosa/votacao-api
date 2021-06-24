package com.arthurbarbosa.votacao.repositories;

import com.arthurbarbosa.votacao.entities.Assembleia;
import com.arthurbarbosa.votacao.entities.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssembleiaRepository extends JpaRepository<Assembleia, Long> {
}
