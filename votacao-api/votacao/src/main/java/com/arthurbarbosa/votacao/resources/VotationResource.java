package com.arthurbarbosa.votacao.resources;

import com.arthurbarbosa.votacao.dto.VoteCountDTO;
import com.arthurbarbosa.votacao.dto.VoteRequestDTO;
import com.arthurbarbosa.votacao.services.VotationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/votations")
public class VotationResource {

    private final VotationService votationService;

    public VotationResource(VotationService votationService) {
        this.votationService = votationService;
    }

    @GetMapping(value = "/{sessionId}/count-result")
    public ResponseEntity<VoteCountDTO> countVoteResult(@PathVariable Long sessionId) {
        log.info("retrieving the vote result for session id: {}", sessionId);
        return ResponseEntity.ok().body(votationService.countVotes(sessionId));
    }

    @PutMapping(value = "/session/{sessionId}")
    public ResponseEntity<VoteRequestDTO> vote(@PathVariable Long sessionId, @RequestParam boolean vote, @RequestParam Long associateId) {
        log.info("Voting in session id: {}", sessionId, " vote: {}", vote, " withe the assosciate id: {}", associateId);
        return ResponseEntity.ok().body(votationService.vote(sessionId, vote, associateId));
    }

}
