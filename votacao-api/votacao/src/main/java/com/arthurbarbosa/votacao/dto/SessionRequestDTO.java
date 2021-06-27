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
public class SessionRequestDTO {

    @Schema(description = "session's duration. In minutes", example = "3", defaultValue = "1")
    private Long duration;
    @Schema(description = "schedule's id", example = "1")
    private Long scheduleId;
}
