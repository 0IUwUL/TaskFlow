package com.shop.web.service;

import java.util.List;

import com.shop.web.dto.DetailsDTO;


public interface DetailService {
    void createDetail(Long userId, DetailsDTO detailDTO);
    List<DetailsDTO> findallTasks();
    DetailsDTO findDetailByUser(Long userId);
}
