package com.arthurbarbosa.votacao.dto;

import com.arthurbarbosa.votacao.entities.Schedule;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleResponseDTO {

    @Schema(description = "Schedule's id", example = "1")
    private Long id;

    @NotEmpty
    @Schema(description = "Schedule description", example = "Dividendos")
    private String description;

    public ScheduleResponseDTO(Schedule entity) {
        this.id = entity.getId();
        this.description = entity.getDescription();
    }
}
