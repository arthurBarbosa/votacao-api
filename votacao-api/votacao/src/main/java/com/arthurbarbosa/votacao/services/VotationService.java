package com.arthurbarbosa.votacao.services;

import com.arthurbarbosa.votacao.dto.VoteCountDTO;

public interface VotationService {
    VoteCountDTO countVotes(Long sessionId);
}
