package com.shop.web.mapper;

import com.shop.web.dto.TaskDTO;
import com.shop.web.models.Task;

public class TaskMapper {
    public static Task maptoDetail(TaskDTO detailsDTO){
        return Task.builder()
                        .id(detailsDTO.getId())
                        .title(detailsDTO.getTitle())
                        .type(detailsDTO.getType())
                        .description(detailsDTO.getDescription())
                        .createdOn(detailsDTO.getCreatedOn())
                        .updatedOn(detailsDTO.getUpdatedOn())
                        .user(detailsDTO.getUser())
                        .build();
    }

    public static TaskDTO maptoDetailDTO(Task details){
        return TaskDTO.builder()
                        .id(details.getId())
                        .title(details.getTitle())
                        .type(details.getType())
                        .description(details.getDescription())
                        .createdOn(details.getCreatedOn())
                        .updatedOn(details.getUpdatedOn())
                        .user(details.getUser())
                        .build();
    }
}
