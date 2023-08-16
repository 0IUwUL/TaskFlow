package com.service;

import java.util.List;

import com.dto.UserDTO;

public interface UserService {
    List<UserDTO> findallUsers();
}
