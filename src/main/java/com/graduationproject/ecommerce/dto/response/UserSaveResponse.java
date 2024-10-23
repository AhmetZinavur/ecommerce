package com.graduationproject.ecommerce.dto.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserSaveResponse {
    private Long id;
    private String accountCreationDate;
    private String accountUpdateDate;
}
