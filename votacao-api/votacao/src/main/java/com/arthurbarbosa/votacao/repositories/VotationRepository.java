package com.arthurbarbosa.votacao.repositories;

import com.arthurbarbosa.votacao.entities.Votation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotationRepository extends JpaRepository<Votation, Long> {
    Long countBySessionIdAndVoteTrue(Long sessionId);

    Long countBySessionIdAndVoteFalse(Long sessionId);
}
