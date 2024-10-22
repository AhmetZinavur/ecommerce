package com.graduationproject.ecommerce.exception.customexception;

public class SuchUserAlreadyExistsException extends RuntimeException{

    public CustomeException customeException;

    public SuchUserAlreadyExistsException(CustomeException customeException) {
        super(customeException.getMessage());
        this.customeException = customeException;
    }
    
}
