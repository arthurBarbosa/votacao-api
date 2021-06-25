package com.arthurbarbosa.votacao.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssociateRequestDTO {

    @Schema(description = "Associate's CPF", example = "01373891000", minimum = "11", maximum = "11")
    private String cpf;

    @Schema(description = "Associate's Name", example = "Fernando")
    private String name;
}
