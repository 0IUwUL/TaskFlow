package com.shop.web.dto;

import java.time.LocalDateTime;
import java.util.List;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class UserDTO {
    private Long id;
    @NotEmpty(message="Username field should not be empty.")
    private String username;
    @NotEmpty(message="Email field should not be empty.")
    @Email(message="Email field should consist of email.")
    private String email;
    @NotEmpty(message="Password field should not be empty.")
    @Size(min=3, max=8, message="Password field should be 3-8 characters.")
    private String password;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    private List<DetailsDTO> todo;
}
