package com.shop.web.service.impl;

import org.springframework.stereotype.Service;

import com.shop.web.dto.TaskDTO;
import com.shop.web.models.Task;
import com.shop.web.models.User;
import com.shop.web.repository.TaskRepository;
import com.shop.web.repository.UserRepository;
import com.shop.web.service.TaskService;

import jakarta.validation.Valid;

import static com.shop.web.mapper.TaskMapper.maptoDetail;
import static com.shop.web.mapper.TaskMapper.maptoDetailDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImp implements TaskService {

    private TaskRepository detailRepo;
    private UserRepository userRepo;
    public TaskServiceImp(TaskRepository detailRepo, UserRepository userRepo) {
        this.detailRepo = detailRepo;
        this.userRepo = userRepo;
    }

    @Override
    public void createDetail(Long userId, TaskDTO detailDTO) {
        User user = userRepo.findById(userId).get();
        Task detail = maptoDetail(detailDTO);
        detail.setUser(user);
        detailRepo.save(detail);
    }

    @Override
    public List<TaskDTO> findallTasks() {
        List<Task> tasks = detailRepo.findAllByOrderById();
        return tasks.stream().map((task) -> maptoDetailDTO(task)).collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> findDetailByUser(Long userId) {
        List<Task> details = detailRepo.findByUserId(userId);
        if (details.isEmpty()) {
            return null;
        }
        return details.stream().map((task) -> maptoDetailDTO(task)).collect(Collectors.toList());
        
    }

    @Override
    public void updateDetail(@Valid TaskDTO detailDto) {
        Task details = maptoDetail(detailDto);

        detailRepo.save(details);
    }

    @Override
    public TaskDTO findById(Long detailId) {
        Task details = detailRepo.findById(detailId).get();
        return maptoDetailDTO(details);
    }

    @Override
    public String deleteTask(long detailId) {
        String title = detailRepo.findById(detailId).get().getTitle();
        detailRepo.deleteById(detailId);
        return title;
    }
    
}
