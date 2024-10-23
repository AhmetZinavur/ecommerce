package com.graduationproject.ecommerce.controller;

import com.graduationproject.ecommerce.dto.request.login.UserLoginRequest;
import com.graduationproject.ecommerce.dto.request.register.AdminRegisterRequest;
import com.graduationproject.ecommerce.dto.request.register.CustomerRegisterRequest;
import com.graduationproject.ecommerce.dto.request.register.StoreOwnerRegisterRequest;
import com.graduationproject.ecommerce.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    
    @PostMapping("admin-reigster")
    public void adminRegister(@RequestBody AdminRegisterRequest adminRegisterRequest) {
        authService.saveAdmin(adminRegisterRequest);
    }
    
    @PostMapping("customer-register")
    public void customerRegister(@RequestBody CustomerRegisterRequest customerRegisterRequest) {
        authService.saveCustomer(customerRegisterRequest);
    }
    
    @PostMapping("store-owner-register")
    public void storeOwnerRegister(@RequestBody StoreOwnerRegisterRequest storeOwnerRegisterRequest) {
        authService.saveStoreOwner(storeOwnerRegisterRequest);
    }
    
    @PostMapping("login")
    public String login(@RequestBody UserLoginRequest userLoginRequest) {
        return authService.login(userLoginRequest);
    }
}
