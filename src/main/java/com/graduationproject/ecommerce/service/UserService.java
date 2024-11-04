package com.graduationproject.ecommerce.service;

import com.graduationproject.ecommerce.dto.request.OrderRequest;
import com.graduationproject.ecommerce.dto.request.login.UserLoginRequest;
import com.graduationproject.ecommerce.dto.request.update.UserUpdateRequest;
import com.graduationproject.ecommerce.dto.response.UserResponse;
import com.graduationproject.ecommerce.dto.response.UserSaveResponse;
import com.graduationproject.ecommerce.entity.User;
import com.graduationproject.ecommerce.exception.customexception.CustomeException;
import com.graduationproject.ecommerce.exception.customexception.NoSuchUserExistsException;
import com.graduationproject.ecommerce.exception.customexception.UserNameOrPasswordIsWrongException;
import com.graduationproject.ecommerce.mapper.UserMapper;
import org.springframework.stereotype.Service;

import com.graduationproject.ecommerce.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    
    protected UserSaveResponse save(User user) {
        user.setAccountCreationDate(LocalDateTime.now());
        return UserMapper.INSTANCE.userToUserSaveResponse(userRepository.save(user));
    }
    
    protected UserResponse findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new NoSuchUserExistsException(CustomeException.NO_SUCH_USER_EXISTS_EXCEPTION)
        );
        
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .userName(user.getUserName())
                .password(user.getPassword())
                .role(user.getAuth().getRole().toString())
                .build();
    }
    
    protected boolean isUserExist(String userName, String email) {
        return userRepository.existsByUserNameOrEmail(userName, email);
    }
    
    protected UserResponse checkUserAndUserPassword(UserLoginRequest loginRequest) {
        return UserMapper.INSTANCE.userToUserResponse(userRepository.findOptionalByUserNameOrPassword(loginRequest.getUserName(), loginRequest.getPassword()).orElseThrow(() -> new UserNameOrPasswordIsWrongException(CustomeException.USER_NAME_OR_PASSWORD_IS_WRONG_EXCEPTION)));
    }
    
    public void delete(Long id) {
        UserResponse user = findById(id);
        userRepository.deleteById(user.getId());
    }
    
    public void update(UserUpdateRequest userUpdateRequest) {
        
        User user = userRepository.findById(userUpdateRequest.getId()).orElseThrow(() -> new NoSuchUserExistsException(CustomeException.NO_SUCH_USER_EXISTS_EXCEPTION));
        
        user.setId(userUpdateRequest.getId());
        user.setName(userUpdateRequest.getName());
        user.setLastName(userUpdateRequest.getLastName());
        user.setEmail(userUpdateRequest.getEmail());
        user.setUserName(userUpdateRequest.getUserName());
        user.setPassword(userUpdateRequest.getPassword());
        user.setAccountUpdateDate(LocalDateTime.now());
        
        userRepository.save(user);
    }
}
