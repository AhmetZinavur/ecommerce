package com.graduationproject.ecommerce.exception.customexception;

public class NoSuchProductExistException extends RuntimeException{
    public CustomeException customeException;

    public NoSuchProductExistException(CustomeException customeException) {
        super(customeException.getMessage());
        this.customeException = customeException;
    }
}
