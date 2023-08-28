package com.shop.web.service.impl;

import org.springframework.stereotype.Service;

import com.shop.web.dto.DetailsDTO;
import com.shop.web.models.Details;
import com.shop.web.models.User;
import com.shop.web.repository.DetailsRepository;
import com.shop.web.repository.UserRepository;
import com.shop.web.service.DetailService;
import static com.shop.web.mapper.DetailMapper.maptoDetail;
import static com.shop.web.mapper.DetailMapper.maptoDetailDTO;

import java.util.List;
import java.util.Optional;
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
    public DetailsDTO findDetailByUser(Long userId) {
        Optional<Details> details = detailRepo.findByUserId(userId);
        Details tasks = new Details();
        if (details.isPresent()) {
            tasks = details.get();
            return maptoDetailDTO(tasks);
        }else{
            return null;
        }
        
    }
    
}
