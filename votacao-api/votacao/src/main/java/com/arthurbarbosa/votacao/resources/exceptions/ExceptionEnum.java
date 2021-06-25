package com.arthurbarbosa.votacao.resources.exceptions;

import lombok.Getter;

@Getter
public enum ExceptionEnum {

    INVALID_DATA("/dados-invalidos", "Dados inválidos");

    private String uri;
    private String description;

    ExceptionEnum(String path, String description) {
        this.uri = "sicredi-app" + path;
        this.description = description;
    }
}
