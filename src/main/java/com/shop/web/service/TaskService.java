package com.shop.web.service;

import java.util.List;

import com.shop.web.dto.TaskDTO;

import jakarta.validation.Valid;


public interface TaskService {
    void createDetail(Long userId, TaskDTO detailDTO);
    List<TaskDTO> findallTasks();
    List<TaskDTO> findDetailByUser(Long userId);
    void updateDetail(@Valid TaskDTO detailDto);
    TaskDTO findById(Long detailId);
    String deleteTask(long detailId);
}
