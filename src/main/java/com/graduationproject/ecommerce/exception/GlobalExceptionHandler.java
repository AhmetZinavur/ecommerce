package com.graduationproject.ecommerce.exception;

import com.graduationproject.ecommerce.exception.customexception.AdminAndCustomerCanNotBeStoreOwner;
import com.graduationproject.ecommerce.exception.customexception.ApproveOrRejectExcepiton;
import com.graduationproject.ecommerce.exception.customexception.AuthorityNotFoundException;
import com.graduationproject.ecommerce.exception.customexception.CustomeException;
import com.graduationproject.ecommerce.exception.customexception.InvalidTokenException;
import com.graduationproject.ecommerce.exception.customexception.NoProductFoundToDisplay;
import com.graduationproject.ecommerce.exception.customexception.NoSuchProductExistException;
import com.graduationproject.ecommerce.exception.customexception.NoSuchStoreExistsException;
import com.graduationproject.ecommerce.exception.customexception.NoSuchUserExistsException;
import com.graduationproject.ecommerce.exception.customexception.NotEnoughStockException;
import com.graduationproject.ecommerce.exception.customexception.OrderNotFoundException;
import com.graduationproject.ecommerce.exception.customexception.ProductAlreadyExistException;
import com.graduationproject.ecommerce.exception.customexception.ProductDoesNotBelongToYourStore;
import com.graduationproject.ecommerce.exception.customexception.SuchStoreAlreadyExistsException;
import com.graduationproject.ecommerce.exception.customexception.SuchUserAlreadyExistsException;
import com.graduationproject.ecommerce.exception.customexception.UnauthorizedAccessException;
import com.graduationproject.ecommerce.exception.customexception.UserNameOrPasswordIsWrongException;
import com.graduationproject.ecommerce.exception.response.ExceptionResponse;
import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ExceptionResponse response(CustomeException customeException) {
        return ExceptionResponse.builder()
                .statusCode(customeException.getStatusCode())
                .message(customeException.getMessage())
                .timeStamp(LocalDateTime.now().toString())
                .build();
    }

    @ExceptionHandler(NoSuchUserExistsException.class)
    @ResponseBody
    public ExceptionResponse noSuchUserExistsException(NoSuchUserExistsException exception) {
        return response(exception.customeException);
    }

    @ExceptionHandler(SuchUserAlreadyExistsException.class)
    @ResponseBody
    public ExceptionResponse suchUserAlreadyExistsException(SuchUserAlreadyExistsException exception) {
        return response(exception.customeException);
    }

    @ExceptionHandler(NoSuchStoreExistsException.class)
    @ResponseBody
    public ExceptionResponse noSuchStoreExistsException(NoSuchStoreExistsException exception) {
        return response(exception.customeException);
    }

    @ExceptionHandler(SuchStoreAlreadyExistsException.class)
    @ResponseBody
    public ExceptionResponse suchStoreAlreadyExistsException(SuchStoreAlreadyExistsException exception) {
        return response(exception.customeException);
    }

    @ExceptionHandler(UserNameOrPasswordIsWrongException.class)
    @ResponseBody
    public ExceptionResponse userNameOrPasswordIsWrongException(UserNameOrPasswordIsWrongException exception) {
        return response(exception.customeException);
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    @ResponseBody
    public ExceptionResponse unauthorizedAccessException(UnauthorizedAccessException exception) {
        return response(exception.customeException);
    }

    @ExceptionHandler(InvalidTokenException.class)
    @ResponseBody
    public ExceptionResponse invalidTokenException(InvalidTokenException excepiton) {
        return response(excepiton.customeException);
    }

    @ExceptionHandler(AuthorityNotFoundException.class)
    @ResponseBody
    public ExceptionResponse authorityNotFoundException(AuthorityNotFoundException excepiton) {
        return response(excepiton.customeException);
    }

    @ExceptionHandler(AdminAndCustomerCanNotBeStoreOwner.class)
    @ResponseBody
    public ExceptionResponse adminAndCustomerCanNotBeStoreOwner(AdminAndCustomerCanNotBeStoreOwner exception) {
        return response(exception.customeException);
    }

    @ExceptionHandler(NoProductFoundToDisplay.class)
    @ResponseBody
    public ExceptionResponse noProductFoundToDisplay(NoProductFoundToDisplay exception) {
        return response(exception.customeException);
    }

    @ExceptionHandler(NoSuchProductExistException.class)
    @ResponseBody
    public ExceptionResponse noSuchProductExistException(NoSuchProductExistException exception) {
        return response(exception.customeException);
    }

    @ExceptionHandler(ProductDoesNotBelongToYourStore.class)
    @ResponseBody
    public ExceptionResponse productDoesNotBelongToYourStore(ProductDoesNotBelongToYourStore exception) {
        return response(exception.customeException);
    }

    @ExceptionHandler(ProductAlreadyExistException.class)
    @ResponseBody
    public ExceptionResponse productAlreadyExistException(ProductAlreadyExistException exception) {
        return response(exception.customeException);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseBody
    public ExceptionResponse orderNotFoundException(OrderNotFoundException exception) {
        return response(exception.customeException);
    }
    
    @ExceptionHandler(ApproveOrRejectExcepiton.class)
    @ResponseBody
    public ExceptionResponse approveOrRejectExcepiton(ApproveOrRejectExcepiton exception) {
        return response(exception.customeException);
    }
    
    @ExceptionHandler(NotEnoughStockException.class)
    @ResponseBody
    public ExceptionResponse notEnoughStockException(NotEnoughStockException exception) {
        return response(exception.customeException);
    }
}
