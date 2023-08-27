package com.shop.web.service.impl;

import org.springframework.stereotype.Service;

import com.shop.web.dto.DetailsDTO;
import com.shop.web.models.Details;
import com.shop.web.models.User;
import com.shop.web.repository.DetailsRepository;
import com.shop.web.repository.UserRepository;
import com.shop.web.service.DetailService;

import jakarta.validation.Valid;

@Service
public class DetailServiceImp implements DetailService {

    private DetailsRepository detailRepo;
    private UserRepository userRepo;
    public DetailServiceImp(DetailsRepository detailRepo, UserRepository userRepo) {
        this.detailRepo = detailRepo;
        this.userRepo = userRepo;
    }

    @Override
    public void createDetail(Long userId, DetailsDTO detailDTO) {
        User user = userRepo.findById(userId).get();
        Details detail = maptoDetail(detailDTO);
        detail.setUser(user);
        detailRepo.save(detail);
    }

    private Details maptoDetail(DetailsDTO detailsDTO){
        return Details.builder()
                        .id(detailsDTO.getId())
                        .title(detailsDTO.getTitle())
                        .type(detailsDTO.getType())
                        .description(detailsDTO.getDescription())
                        .build();
    }
    
}
