package com.graduationproject.ecommerce.exception.customexception;

public class SuchStoreAlreadyExistsException extends RuntimeException{

    public CustomeException customeException;

    public SuchStoreAlreadyExistsException(CustomeException customeException) {
        super(customeException.getMessage());
        this.customeException = customeException;
    }
    
}
