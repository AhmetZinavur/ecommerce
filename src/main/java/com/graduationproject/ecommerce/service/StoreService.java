package com.graduationproject.ecommerce.service;

import com.graduationproject.ecommerce.dto.request.build.BuildStoreRequest;
import com.graduationproject.ecommerce.dto.response.UserResponse;
import com.graduationproject.ecommerce.entity.Store;
import com.graduationproject.ecommerce.entity.User;
import com.graduationproject.ecommerce.exception.customexception.CustomeException;
import com.graduationproject.ecommerce.exception.customexception.InvalidTokenException;
import com.graduationproject.ecommerce.mapper.StoreMapper;
import com.graduationproject.ecommerce.mapper.UserMapper;
import org.springframework.stereotype.Service;

import com.graduationproject.ecommerce.repository.StoreRepository;
import com.graduationproject.ecommerce.util.JWTManager;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StoreService {
    
    private final StoreRepository storeRepository;
    private final UserService userService;
    private final JWTManager jWTManager;
    
    public void buildStore(String token, BuildStoreRequest buildStoreRequest) {
        
        Long id = jWTManager.validToken(token).orElseThrow(() -> new InvalidTokenException(CustomeException.INVALID_TOKEN_EXCEPTION));
        
        UserResponse response = userService.findById(id);
        
        Store store = StoreMapper.INSTANCE.buildStoreRequestToStore(buildStoreRequest);
        
        store.setStoreCreationDate(LocalDateTime.now());
        
        store.setUser(User.builder()
                .id(response.getId())
                .build());
        
        storeRepository.save(store);
    }
}
