package com.graduationproject.ecommerce.exception.customexception;

public class OrderNotFoundException extends RuntimeException{
    public CustomeException customeException;

    public OrderNotFoundException(CustomeException customeException) {
        super(customeException.getMessage());
        this.customeException = customeException;
    }
}
