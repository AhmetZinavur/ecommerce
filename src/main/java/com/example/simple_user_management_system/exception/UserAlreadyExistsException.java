package com.example.simple_user_management_system.exception;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String email) {
        super("User already exists by: " + email);
    }
    
}
