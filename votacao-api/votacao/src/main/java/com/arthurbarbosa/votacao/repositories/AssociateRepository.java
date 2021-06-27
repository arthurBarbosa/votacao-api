package com.arthurbarbosa.votacao.repositories;

import com.arthurbarbosa.votacao.entities.Associate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssociateRepository extends JpaRepository<Associate, Long> {
    boolean existsByCpf(String cpf);
}
