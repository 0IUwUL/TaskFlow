package com.shop.web.service;

import com.shop.web.dto.RegistrationDTO;

public interface UserEntityService {
    void saveUser(RegistrationDTO registerDto);
}
