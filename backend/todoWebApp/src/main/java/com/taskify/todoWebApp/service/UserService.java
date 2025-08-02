package com.taskify.todoWebApp.service;

import com.taskify.todoWebApp.dto.UserDto;
import com.taskify.todoWebApp.entity.User;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();

    UserDto updateUser(Long userId, UserDto userDto);

    void deleteUser(Long userId);
}
