package com.taskify.todoWebApp.service;

import com.taskify.todoWebApp.dto.LoginDto;
import com.taskify.todoWebApp.dto.LoginResponseDto;

public interface AuthService {

    LoginResponseDto login(LoginDto loginDto);
}
