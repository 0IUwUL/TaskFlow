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
        return users.stream().map((group) -> mapToUserDto(group)).collect(Collectors.toList());
    }

    @Override
    public Group save(GroupDTO userDTO) {
        String username = SecurityUtil.getSessionUser();
        UserEntity user_entity = userEntityRepo.findByUsername(username);
        Group group = mapToUser(userDTO);
        group.setCreated_by(user_entity);
        return userRepo.save(group);
    }

    @Override
    public GroupDTO findUserById(long userId) {
        Group group = userRepo.findById(userId).get();

        return mapToUserDto(group);
    }

    @Override
    public void updateUser(GroupDTO userDto) {
        String username = SecurityUtil.getSessionUser();
        UserEntity user_entity = userEntityRepo.findByUsername(username);
        Group group = mapToUser(userDto);
        group.setCreated_by(user_entity);
        userRepo.save(group);
    }

    @Override
    public String delete(long userId) {
        Group group = userRepo.findById(userId).get();
        userRepo.deleteById(userId);
        return group.getName();
    }

    @Override
    public List<GroupDTO> searchGroups(String query) {
        List<Group> users = userRepo.searchGroups(query);
        return users.stream().map(group -> mapToUserDto(group)).collect(Collectors.toList());
    }
    
}