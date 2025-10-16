package com.domainchallenge.pets.utils.exception;

import lombok.Getter;

import java.util.UUID;

@Getter
public class PetNotFoundException extends RuntimeException {

    private final UUID transactionId;

    public PetNotFoundException(String message, UUID transactionId) {
        super(message);
        this.transactionId = transactionId;
    }

}
