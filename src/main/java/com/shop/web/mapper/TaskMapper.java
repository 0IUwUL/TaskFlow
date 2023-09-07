package com.shop.web.mapper;

import com.shop.web.dto.TaskDTO;
import com.shop.web.models.Task;

public class TaskMapper {
    public static Task maptoTask(TaskDTO taskDTO){
        return Task.builder()
                        .id(taskDTO.getId())
                        .title(taskDTO.getTitle())
                        .description(taskDTO.getDescription())
                        .createdOn(taskDTO.getCreatedOn())
                        .updatedOn(taskDTO.getUpdatedOn())
                        .user(taskDTO.getUser())
                        .type(taskDTO.getType())
                        .build();
    }

    public static TaskDTO maptoTaskDTO(Task task){
        return TaskDTO.builder()
                        .id(task.getId())
                        .title(task.getTitle())
                        .description(task.getDescription())
                        .createdOn(task.getCreatedOn())
                        .updatedOn(task.getUpdatedOn())
                        .user(task.getUser())
                        .type(task.getType())
                        .build();
    }
}
