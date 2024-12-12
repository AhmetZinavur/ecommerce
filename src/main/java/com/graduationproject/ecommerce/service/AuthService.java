package com.graduationproject.ecommerce.service;

import com.graduationproject.ecommerce.dto.request.login.UserLoginRequest;
import com.graduationproject.ecommerce.dto.request.register.AdminRegisterRequest;
import com.graduationproject.ecommerce.dto.request.register.CustomerRegisterRequest;
import com.graduationproject.ecommerce.dto.request.register.StoreOwnerRegisterRequest;
import com.graduationproject.ecommerce.dto.response.UserResponse;
import com.graduationproject.ecommerce.dto.response.UserSaveResponse;
import com.graduationproject.ecommerce.entity.Auth;
import com.graduationproject.ecommerce.entity.enums.Role;
import com.graduationproject.ecommerce.exception.customexception.CustomeException;
import com.graduationproject.ecommerce.exception.customexception.SuchUserAlreadyExistsException;
import com.graduationproject.ecommerce.mapper.UserMapper;
import org.springframework.stereotype.Service;

import com.graduationproject.ecommerce.repository.AuthRepository;
import com.graduationproject.ecommerce.util.JWTManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final UserService userService;
    private final JWTManager jWTManager;

    @Transactional
    public void saveAdmin(AdminRegisterRequest adminRegisterRequest) {
        if(userService.isUserExist(adminRegisterRequest.getUserName(), adminRegisterRequest.getEmail())) {
            throw new SuchUserAlreadyExistsException(CustomeException.SUCH_USER_ALREADY_EXISTS_EXCEPTION);
        }
        
        UserSaveResponse user = userService.save(UserMapper.INSTANCE.adminRegisterRequestToUser(adminRegisterRequest));        
        authRepository.save(Auth.builder()
                .role(Role.ADMIN)
                .user(UserMapper.INSTANCE.userSaveResponseToUser(user))
                .build()
        );
    }
    
    @Transactional
    public void saveCustomer(CustomerRegisterRequest customerRegisterRequest) {
        if(userService.isUserExist(customerRegisterRequest.getUserName(), customerRegisterRequest.getEmail())) {
            throw new SuchUserAlreadyExistsException(CustomeException.SUCH_USER_ALREADY_EXISTS_EXCEPTION);
        }
        
        UserSaveResponse user = userService.save(UserMapper.INSTANCE.customerRegisterRequestToUser(customerRegisterRequest));
        authRepository.save(Auth.builder()
                .role(Role.CUSTOMER)
                .user(UserMapper.INSTANCE.userSaveResponseToUser(user))
                .build()
        );
    }
    
    @Transactional
    public void saveStoreOwner(StoreOwnerRegisterRequest storeOwnerRegisterRequest) {
        if(userService.isUserExist(storeOwnerRegisterRequest.getUserName(), storeOwnerRegisterRequest.getEmail())) {
            throw new SuchUserAlreadyExistsException(CustomeException.SUCH_USER_ALREADY_EXISTS_EXCEPTION);
        }
        
        UserSaveResponse user = userService.save(UserMapper.INSTANCE.storeOwnerRegisterRequestToUser(storeOwnerRegisterRequest));
        authRepository.save(Auth.builder()
                .role(Role.STOREOWNER)
                .user(UserMapper.INSTANCE.userSaveResponseToUser(user))
                .build()
        );
    }
    
    public String login(UserLoginRequest userLoginRequest) {
        UserResponse userResponse = userService.checkUserAndUserPassword(userLoginRequest);
        
        return jWTManager.generateToken(userResponse.getId());
    }

}