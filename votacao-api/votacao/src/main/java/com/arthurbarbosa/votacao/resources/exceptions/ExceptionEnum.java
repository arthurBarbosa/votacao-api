package com.arthurbarbosa.votacao.resources.exceptions;

import lombok.Getter;

@Getter
public enum ExceptionEnum {

    INVALID_DATA("/dados-invalidos", "Dados inválidos"),
    RESOURCE_NOT_FOUND("/recurso-nao-encontrado", "Recurso não encontrado"),
    DUPLICATE_CPF("/cpf-duplicado", "O CPF já existe na base de dados"),
    INVALID_SESSION_DURATION("/duracao-sessao-invalida", "A duração da sessão não pode ser menor que 1 minuto"),
    SERVICE_UNAVALAIBLE("/servico-indisponivel", "Serviço (indisponivel/cpf inválido) tente novamente mais tarde."),
    UNABLE_TO_VOTE("/voto-nao-permitido", "Associado não habilitado para voto"),
    DUPLICATE_VOTE("/voto-duplicado", "Não é permitido votar mais de uma vez."),
    COUNT_VOTE_SESSION_OPEN("/votacao-sessao-aberta", "Por favor aguarde ou encerre a sessão para a contagem dos votos.");

    private String uri;
    private String description;

    ExceptionEnum(String path, String description) {
        this.uri = "sicredi-app" + path;
        this.description = description;
    }
}
