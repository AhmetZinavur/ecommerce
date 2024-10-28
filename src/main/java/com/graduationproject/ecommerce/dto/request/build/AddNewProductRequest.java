package com.graduationproject.ecommerce.dto.request.build;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AddNewProductRequest {
    private String productName;
    private Integer productQuantity;
    private Double productPrice;
}