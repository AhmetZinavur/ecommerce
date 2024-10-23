package com.graduationproject.ecommerce.controller;

import com.graduationproject.ecommerce.dto.request.update.UserUpdateRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.graduationproject.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    
    @DeleteMapping("delete-admin")
    public void delete(Long id) {
        userService.delete(id);
    }
    
    @PutMapping("update-user")
    public void update(@RequestBody UserUpdateRequest userUpdateRequest) {
        userService.update(userUpdateRequest);
    }
}
