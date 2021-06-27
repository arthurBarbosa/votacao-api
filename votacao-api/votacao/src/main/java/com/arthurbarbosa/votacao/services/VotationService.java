package com.arthurbarbosa.votacao.services;

import com.arthurbarbosa.votacao.dto.VoteCountDTO;
import com.arthurbarbosa.votacao.dto.VoteRequestDTO;

public interface VotationService {
    VoteCountDTO countVotes(Long sessionId);

    VoteRequestDTO vote(Long sessionId, boolean vote, Long associateId);
}
