package com.graduationproject.ecommerce.dto.request.register;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CustomerRegisterRequest {
    private String name;
    private String lastName;
    private String email;
    private String userName;
    private String password;
}
