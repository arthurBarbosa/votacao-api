package com.arthurbarbosa.votacao.services;

import com.arthurbarbosa.votacao.entities.Session;
import com.arthurbarbosa.votacao.repositories.AssociateRepository;
import com.arthurbarbosa.votacao.repositories.SessionRepository;
import com.arthurbarbosa.votacao.repositories.VotationRepository;
import com.arthurbarbosa.votacao.services.impl.VotationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class VotationServiceTest {

    private VotationService votationService;

    @MockBean
    private VotationRepository votationRepository;
    @MockBean
    private SessionRepository sessionRepository;
    @MockBean
    private AssociateRepository associateRepository;


    @BeforeEach
    public void setUp() {
        this.votationService = new VotationServiceImpl(sessionRepository, votationRepository, associateRepository);
    }

    @Test
    public void should_count_votes(){
        Long sessionId = 1L;
        var session = Session.builder().id(1L).build();
        Mockito.when(sessionRepository.findById(sessionId)).thenReturn(Optional.of(session));

        Mockito.when(votationRepository.countBySessionIdAndVoteTrue(sessionId)).thenReturn(5L);
        Mockito.when(votationRepository.countBySessionIdAndVoteFalse(sessionId)).thenReturn(6L);

        var dto = votationService.countVotes(sessionId);

        assertThat(dto.getVotesYes()).isGreaterThanOrEqualTo(0);
        assertThat(dto.getVotesNo()).isGreaterThanOrEqualTo(0);
    }
}