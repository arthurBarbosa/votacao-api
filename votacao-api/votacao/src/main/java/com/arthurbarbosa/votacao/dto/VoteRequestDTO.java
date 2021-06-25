package com.arthurbarbosa.votacao.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteRequestDTO {

    @Schema(description = "Schedule's id", example = "1")
    private Long scheduleId;

    @Schema(description = "Associate's id", example = "1")
    private Long associateId;

    @Schema(description = "session's id", example = "1")
    private Long sessionId;

    @Schema(description = "sim = 1 / não = 0", example = "1")
    private String vote;
}
