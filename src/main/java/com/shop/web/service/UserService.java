package com.shop.web.service;

import java.util.List;

import com.shop.web.dto.UserDTO;

public interface UserService {
    List<UserDTO> findallUsers();
}
