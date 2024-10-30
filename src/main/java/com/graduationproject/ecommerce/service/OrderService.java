package com.graduationproject.ecommerce.service;

import org.springframework.stereotype.Service;

import com.graduationproject.ecommerce.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    
    
}
