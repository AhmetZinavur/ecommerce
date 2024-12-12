package com.graduationproject.ecommerce.controller;

import com.graduationproject.ecommerce.dto.request.create.OrderRequest;
import com.graduationproject.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    
    @PostMapping("create-order/{token}")
    public void createOrder(@RequestParam String token, @RequestBody OrderRequest request) {
        orderService.addOrder(token, request);
    }
}
