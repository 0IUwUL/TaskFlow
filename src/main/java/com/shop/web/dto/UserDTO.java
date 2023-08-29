package com.shop.web.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class UserDTO {
    private Long id;
    @NotEmpty(message="Name field should not be empty.")
    private String name;
    @NotEmpty(message="Address field should not be empty.")
    private String address;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    private List<DetailsDTO> todo;
}
