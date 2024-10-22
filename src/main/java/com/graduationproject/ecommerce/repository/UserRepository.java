package com.graduationproject.ecommerce.repository;

import com.graduationproject.ecommerce.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserNameOrEmail(String username, String email);
}
