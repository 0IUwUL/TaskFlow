package com.shop.web.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.shop.web.dto.UserDTO;
import com.shop.web.models.User;
import com.shop.web.repository.UserRepository;
import com.shop.web.service.UserService;

/**
 * UserServiceImpl
 */
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepo;

    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<UserDTO> findallUsers() {
        List<User> users = userRepo.findAll();
        return users.stream().map((user) -> mapToUserDto(user)).collect(Collectors.toList());
    }

    private UserDTO mapToUserDto(User user){
        UserDTO userDto = UserDTO.builder()
                            .id(user.getId())
                            .username(user.getUsername())
                            .email(user.getEmail())
                            .createdOn(user.getCreatedOn())
                            .updatedOn(user.getUpdatedOn())
                            .build();
        return userDto;
    }
    
}