package com.arthurbarbosa.votacao.services.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CountVoteSessionOpenException extends RuntimeException {

    public CountVoteSessionOpenException(String s) {
        super(s);
    }
}
