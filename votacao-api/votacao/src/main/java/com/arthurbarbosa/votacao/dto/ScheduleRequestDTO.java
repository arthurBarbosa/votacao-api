package com.arthurbarbosa.votacao.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleRequestDTO {

    @NotEmpty
    @Schema(description = "Schedule description", example = "Dividendos")
    private String description;
}
