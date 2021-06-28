package com.arthurbarbosa.votacao.services;

import com.arthurbarbosa.votacao.entities.Associate;
import com.arthurbarbosa.votacao.entities.Schedule;
import com.arthurbarbosa.votacao.entities.Session;
import com.arthurbarbosa.votacao.entities.Votation;
import com.arthurbarbosa.votacao.repositories.AssociateRepository;
import com.arthurbarbosa.votacao.repositories.SessionRepository;
import com.arthurbarbosa.votacao.repositories.VotationRepository;
import com.arthurbarbosa.votacao.services.impl.VotationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class VotationServiceTest {

    private VotationService votationService;

    private ValidateCPFService validateCPFService;

    @MockBean
    private VotationRepository votationRepository;
    @MockBean
    private SessionRepository sessionRepository;
    @MockBean
    private AssociateRepository associateRepository;

    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        this.votationService = new VotationServiceImpl(sessionRepository, votationRepository, associateRepository, validateCPFService, modelMapper);
    }

    @Test
    public void should_count_votes() {
        Long sessionId = 1L;
        var session = Session.builder().id(1L).build();
        Mockito.when(sessionRepository.findById(sessionId)).thenReturn(Optional.of(session));

        Mockito.when(votationRepository.countBySessionIdAndVoteTrue(sessionId)).thenReturn(5L);
        Mockito.when(votationRepository.countBySessionIdAndVoteFalse(sessionId)).thenReturn(6L);

        var dto = votationService.countVotes(sessionId);

        assertThat(dto.getVotesYes()).isGreaterThanOrEqualTo(0);
        assertThat(dto.getVotesNo()).isGreaterThanOrEqualTo(0);
    }

    @Test
    public void should_return_vote_success() {
        boolean vote = false;
        var schedule = Schedule.builder().id(1L).build();
        var session = Session.builder().id(1L).isOpen(true).schedule(schedule).build();
        Mockito.when(sessionRepository.findById(1L)).thenReturn(Optional.of(session));
        var associate = Associate.builder().id(1L).build();
        Mockito.when(associateRepository.findById(1L)).thenReturn(Optional.of(associate));

        var votation = Votation.builder().associate(associate).vote(vote).session(session).build();
        var savedVotation = Votation.builder().id(1L).associate(associate).vote(vote).session(session).build();

        Mockito.when(votationRepository.save(votation)).thenReturn(savedVotation);

        votationService.vote(session.getId(), vote, associate.getId());

        assertThat(savedVotation.getId()).isNotNull();
        assertThat(session.isOpen()).isTrue();
        assertThat(savedVotation.getVote()).isNotNull();
    }
}
