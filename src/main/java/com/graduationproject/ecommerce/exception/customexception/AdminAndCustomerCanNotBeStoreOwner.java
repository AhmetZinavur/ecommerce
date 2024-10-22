package com.graduationproject.ecommerce.exception.customexception;

public class AdminAndCustomerCanNotBeStoreOwner extends RuntimeException{
    public CustomeException customeException;

    public AdminAndCustomerCanNotBeStoreOwner (CustomeException customeException) {
        super(customeException.getMessage());
        this.customeException = customeException;
    }
}
