package com.example.secretsanta.misc.exception;

public class TossException extends RuntimeException {
    public TossException(){
        super("Toss error");
    }
}