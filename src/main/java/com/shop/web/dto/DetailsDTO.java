package com.shop.web.dto;

import java.time.LocalDateTime;

import com.shop.web.Status;
import com.shop.web.models.User;

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

public class DetailsDTO {
    private Long id;
    @NotEmpty(message="Title Field shall not be empty.")
    private String title;
    private Status type;
    @NotEmpty(message="Description Field shall not be empty.")
    @Column(length = 256)
    private String description;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private User user;
}