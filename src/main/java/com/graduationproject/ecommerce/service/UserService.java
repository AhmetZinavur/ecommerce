package com.graduationproject.ecommerce.service;

import com.graduationproject.ecommerce.dto.response.UserResponse;
import com.graduationproject.ecommerce.dto.response.UserSaveResponse;
import com.graduationproject.ecommerce.entity.User;
import com.graduationproject.ecommerce.exception.customexception.CustomeException;
import com.graduationproject.ecommerce.exception.customexception.NoSuchUserExistsException;
import com.graduationproject.ecommerce.mapper.UserMapper;
import org.springframework.stereotype.Service;

import com.graduationproject.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    protected UserSaveResponse save(User user) {
        return UserMapper.INSTANCE.userToUserSaveResponse(userRepository.save(user));
    }
    
    public UserResponse findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new NoSuchUserExistsException(CustomeException.NO_SUCH_USER_EXISTS_EXCEPTION)
        );
        
        return UserMapper.INSTANCE.userToUserResponse(user);
    }
    
    protected boolean isUserExist(String userName, String email) {
        return userRepository.existsByUserNameOrEmail(userName, email);
    } 
}
