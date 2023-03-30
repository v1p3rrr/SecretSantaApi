package com.example.secretsanta.misc.exception;


public class GroupNotFoundException extends RuntimeException {
    public GroupNotFoundException(Long id){
        super("Group with id " + id + " was not found");
    }
}
