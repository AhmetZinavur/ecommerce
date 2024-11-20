package com.example.simple_user_management_system.service;

import com.example.simple_user_management_system.entity.User;
import com.example.simple_user_management_system.exception.UserAlreadyExistsException;
import com.example.simple_user_management_system.exception.UserNotFoundException;
import com.example.simple_user_management_system.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User create(User user) {
        if(userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException(user.getEmail());
        }
        return userRepository.save(User.builder()
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .build());
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(id)
        );
    }

    public String delete(Long id) {
        User user = findById(id);
        userRepository.deleteById(user.getId());
        return "User deleted successfully";
    }

    public User update(Long id, User user) {
        User currentUser = findById(id);
        currentUser.setName(user.getName());
        currentUser.setEmail(user.getEmail());
        currentUser.setRole(user.getRole());
        return userRepository.save(currentUser);
    }
}
