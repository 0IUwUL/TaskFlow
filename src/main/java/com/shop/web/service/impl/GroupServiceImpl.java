package com.shop.web.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.shop.web.dto.GroupDTO;
import com.shop.web.models.Group;
import com.shop.web.models.UserEntity;
import com.shop.web.repository.UserEntityRepository;
import com.shop.web.repository.GroupRepository;
import com.shop.web.security.SecurityUtil;
import com.shop.web.service.GroupService;
import static com.shop.web.mapper.GroupMapper.mapToUser;
import static com.shop.web.mapper.GroupMapper.mapToUserDto;
/**
 * UserServiceImpl
 */
@Service
public class GroupServiceImpl implements GroupService {

    private GroupRepository userRepo;
    private UserEntityRepository userEntityRepo;

    public GroupServiceImpl(GroupRepository userRepo, UserEntityRepository userEntityRepo) {
        this.userRepo = userRepo;
        this.userEntityRepo = userEntityRepo;
    }

    @Override
    public List<GroupDTO> findallUsers() {
        List<Group> users = userRepo.findAllByOrderById();
        return users.stream().map((user) -> mapToUserDto(user)).collect(Collectors.toList());
    }

    @Override
    public Group save(GroupDTO userDTO) {
        String username = SecurityUtil.getSessionUser();
        UserEntity user_entity = userEntityRepo.findByUsername(username);
        Group user = mapToUser(userDTO);
        user.setCreated_by(user_entity);
        return userRepo.save(user);
    }

    @Override
    public GroupDTO findUserById(long userId) {
        Group user = userRepo.findById(userId).get();

        return mapToUserDto(user);
    }

    @Override
    public void updateUser(GroupDTO userDto) {
        String username = SecurityUtil.getSessionUser();
        UserEntity user_entity = userEntityRepo.findByUsername(username);
        Group user = mapToUser(userDto);
        user.setCreated_by(user_entity);
        userRepo.save(user);
    }

    @Override
    public String delete(long userId) {
        Group user = userRepo.findById(userId).get();
        userRepo.deleteById(userId);
        return user.getName();
    }

    @Override
    public List<GroupDTO> searchUsers(String query) {
        List<Group> users = userRepo.searchUsers(query);
        return users.stream().map(user -> mapToUserDto(user)).collect(Collectors.toList());
    }
    
}