package com.graduationproject.ecommerce.controller;

import com.graduationproject.ecommerce.dto.request.build.BuildStoreRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.graduationproject.ecommerce.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("store")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @PostMapping("build-store")
    public void buildStore(String token, @RequestBody BuildStoreRequest buildStoreRequest) {
        storeService.buildStore(token, buildStoreRequest);
    }
}
