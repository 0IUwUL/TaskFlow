package com.shop.web.service.impl;

import org.springframework.stereotype.Service;

import com.shop.web.dto.TaskDTO;
import com.shop.web.models.Task;
import com.shop.web.models.User;
import com.shop.web.repository.TaskRepository;
import com.shop.web.repository.UserRepository;
import com.shop.web.service.TaskService;

import jakarta.validation.Valid;

import static com.shop.web.mapper.TaskMapper.maptoTask;
import static com.shop.web.mapper.TaskMapper.maptoTaskDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImp implements TaskService {

    private TaskRepository taskRepo;
    private UserRepository userRepo;
    public TaskServiceImp(TaskRepository taskRepo, UserRepository userRepo) {
        this.taskRepo = taskRepo;
        this.userRepo = userRepo;
    }

    @Override
    public void createTask(Long userId, TaskDTO taskDTO) {
        User user = userRepo.findById(userId).get();
        Task task = maptoTask(taskDTO);
        task.setUser(user);
        taskRepo.save(task);
    }

    @Override
    public List<TaskDTO> findallTasks() {
        List<Task> tasks = taskRepo.findAllByOrderById();
        return tasks.stream().map((task) -> maptoTaskDTO(task)).collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> findTaskByUser(Long userId) {
        List<Task> tasks = taskRepo.findByUserId(userId);
        if (tasks.isEmpty()) {
            return null;
        }
        return tasks.stream().map((task) -> maptoTaskDTO(task)).collect(Collectors.toList());
        
    }

    @Override
    public void updateTask(@Valid TaskDTO taskDto) {
        Task tasks = maptoTask(taskDto);

        taskRepo.save(tasks);
    }

    @Override
    public TaskDTO findById(Long taskId) {
        Task tasks = taskRepo.findById(taskId).get();
        return maptoTaskDTO(tasks);
    }

    @Override
    public String deleteTask(long taskId) {
        String title = taskRepo.findById(taskId).get().getTitle();
        taskRepo.deleteById(taskId);
        return title;
    }
    
}
