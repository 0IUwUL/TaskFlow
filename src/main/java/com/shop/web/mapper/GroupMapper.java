package com.shop.web.mapper;

import java.util.stream.Collectors;

import com.shop.web.dto.GroupDTO;
import com.shop.web.models.Group;

import static com.shop.web.mapper.TaskMapper.maptoTaskDTO;;

public class GroupMapper {
    public static Group mapToUser(GroupDTO groupDto) {
        Group group = Group.builder()
                        .id(groupDto.getId())
                        .name(groupDto.getName())
                        .address(groupDto.getAddress())
                        .created_by(groupDto.getCreated_by())
                        .createdOn(groupDto.getCreatedOn())
                        .updatedOn(groupDto.getUpdatedOn())
                        .build();
        return group;
    }

    public static GroupDTO mapToUserDto(Group group){
        GroupDTO groupDTO = GroupDTO.builder()
                            .id(group.getId())
                            .name(group.getName())
                            .address(group.getAddress())
                            .created_by(group.getCreated_by())
                            .createdOn(group.getCreatedOn())
                            .updatedOn(group.getUpdatedOn())
                            .todo(group.getTodo().stream().map((task) -> maptoTaskDTO(task)).collect(Collectors.toList()))
                            .build();
        return groupDTO;

    }
}
