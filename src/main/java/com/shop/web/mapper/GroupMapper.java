package com.shop.web.mapper;

import java.util.stream.Collectors;

import com.shop.web.dto.GroupDTO;
import com.shop.web.models.Group;

import static com.shop.web.mapper.TaskMapper.maptoTaskDTO;;

public class GroupMapper {
    public static Group mapToUser(GroupDTO user) {
        Group userDto = Group.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .address(user.getAddress())
                        .created_by(user.getCreated_by())
                        .createdOn(user.getCreatedOn())
                        .updatedOn(user.getUpdatedOn())
                        .build();
        return userDto;
    }

    public static GroupDTO mapToUserDto(Group user){
        GroupDTO userDto = GroupDTO.builder()
                            .id(user.getId())
                            .name(user.getName())
                            .address(user.getAddress())
                            .created_by(user.getCreated_by())
                            .createdOn(user.getCreatedOn())
                            .updatedOn(user.getUpdatedOn())
                            .todo(user.getTodo().stream().map((task) -> maptoTaskDTO(task)).collect(Collectors.toList()))
                            .build();
        return userDto;

    }
}
