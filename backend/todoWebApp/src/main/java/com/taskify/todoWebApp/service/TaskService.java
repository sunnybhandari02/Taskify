package com.taskify.todoWebApp.service;

import com.taskify.todoWebApp.dto.TaskDto;

import java.util.List;

public interface TaskService {

    public TaskDto createTask(TaskDto taskDto);

    public List<TaskDto> getAllTask();

    public List<TaskDto> getTaskByUserId(Long userId);

    public TaskDto updateTask(Long userId, TaskDto taskDto);

    public void deleteTask(Long id);
}
