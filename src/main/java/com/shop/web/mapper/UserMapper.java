package com.shop.web.mapper;

import java.util.stream.Collectors;

import com.shop.web.dto.UserDTO;
import com.shop.web.models.User;

import static com.shop.web.mapper.DetailMapper.maptoDetailDTO;;

public class UserMapper {
    public static User mapToUser(UserDTO user) {
        User userDto = User.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .address(user.getAddress())
                        .created_by(user.getCreated_by())
                        .createdOn(user.getCreatedOn())
                        .updatedOn(user.getUpdatedOn())
                        .build();
        return userDto;
    }

    public static UserDTO mapToUserDto(User user){
        UserDTO userDto = UserDTO.builder()
                            .id(user.getId())
                            .name(user.getName())
                            .address(user.getAddress())
                            .created_by(user.getCreated_by())
                            .createdOn(user.getCreatedOn())
                            .updatedOn(user.getUpdatedOn())
                            .todo(user.getTodo().stream().map((detail) -> maptoDetailDTO(detail)).collect(Collectors.toList()))
                            .build();
        return userDto;

    }
}
