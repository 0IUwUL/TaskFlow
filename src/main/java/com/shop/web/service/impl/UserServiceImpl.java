package com.shop.web.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.shop.web.dto.UserDTO;
import com.shop.web.models.User;
import com.shop.web.repository.UserRepository;
import com.shop.web.service.UserService;
import static com.shop.web.mapper.UserMapper.mapToUser;
import static com.shop.web.mapper.UserMapper.mapToUserDto;
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
        List<User> users = userRepo.findAllByOrderById();
        return users.stream().map((user) -> mapToUserDto(user)).collect(Collectors.toList());
    }

    @Override
    public User save(UserDTO userDTO) {
        User user = mapToUser(userDTO);
        return userRepo.save(user);
    }

    @Override
    public UserDTO findUserById(long userId) {
        User user = userRepo.findById(userId).get();

        return mapToUserDto(user);
    }

    @Override
    public void updateUser(UserDTO userDto) {
        User user = mapToUser(userDto);

        userRepo.save(user);
    }

    @Override
    public String delete(long userId) {
        User user = userRepo.findById(userId).get();
        userRepo.deleteById(userId);
        return user.getUsername();
    }

    @Override
    public List<UserDTO> searchUsers(String query) {
        List<User> users = userRepo.searchUsers(query);
        return users.stream().map(user -> mapToUserDto(user)).collect(Collectors.toList());
    }
    
}