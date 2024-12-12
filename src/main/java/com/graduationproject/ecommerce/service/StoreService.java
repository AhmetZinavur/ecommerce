package com.graduationproject.ecommerce.service;

import com.graduationproject.ecommerce.dto.request.create.AddNewProductRequest;
import com.graduationproject.ecommerce.dto.request.create.BuildStoreRequest;
import com.graduationproject.ecommerce.dto.request.update.ProductUpdateRequest;
import com.graduationproject.ecommerce.dto.request.update.UpdateOrderStatusRequest;
import com.graduationproject.ecommerce.dto.request.update.UpdateStoreRequest;
import com.graduationproject.ecommerce.dto.response.ProductResponse;
import com.graduationproject.ecommerce.dto.response.UserResponse;
import com.graduationproject.ecommerce.entity.Store;
import com.graduationproject.ecommerce.entity.User;
import com.graduationproject.ecommerce.entity.enums.Role;
import com.graduationproject.ecommerce.exception.customexception.AdminAndCustomerCanNotBeStoreOwner;
import com.graduationproject.ecommerce.exception.customexception.CustomeException;
import com.graduationproject.ecommerce.exception.customexception.InvalidTokenException;
import com.graduationproject.ecommerce.exception.customexception.NoSuchStoreExistsException;
import com.graduationproject.ecommerce.exception.customexception.ProductDoesNotBelongToYourStore;
import com.graduationproject.ecommerce.exception.customexception.UnauthorizedAccessException;
import com.graduationproject.ecommerce.mapper.StoreMapper;
import org.springframework.stereotype.Service;

import com.graduationproject.ecommerce.repository.StoreRepository;
import com.graduationproject.ecommerce.util.JWTManager;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final ProductService productService;
    private final OrderService orderService;
    private final UserService userService;
    private final JWTManager jWTManager;

    @Transactional
    public void buildStoreForStoreOwner(String token, BuildStoreRequest buildStoreRequest) {

        Long id = jWTManager.validToken(token).orElseThrow(() -> new InvalidTokenException(CustomeException.INVALID_TOKEN_EXCEPTION));

        UserResponse response = userService.findById(id);

        if (!response.getRole().equals(Role.STOREOWNER.toString())) {
            throw new UnauthorizedAccessException(CustomeException.UNAUTHORIZED_ACCESS_EXCEPTION);
        }

        Store store = StoreMapper.INSTANCE.buildStoreRequestToStore(buildStoreRequest);

        store.setCreateAt(LocalDateTime.now());

        store.setUser(User.builder()
                .id(response.getId())
                .build());

        storeRepository.save(store);
    }

    @Transactional
    public void buildStoreForAdmin(String token, Long storeOwnerId, BuildStoreRequest buildStoreRequest) {

        jWTManager.validToken(token).orElseThrow(() -> new InvalidTokenException(CustomeException.INVALID_TOKEN_EXCEPTION));

        UserResponse response = userService.findById(storeOwnerId);

        if (response.getRole().equals(Role.ADMIN.toString()) || response.getRole().equals(Role.CUSTOMER.toString())) {
            throw new AdminAndCustomerCanNotBeStoreOwner(CustomeException.ADMIN_AND_CUSTOMER_CAN_NOT_BE_STORE_OWNER_EXCEPTION);
        }

        Store store = StoreMapper.INSTANCE.buildStoreRequestToStore(buildStoreRequest);

        store.setCreateAt(LocalDateTime.now());

        store.setUser(User.builder()
                .id(response.getId())
                .build());

        storeRepository.save(store);
    }

    @Transactional
    public void saveProduct(String token, AddNewProductRequest addNewProductRequest) {

        Long storeOwnerId = jWTManager.validToken(token).orElseThrow(() -> new InvalidTokenException(CustomeException.INVALID_TOKEN_EXCEPTION));

        Store store = storeRepository.findById(storeOwnerId).orElseThrow(() -> new NoSuchStoreExistsException(CustomeException.NO_SUCH_STORE_EXISTS_EXCEPTION));

        productService.save(store, addNewProductRequest);
    }

    @Transactional
    public void deleteStore(String token, Long storeId) {
        Long storeOwnerId = jWTManager.validToken(token).orElseThrow(() -> new InvalidTokenException(CustomeException.INVALID_TOKEN_EXCEPTION));

        Store store = storeRepository.findByUserId(storeOwnerId).orElseThrow(() -> new NoSuchStoreExistsException(CustomeException.NO_SUCH_STORE_EXISTS_EXCEPTION));

        if (Objects.equals(storeOwnerId, store.getUser().getId())) {
            storeRepository.deleteById(storeId);
        }

    }

    @Transactional
    public void updateStore(String token, UpdateStoreRequest updateStoreRequest) {
        Long storeOwnerId = jWTManager.validToken(token).orElseThrow(() -> new InvalidTokenException(CustomeException.INVALID_TOKEN_EXCEPTION));

        Store store = storeRepository.findByUserId(storeOwnerId).orElseThrow(() -> new NoSuchStoreExistsException(CustomeException.NO_SUCH_STORE_EXISTS_EXCEPTION));

        if (Objects.equals(storeOwnerId, store.getUser().getId())) {

            store.setId(updateStoreRequest.getId());
            store.setStoreName(updateStoreRequest.getStoreName());
            store.setTaxNumber(updateStoreRequest.getTaxNumber());
            store.setAddress(updateStoreRequest.getAddress());
            store.setUpdateAt(LocalDateTime.now());
            storeRepository.save(store);
        }

    }

    public ProductResponse findProductById(Long id) {
        return productService.findById(id);
    }

    public List<ProductResponse> findAllProduct(String token) {
        return getMyOwnStoreProduct(token);
    }

    @Transactional
    public void updateProduct(String token, ProductUpdateRequest productUpdateRequest) {
        Long storeOwnerId = jWTManager.validToken(token).orElseThrow(() -> new InvalidTokenException(CustomeException.INVALID_TOKEN_EXCEPTION));

        ProductResponse productResponse = productService.findById(productUpdateRequest.getId());

        if (!Objects.equals(storeOwnerId, productResponse.getStoreId())) {
            throw new ProductDoesNotBelongToYourStore(CustomeException.PRODUCT_DOES_NOT_BELONG_TO_YOUR_STORE_EXCEPTION);
        }

        productService.update(productUpdateRequest);
    }

    @Transactional
    public void deleteProduct(String token, Long id) {
        Long storeOwnerId = jWTManager.validToken(token).orElseThrow(() -> new InvalidTokenException(CustomeException.INVALID_TOKEN_EXCEPTION));

        ProductResponse productResponse = productService.findById(id);

        if (!Objects.equals(storeOwnerId, productResponse.getStoreId())) {
            throw new ProductDoesNotBelongToYourStore(CustomeException.PRODUCT_DOES_NOT_BELONG_TO_YOUR_STORE_EXCEPTION);
        }
        productService.delete(id);
    }

    @Transactional
    public void updateOrderStatus(String token, UpdateOrderStatusRequest updateOrderStatusRequest) {
        orderService.updateOrderStatus(token, updateOrderStatusRequest);
    }

    private List<ProductResponse> getMyOwnStoreProduct(String token) {
        Long storeOwnerId = jWTManager.validToken(token).orElseThrow(() -> new InvalidTokenException(CustomeException.INVALID_TOKEN_EXCEPTION));

        Store store = storeRepository.findById(storeOwnerId).orElseThrow(() -> new NoSuchStoreExistsException(CustomeException.NO_SUCH_STORE_EXISTS_EXCEPTION));

        return productService.findByStoreId(store.getId());
    }
}
