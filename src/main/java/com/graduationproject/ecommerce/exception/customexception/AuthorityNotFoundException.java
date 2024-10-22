package com.graduationproject.ecommerce.exception.customexception;

public class AuthorityNotFoundException extends RuntimeException{
    public CustomeException customeException;

    public AuthorityNotFoundException(CustomeException customeException) {
        super(customeException.getMessage());
        this.customeException = customeException;
    }
}