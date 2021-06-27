package com.arthurbarbosa.votacao.resources;

import com.arthurbarbosa.votacao.dto.AssociateRequestDTO;
import com.arthurbarbosa.votacao.dto.AssociateResponseDTO;
import com.arthurbarbosa.votacao.services.AssociateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/associates")
@Slf4j
public class AssociateResource {

    private final AssociateService associateService;

    public AssociateResource(AssociateService associateService) {
        this.associateService = associateService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Void> create(@RequestBody @Valid AssociateRequestDTO dto){
        log.info("creating an associate for CPF: {}", dto.getCpf());
        var responseDTO = associateService.save(dto);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(responseDTO.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AssociateResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(associateService.findById(id));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<AssociateResponseDTO>> findAll(){
        return ResponseEntity.ok().body(associateService.findAll());
    }


}
