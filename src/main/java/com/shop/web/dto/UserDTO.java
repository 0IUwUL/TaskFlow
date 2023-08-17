package com.shop.web.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class UserDTO {
    private long id;
    private String username;
    private String email;
    private String password;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
