package com.example.simple_user_management_system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ExceptionResponse userNotFoundHandler(UserNotFoundException exception) {
        return new ExceptionResponse(exception.getMessage());
    }
    
    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ExceptionResponse userAlreadyExistsHandler(UserAlreadyExistsException exception) {
        return new ExceptionResponse(exception.getMessage());
    }
}
