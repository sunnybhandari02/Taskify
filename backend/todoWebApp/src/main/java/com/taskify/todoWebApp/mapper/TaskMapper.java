package com.taskify.todoWebApp.mapper;

import com.taskify.todoWebApp.dto.TaskDto;
import com.taskify.todoWebApp.entity.Task;
import com.taskify.todoWebApp.entity.User;

import java.time.LocalDateTime;

public class TaskMapper {

    public static TaskDto mapToTaskDto(Task task) {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getDueDate(),
                task.getUser().getId(),
                task.getCreatedAt(),
                task.getUpdatedAt()
        );
    }

    public static Task mapToTask(TaskDto dto, User user) {
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        task.setDueDate(dto.getDueDate());
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        task.setUser(user);
        return task;
    }
}
