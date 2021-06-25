package com.arthurbarbosa.votacao.resources;

import com.arthurbarbosa.votacao.dto.AssociateRequestDTO;
import com.arthurbarbosa.votacao.services.AssociateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

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

}
