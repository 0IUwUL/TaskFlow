package com.shop.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.web.models.Type;

public interface TypeRepository extends JpaRepository<Type, Long> {
}
