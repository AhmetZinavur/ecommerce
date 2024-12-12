package com.graduationproject.ecommerce.exception.customexception;

public class ProductAlreadyExistException extends RuntimeException{
    public CustomeException customeException;

    public ProductAlreadyExistException(CustomeException customeException) {
        super(customeException.getMessage());
        this.customeException = customeException;
    }
}
