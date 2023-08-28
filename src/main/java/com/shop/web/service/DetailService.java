package com.shop.web.service;

import java.util.List;

import com.shop.web.dto.DetailsDTO;

import jakarta.validation.Valid;


public interface DetailService {
    void createDetail(Long userId, DetailsDTO detailDTO);
    List<DetailsDTO> findallTasks();
    List<DetailsDTO> findDetailByUser(Long userId);
    void updateDetail(@Valid DetailsDTO detailDto);
    DetailsDTO findById(Long detailId);
}
