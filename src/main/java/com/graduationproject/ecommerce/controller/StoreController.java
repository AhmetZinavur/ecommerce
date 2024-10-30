package com.graduationproject.ecommerce.controller;

import com.graduationproject.ecommerce.dto.request.build.AddNewProductRequest;
import com.graduationproject.ecommerce.dto.request.build.BuildStoreRequest;
import com.graduationproject.ecommerce.dto.request.update.ProductUpdateRequest;
import com.graduationproject.ecommerce.dto.request.update.UpdateOrderStatusRequest;
import com.graduationproject.ecommerce.dto.response.ProductResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.graduationproject.ecommerce.service.StoreService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("store")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping("build-store-for-store-owner/{token}")
    public void buildStoreForStoreOwner(@PathVariable("token") String token, @RequestBody BuildStoreRequest buildStoreRequest) {
        storeService.buildStoreForStoreOwner(token, buildStoreRequest);
    }

    @PostMapping("build-store-for-admin/{token}")
    public void buildStoreForAdmin(@PathVariable("token") String token, Long storeOwnerId, @RequestBody BuildStoreRequest buildStoreRequest) {
        storeService.buildStoreForAdmin(token, storeOwnerId, buildStoreRequest);
    }

    @PostMapping("add-product/{token}")
    public void saveProduct(@PathVariable("token") String token, AddNewProductRequest addNewProductRequest) {
        storeService.saveProduct(token, addNewProductRequest);
    }
    
    @PostMapping("update-order-status/{token}")
    public void updateOrderStatus(@PathVariable("token") String token, @RequestBody UpdateOrderStatusRequest updateOrderStatusRequest) {
        storeService.updateOrderStatus(token, updateOrderStatusRequest);
    }

    @GetMapping("find-product-by/{id}")
    public ProductResponse findProductById(@PathVariable Long id) {
       return storeService.findProductById(id);
    }

    @GetMapping("find-all-product/{token}")
    public List<ProductResponse> findAllProduct(@PathVariable("token") String token) {
        return storeService.findAllProduct(token);
    }

    @PutMapping("update-product/{token}")
    public void updateProduct(@PathVariable("token") String token, ProductUpdateRequest productUpdateRequest) {
        storeService.updateProduct(token, productUpdateRequest);
    }

    @DeleteMapping("delete-product/{token}")
    public void deleteProduct(@PathVariable("token") String token, Long id) {
        storeService.deleteProduct(token ,id);
    }
}
