package com.graduationproject.ecommerce.dto.request.login;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserLoginRequest {
    private String userName;
    private String password;
}
