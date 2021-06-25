package com.arthurbarbosa.votacao.services.impl;

import com.arthurbarbosa.votacao.dto.VoteCountDTO;
import com.arthurbarbosa.votacao.dto.VoteRequestDTO;
import com.arthurbarbosa.votacao.entities.Votation;
import com.arthurbarbosa.votacao.repositories.AssociateRepository;
import com.arthurbarbosa.votacao.repositories.SessionRepository;
import com.arthurbarbosa.votacao.repositories.VotationRepository;
import com.arthurbarbosa.votacao.services.VotationService;
import org.springframework.stereotype.Service;

@Service
public class VotationServiceImpl implements VotationService {

    private final SessionRepository sessionRepository;
    private final VotationRepository votationRepository;
    private final AssociateRepository associateRepository;

    public VotationServiceImpl(SessionRepository sessionRepository, VotationRepository votationRepository, AssociateRepository associateRepository) {
        this.sessionRepository = sessionRepository;
        this.votationRepository = votationRepository;
        this.associateRepository = associateRepository;
    }

    @Override
    public VoteCountDTO countVotes(Long sessionId) {
        var session = sessionRepository.findById(sessionId).orElseThrow(() -> new RuntimeException("Sessão de voto não encontrada"));
        if (session.isOpen())
            throw new RuntimeException("Não é possível ter o resultado da votação durante uma sessão aberta");

        var dto = new VoteCountDTO();
        dto.setVotesYes(votationRepository.countBySessionIdAndVoteTrue(sessionId));
        dto.setVotesNo(votationRepository.countBySessionIdAndVoteFalse(sessionId));
        return dto;
    }

    @Override
    public VoteRequestDTO vote(Long sessionId, boolean vote, Long associateId) {
        var dto = new VoteRequestDTO();
        var session = sessionRepository.findById(sessionId).orElseThrow(() -> new RuntimeException("Nenhuma sessão encontrada."));
        if (!session.isOpen())
            throw new RuntimeException("Nenhuma pode votar.");

        var votation = votationRepository.findBySessionIdAndAssociateId(sessionId, associateId);
        if (votation != null)
            throw new RuntimeException("Voto duplicado");
        else {
            var associate = associateRepository.findById(associateId).orElseThrow(() -> new RuntimeException("Nenhum associado encontrado"));
            votation = new Votation();
            votation.setSession(session);
            votation.setAssociate(associate);
            votation.setVote(vote);
            votationRepository.save(votation);

            if (vote)
                dto.setVote("SIM");
            else
                dto.setVote("NÃO");
            dto.setAssociateId(associate.getId());
            dto.setScheduleId(session.getSchedule().getId());
            dto.setSessionId(session.getId());
        }
        return dto;
    }
}
