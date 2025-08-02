package com.taskify.todoWebApp.mapper;

import com.taskify.todoWebApp.dto.UserDto;
import com.taskify.todoWebApp.entity.User;

public class UserMapper {

    public static UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getPassword(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    public static User mapToUser(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getUserName(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getCreatedAt(),
                userDto.getUpdatedAt()
        );
    }

}
