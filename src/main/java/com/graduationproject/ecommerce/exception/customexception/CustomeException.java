package com.graduationproject.ecommerce.exception.customexception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomeException {
    NO_SUCH_USER_EXISTS_EXCEPTION(1001,"No Such User Exists Exception"),
    SUCH_USER_ALREADY_EXISTS_EXCEPTION(1002, "Such User Already Exists Exception"),

    NO_SUCH_STORE_EXISTS_EXCEPTION(2001, "No Such Store Exists Exception"),
    SUCH_STORE_ALREADY_EXISTS_EXCEPTION(2002, "Such Store Already Exists Exception"),

    TOKEN_CANNOT_CREATE_EXCEPTION(3001, "Token Can't Create Excepiton"),
    INVALID_TOKEN_EXCEPTION(3002,"Invalid Token Exception"),

    USER_NAME_OR_PASSWORD_IS_WRONG_EXCEPTION(4001, "User Name or Password is Wrong Exception"),
    UNAUTHORIZED_ACCESS_EXCEPTION(4002, "Unauthorized Access Exception"),
    AUTHORITY_NOT_FOUND_EXCEPTION(4003,"Authority Not Found Exception"),
    ADMIN_USER_CAN_NOT_BE_STORE_OWNER_EXCEPTION(4004,"Admin and Customer cannot be store owner"),

    NO_PRODUCT_FOUND_TO_DISPLAY_EXCEPTION(5001,"No products found to display"),
    NO_SUCH_PRODUCT_EXIST_EXCEPTION(5002,"No Such product exist");
    
    private final int statusCode;
    private final String message;
}
