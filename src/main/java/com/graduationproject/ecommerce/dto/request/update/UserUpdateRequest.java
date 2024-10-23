package com.graduationproject.ecommerce.dto.request.update;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserUpdateRequest {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String userName;
    private String password;
}
