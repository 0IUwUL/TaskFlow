package com.shop.web.service;

import com.shop.web.dto.RegistrationDTO;
import com.shop.web.models.Users;

public interface UserService {
    void saveUser(RegistrationDTO registerDto);

    Users findByEmail(String email);

    Users findByUsername(String username);
}
