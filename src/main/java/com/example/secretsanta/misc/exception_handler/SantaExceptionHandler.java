package com.example.secretsanta.misc.exception_handler;

import com.example.secretsanta.misc.exception.GroupNotFoundException;
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
}
