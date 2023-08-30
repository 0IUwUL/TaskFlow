package com.shop.web.mapper;

import com.shop.web.dto.TaskDTO;
import com.shop.web.models.Task;

public class TaskMapper {
    public static Task maptoTask(TaskDTO taskDTO){
        return Task.builder()
                        .id(taskDTO.getId())
                        .title(taskDTO.getTitle())
                        .type(taskDTO.getType())
                        .description(taskDTO.getDescription())
                        .createdOn(taskDTO.getCreatedOn())
                        .updatedOn(taskDTO.getUpdatedOn())
                        .user(taskDTO.getUser())
                        .build();
    }

    public static TaskDTO maptoTaskDTO(Task task){
        return TaskDTO.builder()
                        .id(task.getId())
                        .title(task.getTitle())
                        .type(task.getType())
                        .description(task.getDescription())
                        .createdOn(task.getCreatedOn())
                        .updatedOn(task.getUpdatedOn())
                        .user(task.getUser())
                        .build();
    }
}
