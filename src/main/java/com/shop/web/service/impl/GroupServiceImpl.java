package com.shop.web.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.shop.web.dto.GroupDTO;
import com.shop.web.models.Group;
import com.shop.web.models.Users;
import com.shop.web.repository.UserRepository;
import com.shop.web.repository.GroupRepository;
import com.shop.web.repository.RoleRepository;
import com.shop.web.security.SecurityUtil;
import com.shop.web.service.GroupService;
import static com.shop.web.mapper.GroupMapper.mapToUser;
import static com.shop.web.mapper.GroupMapper.mapToUserDto;

@Service
public class GroupServiceImpl implements GroupService {

    private GroupRepository groupRepo;
    private UserRepository userRepo;
    private RoleRepository roleRepo;
    private UserServiceImp userImpl;

    public GroupServiceImpl(GroupRepository groupRepo, UserRepository userRepo, 
                            RoleRepository roleRepo, UserServiceImp userImpl) {
        this.groupRepo = groupRepo;
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.userImpl = userImpl;
    }

    @Override
    public List<GroupDTO> findallGroups(Users user) {
        List<Group> groups = groupRepo.findByAdminOrderById(user);
        return groups.stream().map((group) -> mapToUserDto(group)).collect(Collectors.toList());
    }

    @Override
    public List<GroupDTO> findallInvolveGroups(Long id) {
        List<Group> groups = groupRepo.findByUsersIdOrderById(id);
        return groups.stream().map((group) -> mapToUserDto(group)).collect(Collectors.toList());
    }

    @Override
    public void save(GroupDTO groupDTO) {
        String username = SecurityUtil.getSessionUser();
        Users users = userRepo.findByUsername(username);
        
        Group group = mapToUser(groupDTO);
        group.setAdmin(users);
        groupRepo.save(group);
        
        // userImpl.updateRole(users);
    }

    @Override
    public GroupDTO findUserById(long userId) {
        Group group = groupRepo.findById(userId).get();

        return mapToUserDto(group);
    }

    @Override
    public void updateGroup(GroupDTO groupDTO) {
        String username = SecurityUtil.getSessionUser();
        Users users = userRepo.findByUsername(username);
        Group group = mapToUser(groupDTO);
        group.setAdmin(users);
        groupRepo.save(group);
    }

    @Override
    public String delete(long userId) {
        Group group = groupRepo.findById(userId).get();
        groupRepo.deleteById(userId);
        return group.getName();
    }

    @Override
    public List<GroupDTO> searchGroups(String query) {
        List<Group> users = groupRepo.searchGroups(query);
        return users.stream().map(group -> mapToUserDto(group)).collect(Collectors.toList());
    }
    
}