package com.shop.web.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shop.web.dto.UserDTO;
import com.shop.web.models.User;


/**
 * UserRepository
 */
public interface UserRepository extends JpaRepository<User, Long>{
    //custom queries
    Optional<User> findById(long id);
    User save(UserDTO userDto);
    List<User> findAllByOrderById();
    @Query("SELECT u from User u WHERE u.username LIKE CONCAT('%', :query, '%')")
    List<User> searchUsers(String query);
}