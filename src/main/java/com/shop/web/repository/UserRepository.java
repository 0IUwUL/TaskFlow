package com.shop.web.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.web.models.User;


/**
 * UserRepository
 */
public interface UserRepository extends JpaRepository<User, Long>{
    //custom queries
    Optional<User> findById(long id);
}