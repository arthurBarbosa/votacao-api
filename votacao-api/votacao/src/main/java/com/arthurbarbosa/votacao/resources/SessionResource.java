package com.arthurbarbosa.votacao.resources;

import com.arthurbarbosa.votacao.dto.SessionRequestDTO;
import com.arthurbarbosa.votacao.dto.SessionResponseDTO;
import com.arthurbarbosa.votacao.services.SessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@Slf4j
@RestController
@RequestMapping(value = "/sessions")
public class SessionResource {

    private final SessionService sessionService;

    public SessionResource(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Operation(summary = "Criar uma nova sessão")
    @ApiResponse(responseCode = "201")
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid SessionRequestDTO dto) {
        var responseDTO = sessionService.save(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(responseDTO.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Operation(summary = "Buscar uma sessão por id")
    @ApiResponse(responseCode = "200")
    @GetMapping(value = "/{id}")
    public ResponseEntity<SessionResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(sessionService.findById(id));
    }

    @Operation(summary = "Atualiza uma sessão para status aberta")
    @ApiResponse(responseCode = "200")
    @PutMapping(value = "/{id}")
    public ResponseEntity<SessionResponseDTO> openSession(@PathVariable Long id) {
        log.info("Opening session with id: {}", id);
        return ResponseEntity.ok().body(sessionService.openSession(id));
    }

    @Operation(summary = "Atualiza uma sessão para status fechado")
    @ApiResponse(responseCode = "200")
    @PutMapping(value = "/{id}/close")
    public ResponseEntity<SessionResponseDTO> close(@PathVariable Long id) {
        log.info("Closing session with id: {}", id);
        return ResponseEntity.ok().body(sessionService.closeSession(id));
    }
}
