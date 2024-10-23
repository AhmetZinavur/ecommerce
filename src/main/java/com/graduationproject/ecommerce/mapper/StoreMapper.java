package com.graduationproject.ecommerce.mapper;

import com.graduationproject.ecommerce.dto.request.build.BuildStoreRequest;
import com.graduationproject.ecommerce.entity.Store;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StoreMapper {
    StoreMapper INSTANCE = Mappers.getMapper(StoreMapper.class);
    
    Store buildStoreRequestToStore (BuildStoreRequest buildStoreRequest);
}
