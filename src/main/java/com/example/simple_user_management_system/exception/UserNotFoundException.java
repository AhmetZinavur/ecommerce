package com.example.simple_user_management_system.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super("User not found for id: " + id);
    }
    
}
