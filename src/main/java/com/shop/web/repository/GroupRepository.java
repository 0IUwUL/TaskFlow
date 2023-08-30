package com.shop.web.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shop.web.dto.GroupDTO;
import com.shop.web.models.Group;


/**
 * UserRepository
 */
public interface GroupRepository extends JpaRepository<Group, Long>{
    //custom queries
    Optional<Group> findById(long id);
    Group save(GroupDTO userDto);
    List<Group> findAllByOrderById();
    @Query("SELECT g from Group g WHERE g.name LIKE CONCAT('%', :query, '%')")
    List<Group> searchUsers(String query);
}