package com.graduationproject.ecommerce.exception.customexception;

public class InvalidTokenException extends RuntimeException{
    public CustomeException customeException;

    public InvalidTokenException(CustomeException customeException) {
        super(customeException.getMessage());
        this.customeException = customeException;
    }
}
