package com.example.simple_user_management_system.controller;

import com.example.simple_user_management_system.entity.User;
import com.example.simple_user_management_system.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("create-user")
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping("users")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("user/{id}")
    public User findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PutMapping("user/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @DeleteMapping("user/{id}")
    public String delete(@PathVariable Long id) {
        return userService.delete(id);
    }
}
