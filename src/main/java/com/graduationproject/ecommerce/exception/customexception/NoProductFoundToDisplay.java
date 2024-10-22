package com.graduationproject.ecommerce.exception.customexception;

public class NoProductFoundToDisplay extends RuntimeException{
    public CustomeException customeException;

    public NoProductFoundToDisplay(CustomeException customeException) {
        super(customeException.getMessage());
        this.customeException = customeException;
    }
}
