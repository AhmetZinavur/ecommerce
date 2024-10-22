package com.graduationproject.ecommerce.repository;

import com.graduationproject.ecommerce.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<Auth, Long> {

}
