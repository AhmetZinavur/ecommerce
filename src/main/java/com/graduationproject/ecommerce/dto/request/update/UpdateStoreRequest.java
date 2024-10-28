package com.graduationproject.ecommerce.dto.request.update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateStoreRequest {
    private Long id;
    private String storeName;
    private String taxNumber;
    private String address;
}
