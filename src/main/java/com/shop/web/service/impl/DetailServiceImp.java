package com.shop.web.service.impl;

import org.springframework.stereotype.Service;

import com.shop.web.dto.DetailsDTO;
import com.shop.web.models.Details;
import com.shop.web.models.User;
import com.shop.web.repository.DetailsRepository;
import com.shop.web.repository.UserRepository;
import com.shop.web.service.DetailService;

import jakarta.validation.Valid;

import static com.shop.web.mapper.DetailMapper.maptoDetail;
import static com.shop.web.mapper.DetailMapper.maptoDetailDTO;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<DetailsDTO> findallTasks() {
        List<Details> tasks = detailRepo.findAllByOrderById();
        return tasks.stream().map((task) -> maptoDetailDTO(task)).collect(Collectors.toList());
    }

    @Override
    public List<DetailsDTO> findDetailByUser(Long userId) {
        List<Details> details = detailRepo.findByUserId(userId);
        if (details.isEmpty()) {
            return null;
        }
        return details.stream().map((task) -> maptoDetailDTO(task)).collect(Collectors.toList());
        
    }

    @Override
    public void updateDetail(@Valid DetailsDTO detailDto) {
        Details details = maptoDetail(detailDto);

        detailRepo.save(details);
    }

    @Override
    public DetailsDTO findById(Long detailId) {
        Details details = detailRepo.findById(detailId).get();
        return maptoDetailDTO(details);
    }
    
}
