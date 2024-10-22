package com.graduationproject.ecommerce.exception.customexception;

public class UnauthorizedAccessException extends RuntimeException{
    public CustomeException customeException;

    public UnauthorizedAccessException(CustomeException customeException) {
        super(customeException.getMessage());
        this.customeException = customeException;
    }
}
