package com.shop.web.service;

import java.util.List;

import com.shop.web.dto.GroupDTO;
import com.shop.web.models.Group;

public interface GroupService {
    List<GroupDTO> findallUsers();
    Group save(GroupDTO userDto);
    GroupDTO findUserById(long userId);
    void updateUser(GroupDTO userDto);
    String delete(long userId);
    List<GroupDTO> searchUsers(String query);
}
