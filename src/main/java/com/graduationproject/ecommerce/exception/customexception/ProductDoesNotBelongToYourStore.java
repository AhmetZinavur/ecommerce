package com.graduationproject.ecommerce.exception.customexception;

public class ProductDoesNotBelongToYourStore extends RuntimeException{
    public CustomeException customeException;

    public ProductDoesNotBelongToYourStore(CustomeException customeException) {
        super(customeException.getMessage());
        this.customeException = customeException;
    }
}
