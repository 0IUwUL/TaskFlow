package com.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class UserDTO {
    private long id;
    private String username;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
