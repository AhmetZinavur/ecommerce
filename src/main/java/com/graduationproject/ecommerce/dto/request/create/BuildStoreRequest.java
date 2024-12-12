package com.graduationproject.ecommerce.dto.request.create;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BuildStoreRequest {
    private String storeName;
    private String taxNumber;
    private String address;
}
