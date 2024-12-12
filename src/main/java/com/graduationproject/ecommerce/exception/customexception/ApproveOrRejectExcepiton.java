package com.graduationproject.ecommerce.exception.customexception;

public class ApproveOrRejectExcepiton extends RuntimeException{
    public CustomeException customeException;

    public ApproveOrRejectExcepiton(CustomeException customeException) {
        super(customeException.getMessage());
        this.customeException = customeException;
    }
}
