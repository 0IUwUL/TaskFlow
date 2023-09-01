package com.shop.web.service;

import java.util.List;

import com.shop.web.dto.GroupDTO;
import com.shop.web.models.Users;

public interface GroupService {
    List<GroupDTO> findallGroups(Users user);
    void save(GroupDTO groupDTO);
    GroupDTO findUserById(long groupId);
    void updateGroup(GroupDTO groupDTO);
    String delete(long groupId);
    List<GroupDTO> searchGroups(String query);
    List<GroupDTO> findallInvolveGroups(Long id);
}
