package com.shop.web.service.impl;

import java.util.Arrays;

import com.shop.web.dto.RegistrationDTO;
import com.shop.web.models.Role;
import com.shop.web.models.UserEntity;
import com.shop.web.repository.RoleRepository;
import com.shop.web.repository.UserEntityRepository;
import com.shop.web.service.UserEntityService;

public class UserEntitySericeImp implements UserEntityService {
    private UserEntityRepository userRepository;
    private RoleRepository roleRepository;
    
    public UserEntitySericeImp(UserEntityRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void saveUser(RegistrationDTO registerDto) {
        UserEntity user = new UserEntity();
        user.setName(registerDto.getName());
        user.setAddress(registerDto.getAddress());
        user.setPassword(registerDto.getPassword());
        Role role = roleRepository.findByTitle("USER");
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }
    
}
