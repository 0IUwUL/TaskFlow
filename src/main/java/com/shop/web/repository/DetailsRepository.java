package com.shop.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.web.dto.DetailsDTO;
import com.shop.web.models.Details;

import jakarta.validation.Valid;

public interface DetailsRepository extends JpaRepository<Details, Long> {

    void save(@Valid DetailsDTO detailDTO);
    
}