package com.arthurbarbosa.votacao.services.exception;

public class UnableToVoteException extends RuntimeException {

    public UnableToVoteException(String message) {
        super(message);
    }
}
