package com.arthurbarbosa.votacao.services.impl;

import com.arthurbarbosa.votacao.dto.VoteCountDTO;
import com.arthurbarbosa.votacao.dto.VoteRequestDTO;
import com.arthurbarbosa.votacao.entities.Votation;
import com.arthurbarbosa.votacao.repositories.AssociateRepository;
import com.arthurbarbosa.votacao.repositories.SessionRepository;
import com.arthurbarbosa.votacao.repositories.VotationRepository;
import com.arthurbarbosa.votacao.resources.exceptions.ExceptionEnum;
import com.arthurbarbosa.votacao.services.ValidateCPFService;
import com.arthurbarbosa.votacao.services.VotationService;
import com.arthurbarbosa.votacao.services.exception.CountVoteSessionOpenException;
import com.arthurbarbosa.votacao.services.exception.DuplicateVoteException;
import com.arthurbarbosa.votacao.services.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class VotationServiceImpl implements VotationService {

    private final SessionRepository sessionRepository;
    private final VotationRepository votationRepository;
    private final AssociateRepository associateRepository;
    private final ValidateCPFService validateCPFService;
    private final ModelMapper modelMapper;

    public VotationServiceImpl(SessionRepository sessionRepository, VotationRepository votationRepository, AssociateRepository associateRepository, ValidateCPFService validateCPFService, ModelMapper modelMapper) {
        this.sessionRepository = sessionRepository;
        this.votationRepository = votationRepository;
        this.associateRepository = associateRepository;
        this.validateCPFService = validateCPFService;
        this.modelMapper = modelMapper;
    }

    @Override
    public VoteCountDTO countVotes(Long sessionId) {
        var session = sessionRepository.findById(sessionId).orElseThrow(() -> new ObjectNotFoundException("Sessão de voto não encontrada"));
        if (session.isOpen())
            throw new CountVoteSessionOpenException(ExceptionEnum.COUNT_VOTE_SESSION_OPEN.getDescription());

        var dto = VoteCountDTO.builder().build();
        dto.setVotesYes(votationRepository.countBySessionIdAndVoteTrue(sessionId));
        dto.setVotesNo(votationRepository.countBySessionIdAndVoteFalse(sessionId));
        return dto;
    }

    @Override
    public VoteRequestDTO vote(Long sessionId, boolean vote, Long associateId) {
        var dto = VoteRequestDTO.builder().build();
        var session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new ObjectNotFoundException(ExceptionEnum.RESOURCE_NOT_FOUND.getDescription()));
        if (!session.isOpen())
            throw new CountVoteSessionOpenException(ExceptionEnum.COUNT_VOTE_SESSION_OPEN.getDescription());

        var votation = votationRepository.findBySessionIdAndAssociateId(sessionId, associateId);
        if (votation != null)
            throw new DuplicateVoteException(ExceptionEnum.DUPLICATE_VOTE.getDescription());
        else {
            var associate = associateRepository.findById(associateId)
                    .orElseThrow(() -> new ObjectNotFoundException(ExceptionEnum.RESOURCE_NOT_FOUND.getDescription()));
            validateCPFService.validateCPF(associate.getCpf());
            votation = Votation.builder()
                    .session(session)
                    .associate(associate)
                    .vote(vote).build();
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
