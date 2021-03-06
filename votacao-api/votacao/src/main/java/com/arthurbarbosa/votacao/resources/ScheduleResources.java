package com.arthurbarbosa.votacao.resources;

import com.arthurbarbosa.votacao.dto.ScheduleRequestDTO;
import com.arthurbarbosa.votacao.dto.ScheduleResponseDTO;
import com.arthurbarbosa.votacao.services.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/schedules")
public class ScheduleResources {

    private final ScheduleService scheduleService;

    public ScheduleResources(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @Operation(summary = "Criar uma nova pauta")
    @ApiResponse(responseCode = "201")
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ScheduleRequestDTO dto) {
        var responseDTO = scheduleService.save(dto);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(responseDTO.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Operation(summary = "Buscar uma paute pelo id")
    @ApiResponse(responseCode = "200")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ScheduleResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(scheduleService.findById(id));
    }

    @Operation(summary = "Listar todas as pautas")
    @ApiResponse(responseCode = "201")
    @GetMapping(value = "/all")
    public ResponseEntity<List<ScheduleResponseDTO>> findAll(){
        return ResponseEntity.ok().body(scheduleService.findAll());
    }
}
