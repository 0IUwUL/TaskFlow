package com.shop.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.web.dto.TaskDTO;
import com.shop.web.models.Task;

import jakarta.validation.Valid;

public interface TaskRepository extends JpaRepository<Task, Long> {

    void save(@Valid TaskDTO taskDTO);
    List<Task> findAllByOrderById();
    List<Task> findByUserId(Long userId);
}
