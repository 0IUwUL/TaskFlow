package com.shop.web.service.impl;

import java.util.Arrays;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shop.web.dto.RegistrationDTO;
import com.shop.web.models.Role;
import com.shop.web.models.UserEntity;
import com.shop.web.repository.RoleRepository;
import com.shop.web.repository.UserEntityRepository;
import com.shop.web.service.UserEntityService;

@Service
public class UserEntitySericeImp implements UserEntityService {
    private UserEntityRepository userEntityRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder encodePassword;

    public UserEntitySericeImp(UserEntityRepository userEntityRepository, RoleRepository roleRepository, PasswordEncoder encodePassword) {
        this.userEntityRepository = userEntityRepository;
        this.roleRepository = roleRepository;
        this.encodePassword = encodePassword;
    }

    @Override
    public void saveUser(RegistrationDTO registerDto) {
        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(encodePassword.encode(registerDto.getPassword()));
        Role role = roleRepository.findByTitle("USER");
        user.setRoles(Arrays.asList(role));
        userEntityRepository.save(user);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userEntityRepository.findByEmail(email);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userEntityRepository.findByUsername(username);
    }
    
}
