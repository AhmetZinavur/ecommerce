package com.graduationproject.ecommerce.dto.request.update;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class UpdateOrderStatusRequest {
    private Long id;
    private String orderStatus;
}
