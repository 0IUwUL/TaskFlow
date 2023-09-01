package com.shop.web.service.impl;

import java.util.Arrays;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shop.web.dto.RegistrationDTO;
import com.shop.web.models.Role;
import com.shop.web.models.Users;
import com.shop.web.repository.RoleRepository;
import com.shop.web.repository.UserRepository;
import com.shop.web.service.UserService;

@Service
public class UserServiceImp implements UserService {
    private UserRepository userRepo;
    private RoleRepository roleRepository;
    private PasswordEncoder encodePassword;

    public UserServiceImp(UserRepository userRepo, RoleRepository roleRepository, PasswordEncoder encodePassword) {
        this.userRepo = userRepo;
        this.roleRepository = roleRepository;
        this.encodePassword = encodePassword;
    }

    @Override
    public void saveUser(RegistrationDTO registerDto) {
        Users user = new Users();
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(encodePassword.encode(registerDto.getPassword()));
        Role role = roleRepository.findByTitle("USER");
        user.setRoles(Arrays.asList(role));
        userRepo.save(user);
    }

    @Override
    public Users findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public Users findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public void updateRole(Users user){
        Role role = roleRepository.findByTitle("ADMIN");
        user.setRoles(Arrays.asList(role));
        userRepo.save(user);
    }
}
