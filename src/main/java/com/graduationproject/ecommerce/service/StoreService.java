package com.graduationproject.ecommerce.service;

import org.springframework.stereotype.Service;

import com.graduationproject.ecommerce.repository.StoreRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
}
