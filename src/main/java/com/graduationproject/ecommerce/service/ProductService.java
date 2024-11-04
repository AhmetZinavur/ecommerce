package com.graduationproject.ecommerce.service;

import com.graduationproject.ecommerce.dto.request.build.AddNewProductRequest;
import com.graduationproject.ecommerce.dto.request.update.ProductUpdateRequest;
import com.graduationproject.ecommerce.dto.response.ProductResponse;
import com.graduationproject.ecommerce.entity.Product;
import com.graduationproject.ecommerce.entity.Store;
import com.graduationproject.ecommerce.exception.customexception.CustomeException;
import com.graduationproject.ecommerce.exception.customexception.NoProductFoundToDisplay;
import com.graduationproject.ecommerce.exception.customexception.NoSuchProductExistException;
import com.graduationproject.ecommerce.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import com.graduationproject.ecommerce.repository.ProductRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    protected ProductResponse save(Store store, AddNewProductRequest addNewProductRequest) {

        Product product = ProductMapper.INSTANCE.addNewProductRequestToProduct(addNewProductRequest);

        product.setStore(store);
        product.setProductCreationDate(LocalDateTime.now());

        return ProductMapper.INSTANCE.productToProductResponse(productRepository.save(product));
    }

    protected ProductResponse findById(Long id) {
        Product p = productRepository.findById(id).orElseThrow(() -> new NoSuchProductExistException(CustomeException.NO_SUCH_PRODUCT_EXIST_EXCEPTION));

        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(p.getId());
        productResponse.setProductName(p.getProductName());
        productResponse.setProductPrice(p.getProductPrice());
        productResponse.setProductQuantity(p.getStockQuantity());
        productResponse.setStoreId(p.getStore().getId());

        return productResponse;
    }

    protected List<ProductResponse> findAll() {
        List<ProductResponse> productResponses = new ArrayList<>();

        List<Product> products = productRepository.findAll();

        for (Product product : products) {
            productResponses.add(ProductMapper.INSTANCE.productToProductResponse(product));
        }

        return productResponses;
    }

    protected List<ProductResponse> findByStoreId(Long id) {
        List<ProductResponse> productResponses = new ArrayList<>();

        List<Product> products = productRepository.findByStoreId(id).orElseThrow(() -> new NoProductFoundToDisplay(CustomeException.NO_PRODUCT_FOUND_TO_DISPLAY_EXCEPTION));

        for (Product product : products) {
            
            ProductResponse pr = new ProductResponse();
            pr.setId(product.getId());
            pr.setProductName(product.getProductName());
            pr.setProductPrice(product.getProductPrice());
            pr.setProductQuantity(product.getStockQuantity());
            pr.setStoreId(product.getStore().getId());
            
            productResponses.add(
                   pr
            );
        }

        return productResponses;
    }

    protected void update(ProductUpdateRequest productUpdateRequest) {
        Product product = Product.builder()
                .id(productUpdateRequest.getId())
                .productName(productUpdateRequest.getProductName())
                .productPrice(productUpdateRequest.getProductPrice())
                .stockQuantity(productUpdateRequest.getStockQuantity())
                .build();
        product.setProductUpdateDate(LocalDateTime.now());
        productRepository.save(product);
    }

    protected void delete(Long id) {
        ProductResponse p = findById(id);
        productRepository.deleteById(p.getId());
    }
}
