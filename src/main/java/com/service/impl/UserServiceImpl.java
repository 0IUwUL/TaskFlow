package com.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.dto.UserDTO;
import com.models.User;
import com.repository.UserRepository;
import com.service.UserService;

/**
 * UserServiceImpl
 */
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
                            .createdOn(user.getCreatedOn())
                            .updatedOn(user.getUpdatedOn())
                            .build();
        return userDto;
    }
    
}