package com.arthurbarbosa.votacao.services.exception;

public class InvalidSessionDurationException extends RuntimeException {

    public InvalidSessionDurationException(String message) {
        super(message);
    }
}
