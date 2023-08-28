package com.shop.web.dto;

import groovy.transform.builder.Builder;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
 
public class RegistrationDTO {
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String address;
    @NotEmpty
    private String password;
}
