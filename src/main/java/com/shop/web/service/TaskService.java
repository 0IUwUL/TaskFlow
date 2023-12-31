package com.shop.web.service;

import java.util.List;

import com.shop.web.dto.TaskDTO;

import jakarta.validation.Valid;


public interface TaskService {
    void createTask(Long userId, TaskDTO taskDTO);
    List<TaskDTO> findallTasks();
    void updateTask(@Valid TaskDTO taskDto);
    TaskDTO findById(Long taskId);
    String deleteTask(long taskId);
    List<TaskDTO> searchTask(String query);
}
