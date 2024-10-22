package com.graduationproject.ecommerce.exception.customexception;

public class NoSuchUserExistsException extends RuntimeException{
    
    public CustomeException customeException;

    public NoSuchUserExistsException(CustomeException customeException) {
        super(customeException.getMessage());
        this.customeException = customeException;
    }

}
