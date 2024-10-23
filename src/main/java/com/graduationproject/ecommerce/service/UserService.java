package com.graduationproject.ecommerce.service;

import com.graduationproject.ecommerce.dto.request.update.UserUpdateRequest;
import com.graduationproject.ecommerce.dto.response.UserResponse;
import com.graduationproject.ecommerce.dto.response.UserSaveResponse;
import com.graduationproject.ecommerce.entity.User;
import com.graduationproject.ecommerce.exception.customexception.CustomeException;
import com.graduationproject.ecommerce.exception.customexception.NoSuchUserExistsException;
import com.graduationproject.ecommerce.mapper.UserMapper;
import org.springframework.stereotype.Service;

import com.graduationproject.ecommerce.repository.UserRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    protected UserSaveResponse save(User user) {
        user.setAccountCreationDate(LocalDateTime.now());
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