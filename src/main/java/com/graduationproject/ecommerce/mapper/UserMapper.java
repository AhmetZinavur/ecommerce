package com.graduationproject.ecommerce.mapper;

import com.graduationproject.ecommerce.dto.request.register.AdminRegisterRequest;
import com.graduationproject.ecommerce.dto.request.register.CustomerRegisterRequest;
import com.graduationproject.ecommerce.dto.request.register.StoreOwnerRegisterRequest;
import com.graduationproject.ecommerce.dto.response.UserResponse;
import com.graduationproject.ecommerce.dto.response.UserSaveResponse;
import com.graduationproject.ecommerce.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    
    User adminRegisterRequestToUser(AdminRegisterRequest adminRegisterRequest);
    User customerRegisterRequestToUser(CustomerRegisterRequest customerRegisterRequest);
    User storeOwnerRegisterRequestToUser(StoreOwnerRegisterRequest storeOwnerRegisterRequest);
    
    UserSaveResponse userToUserSaveResponse(User user);
    User userSaveResponseToUser(UserSaveResponse userSaveResponse);
    
    UserResponse userToUserResponse(User user);
}
