package com.arthurbarbosa.votacao.services;

import com.arthurbarbosa.votacao.dto.AssociateRequestDTO;
import com.arthurbarbosa.votacao.dto.AssociateResponseDTO;
import com.arthurbarbosa.votacao.entities.Associate;
import com.arthurbarbosa.votacao.repositories.AssociateRepository;
import com.arthurbarbosa.votacao.services.impl.AssociateServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class AssociateServiceTest {

    private AssociateService associateService;
    @MockBean
    private AssociateRepository associateRepository;

    @BeforeEach
    public void setUp() {
        this.associateService = new AssociateServiceImpl(associateRepository);
    }

    @Test
    public void should_create_associate() {
        var associate = Associate.builder().name("Arthur").cpf("07390397098").build();
        var dto = AssociateRequestDTO.builder().name("Arthur").cpf("07390397098").build();
        var associateSave = Associate.builder().id(1L).name("Arthur").cpf("07390397098").build();

        Mockito.when(associateRepository.save(associate)).thenReturn(associateSave);

        associateService.save(dto);

        assertThat(associateSave.getId()).isNotNull();
        assertThat(associateSave.getName()).isEqualTo("Arthur");
        assertThat(associateSave.getCpf()).isEqualTo("07390397098");
    }

    @Test
    public void should_find_associate_by_id() {
        Long associateId = 1L;
        var associate = Associate.builder().id(associateId).name("Arthur").cpf("07390397098").build();

        Mockito.when(associateRepository.findById(associateId)).thenReturn(Optional.of(associate));

        var dto = associateService.findById(associateId);

        assertThat(dto.getName()).isEqualTo(associate.getName());
        assertThat(dto.getCpf()).isEqualTo(associate.getCpf());
    }

    @Test
    public void should_return_associate_list(){
        Long associateId = 1L;
        var associate = Associate.builder().id(associateId).name("Arthur").cpf("07390397098").build();
        var associate1 = Associate.builder().id(1L).name("Barbosa").cpf("12345678900").build();

        List<Associate> associates = new ArrayList<>(Arrays.asList(associate, associate1));

        Mockito.when(associateRepository.findAll()).thenReturn(associates);

        List<AssociateResponseDTO> dtos = associateService.findAll();
        assertThat(dtos.size()).isEqualTo(2);
    }


}
