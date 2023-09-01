package com.shop.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.web.models.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);
    Users findByUsername(String username);
    Users findFirstByUsername(String username);
}
