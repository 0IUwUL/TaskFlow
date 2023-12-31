package com.shop.web.dto;

import java.time.LocalDateTime;
import com.shop.web.models.Type;
import com.shop.web.models.Users;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class TaskDTO {
    private Long id;
    @NotEmpty(message="Title Field shall not be empty.")
    private String title;
    @NotEmpty(message="Description Field shall not be empty.")
    @Column(length = 256)
    private String description;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private Users user;
    private Type type;
}