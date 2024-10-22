package com.graduationproject.ecommerce.service;

import org.springframework.stereotype.Service;

import com.graduationproject.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    
    private final ProductRepository productRepository;   
    
}
