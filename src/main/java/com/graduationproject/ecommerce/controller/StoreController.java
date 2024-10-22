package com.graduationproject.ecommerce.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.graduationproject.ecommerce.service.StoreService;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("store")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

}
