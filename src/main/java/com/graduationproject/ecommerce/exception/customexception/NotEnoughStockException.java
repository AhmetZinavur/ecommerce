package com.graduationproject.ecommerce.exception.customexception;

public class NotEnoughStockException extends RuntimeException{
    public CustomeException customeException;

    public NotEnoughStockException(CustomeException customeException) {
        super(customeException.getMessage());
        this.customeException = customeException;
    }
}
