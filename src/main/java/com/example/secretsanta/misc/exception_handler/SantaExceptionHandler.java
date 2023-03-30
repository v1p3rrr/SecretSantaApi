package com.example.secretsanta.misc.exception_handler;

import com.example.secretsanta.misc.exception.GroupNotFoundException;
import com.example.secretsanta.misc.exception.ParticipantNotFoundException;
import com.example.secretsanta.misc.exception.TossException;
import com.example.secretsanta.misc.responce_message.DefaultResponseMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SantaExceptionHandler {
    @ExceptionHandler(GroupNotFoundException.class)
    public ResponseEntity<DefaultResponseMessage> handleGroupNotFoundException(GroupNotFoundException e, HttpServletRequest request){
        return new ResponseEntity<>(new DefaultResponseMessage(HttpStatus.NOT_FOUND.value(), request.getRequestURI(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ParticipantNotFoundException.class)
    public ResponseEntity<DefaultResponseMessage> handleParticipantNotFoundException(ParticipantNotFoundException e, HttpServletRequest request){
        return new ResponseEntity<>(new DefaultResponseMessage(HttpStatus.NOT_FOUND.value(), request.getRequestURI(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TossException.class)
    public ResponseEntity<DefaultResponseMessage> handleTossException(TossException e, HttpServletRequest request){
        return new ResponseEntity<>(new DefaultResponseMessage(HttpStatus.CONFLICT.value(), request.getRequestURI(), e.getMessage()), HttpStatus.CONFLICT);
    }
}
