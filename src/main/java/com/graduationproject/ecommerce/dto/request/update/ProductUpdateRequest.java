package com.graduationproject.ecommerce.dto.request.update;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ProductUpdateRequest {
    private Long id;
    private String productName;
    private Integer stockQuantity;
    private Double productPrice;
}
