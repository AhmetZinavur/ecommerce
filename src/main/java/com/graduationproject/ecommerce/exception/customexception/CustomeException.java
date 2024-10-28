package com.graduationproject.ecommerce.exception.customexception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomeException {
    NO_SUCH_USER_EXISTS_EXCEPTION(1001,"No Such User Exists"),
    SUCH_USER_ALREADY_EXISTS_EXCEPTION(1002, "Such User Already Exists"),

    NO_SUCH_STORE_EXISTS_EXCEPTION(2001, "No Such Store Exists"),
    SUCH_STORE_ALREADY_EXISTS_EXCEPTION(2002, "Such Store Already Exists"),

    TOKEN_CANNOT_CREATE_EXCEPTION(3001, "Token Can't Create Excepiton"),
    INVALID_TOKEN_EXCEPTION(3002,"Invalid Token Exception"),

    USER_NAME_OR_PASSWORD_IS_WRONG_EXCEPTION(4001, "User Name or Password is Wrong"),
    UNAUTHORIZED_ACCESS_EXCEPTION(4002, "Unauthorized Access"),
    AUTHORITY_NOT_FOUND_EXCEPTION(4003,"Authority Not Found"),
    ADMIN_AND_CUSTOMER_CAN_NOT_BE_STORE_OWNER_EXCEPTION(4004,"Admin and Customer cannot be store owner"),

    NO_PRODUCT_FOUND_TO_DISPLAY_EXCEPTION(5001,"No products found to display"),
    NO_SUCH_PRODUCT_EXIST_EXCEPTION(5002,"No Such product exist"),
    PRODUCT_DOES_NOT_BELONG_TO_YOUR_STORE_EXCEPTION(5003, "This product does not belong to your store");
    
    private final int statusCode;
    private final String message;
}
