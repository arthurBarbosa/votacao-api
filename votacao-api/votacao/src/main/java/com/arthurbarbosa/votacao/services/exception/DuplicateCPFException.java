package com.arthurbarbosa.votacao.services.exception;

public class DuplicateCPFException extends RuntimeException{
    public DuplicateCPFException(String message) {
        super(message);
    }
}
