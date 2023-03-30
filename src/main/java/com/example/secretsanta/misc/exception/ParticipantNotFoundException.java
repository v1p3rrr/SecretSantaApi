package com.example.secretsanta.misc.exception;

public class ParticipantNotFoundException extends RuntimeException {
    public ParticipantNotFoundException(Long id){
        super("Participant with id " + id + " was not found");
    }    
}