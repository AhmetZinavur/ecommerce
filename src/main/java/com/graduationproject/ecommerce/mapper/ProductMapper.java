package com.graduationproject.ecommerce.mapper;

import com.graduationproject.ecommerce.dto.request.create.AddNewProductRequest;
import com.graduationproject.ecommerce.dto.request.update.ProductUpdateRequest;
import com.graduationproject.ecommerce.dto.response.ProductResponse;
import com.graduationproject.ecommerce.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    
    Product addNewProductRequestToProduct(AddNewProductRequest addNewProductRequest);
    
    ProductResponse productToProductResponse(Product product);
    
    ProductUpdateRequest productToProductUpdateRequest(ProductUpdateRequest productUpdateRequest);
}
