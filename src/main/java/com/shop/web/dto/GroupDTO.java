package com.shop.web.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.shop.web.models.Users;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class GroupDTO {
    private Long id;
    @NotEmpty(message="Name field should not be empty.")
    private String name;
    @NotEmpty(message="Description field should not be empty.")
    private String description;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private Users admin;
    private List<TaskDTO> todo;
    private List<Users> users;
}
