package com.graduationproject.ecommerce.exception.customexception;

public class NoSuchStoreExistsException extends RuntimeException{
    
    public CustomeException customeException;

    public NoSuchStoreExistsException(CustomeException customeException) {
        super(customeException.getMessage());
        this.customeException = customeException;
    }

}
