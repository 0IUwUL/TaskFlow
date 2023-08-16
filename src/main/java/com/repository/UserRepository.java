package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.models.User;


/**
 * UserRepository
 */
public interface UserRepository extends JpaRepository<User, Long>{
    //custom queries
    Optional<User> findById(long id);
}