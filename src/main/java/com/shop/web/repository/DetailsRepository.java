package com.shop.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.web.models.Details;

public interface DetailsRepository extends JpaRepository<Details, Long> {
    
}
