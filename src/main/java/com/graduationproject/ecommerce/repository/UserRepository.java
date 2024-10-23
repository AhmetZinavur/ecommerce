package com.graduationproject.ecommerce.repository;

import com.graduationproject.ecommerce.entity.User;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserNameOrEmail(String username, String email);
    Optional<User> findOptionalByUserNameOrPassword(String userName, String password);
}
