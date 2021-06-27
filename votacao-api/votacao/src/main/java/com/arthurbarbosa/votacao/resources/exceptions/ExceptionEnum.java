package com.arthurbarbosa.votacao.resources.exceptions;

import lombok.Getter;

@Getter
public enum ExceptionEnum {

    INVALID_DATA("/dados-invalidos", "Dados inválidos"),
    RESOURCE_NOT_FOUND("/recurso-nao-encontrado", "Recurso não encontrado"),
    DUPLICATE_CPF("/cpf-duplicado", "O CPF já existe na base de dados"),
    INVALID_SESSION_DURATION("/duracao-sessao-invalida", "A duração da sessão não pode ser menor que 1 minuto"),
    COUNT_VOTE_SESSION_OPEN("/votacao-sessao-aberta", "Não é possível ter o resultado da votação durante uma sessão aberta");

    private String uri;
    private String description;

    ExceptionEnum(String path, String description) {
        this.uri = "sicredi-app" + path;
        this.description = description;
    }
}
