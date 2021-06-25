package com.arthurbarbosa.votacao.resources;

import com.arthurbarbosa.votacao.dto.VoteCountDTO;
import com.arthurbarbosa.votacao.services.VotationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/votations")
public class VotationResource {

    private final VotationService votationService;

    public VotationResource(VotationService votationService) {
        this.votationService = votationService;
    }

    @GetMapping(value = "/{sessionId}/count-result")
    public ResponseEntity<VoteCountDTO> countVoteResult(@PathVariable Long sessionId){
        log.info("retrieving the vote result for session id: {}", sessionId);
        return ResponseEntity.ok().body(votationService.countVotes(sessionId));
    }


}
