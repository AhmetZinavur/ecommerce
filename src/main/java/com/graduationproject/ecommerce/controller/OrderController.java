package com.graduationproject.ecommerce.controller;

import com.graduationproject.ecommerce.dto.request.OrderRequest;
import com.graduationproject.ecommerce.service.OrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    
    @PostMapping("add-order/{token}")
    public void addOrder(@PathVariable String token, @RequestBody List<OrderRequest> orderRequests) {
        orderService.addOrder(token, orderRequests);
    }
}
