package com.shop.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.web.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
    Role findByTitle(String title);
}
