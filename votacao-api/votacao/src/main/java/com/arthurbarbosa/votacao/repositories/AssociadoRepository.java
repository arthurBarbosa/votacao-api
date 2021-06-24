package com.arthurbarbosa.votacao.repositories;

import com.arthurbarbosa.votacao.entities.Assembleia;
import com.arthurbarbosa.votacao.entities.Associado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssociadoRepository extends JpaRepository<Associado, Long> {
}
