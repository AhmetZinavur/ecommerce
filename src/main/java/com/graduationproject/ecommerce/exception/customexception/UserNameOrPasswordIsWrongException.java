package com.graduationproject.ecommerce.exception.customexception;

public class UserNameOrPasswordIsWrongException extends RuntimeException{
    public CustomeException customeException;

    public UserNameOrPasswordIsWrongException(CustomeException customeException) {
        super(customeException.getMessage());
        this.customeException = customeException;
    }
}
