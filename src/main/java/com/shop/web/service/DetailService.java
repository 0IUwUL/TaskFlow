package com.shop.web.service;

import com.shop.web.dto.DetailsDTO;

public interface DetailService {
    void createDetail(Long userId, DetailsDTO detailDTO);
}
