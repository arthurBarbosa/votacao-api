package com.arthurbarbosa.votacao.repositories;

import com.arthurbarbosa.votacao.entities.Assembleia;
import com.arthurbarbosa.votacao.entities.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotoRepository extends JpaRepository<Voto, Long> {
}
