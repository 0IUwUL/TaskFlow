package com.shop.web.service;

import java.util.List;

import com.shop.web.dto.UserDTO;
import com.shop.web.models.User;

public interface UserService {
    List<UserDTO> findallUsers();
    User save(User user);
    UserDTO findUserById(long userId);
    void updateUser(UserDTO userDto);
}
