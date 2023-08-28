package com.shop.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.web.models.UserEntity;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByAddress(String address);
    UserEntity findByName(String name);
}
