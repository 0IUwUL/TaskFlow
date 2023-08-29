package com.shop.web.service;

import com.shop.web.dto.RegistrationDTO;
import com.shop.web.models.UserEntity;

public interface UserEntityService {
    void saveUser(RegistrationDTO registerDto);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);
}
