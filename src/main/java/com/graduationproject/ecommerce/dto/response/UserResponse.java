package com.graduationproject.ecommerce.dto.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserResponse {
    private Long id;
    private String userName;
    private String password;
}
