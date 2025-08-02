package com.taskify.todoWebApp.service.impl;

import com.taskify.todoWebApp.dto.LoginDto;
import com.taskify.todoWebApp.dto.LoginResponseDto;
import com.taskify.todoWebApp.entity.User;
import com.taskify.todoWebApp.mapper.UserMapper;
import com.taskify.todoWebApp.repository.UserRepository;
import com.taskify.todoWebApp.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public LoginResponseDto login(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(()-> new RuntimeException("Invalid email or password"));

        if(!user.getPassword().equals(loginDto.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        LoginResponseDto response = new LoginResponseDto("Login successful",
                UserMapper.mapToUserDto(user));
        return response;
    }
}
