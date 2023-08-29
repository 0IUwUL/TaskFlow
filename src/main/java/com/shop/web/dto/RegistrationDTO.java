package com.shop.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
 
public class RegistrationDTO {
    private Long id;
    @NotBlank(message = "Username field should not be empty.")
    private String username;
    @Email(message = "Email field should be in email format.")
    @NotBlank(message = "Email field should not be empty.")
    private String email;
    @NotBlank(message = "Password field should not be empty.")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;
}
