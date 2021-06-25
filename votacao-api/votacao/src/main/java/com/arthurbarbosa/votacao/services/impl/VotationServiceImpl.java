package com.arthurbarbosa.votacao.services.impl;

import com.arthurbarbosa.votacao.dto.VoteCountDTO;
import com.arthurbarbosa.votacao.repositories.SessionRepository;
import com.arthurbarbosa.votacao.repositories.VotationRepository;
import com.arthurbarbosa.votacao.services.VotationService;
import org.springframework.stereotype.Service;

@Service
public class VotationServiceImpl implements VotationService {

    private final SessionRepository sessionRepository;
    private final VotationRepository votationRepository;

    public VotationServiceImpl(SessionRepository sessionRepository, VotationRepository votationRepository) {
        this.sessionRepository = sessionRepository;
        this.votationRepository = votationRepository;
    }

    @Override
    public VoteCountDTO countVotes(Long sessionId) {
        var session = sessionRepository.findById(sessionId).orElseThrow(()-> new RuntimeException("Sessão de voto não encontrada"));
        if(session.isOpen())
            throw new RuntimeException("Não é possível ter o resultado da votação durante uma sessão aberta");

        var dto = new VoteCountDTO();
        dto.setVotesYes(votationRepository.countBySessionIdAndVoteTrue(sessionId));
        dto.setVotesNo(votationRepository.countBySessionIdAndVoteFalse(sessionId));
        return dto;
    }
}
