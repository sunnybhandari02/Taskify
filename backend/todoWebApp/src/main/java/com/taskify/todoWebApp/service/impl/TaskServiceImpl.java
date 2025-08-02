package com.taskify.todoWebApp.service.impl;

import com.taskify.todoWebApp.dto.TaskDto;
import com.taskify.todoWebApp.entity.Task;
import com.taskify.todoWebApp.entity.User;
import com.taskify.todoWebApp.enums.TaskStatus;
import com.taskify.todoWebApp.exception.ResourceNotFoundException;
import com.taskify.todoWebApp.mapper.TaskMapper;
import com.taskify.todoWebApp.repository.TaskRepository;
import com.taskify.todoWebApp.repository.UserRepository;
import com.taskify.todoWebApp.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskDto createTask(TaskDto taskDto) {
        User user = userRepository.findById(taskDto.getUserId()).orElseThrow(
                () -> new ResourceNotFoundException("User not exist with given id: " + taskDto.getUserId())
        );
        if("pending".equalsIgnoreCase(taskDto.getStatus().toString())) {
            taskDto.setStatus(TaskStatus.PENDING);
        }
        else if("inprogress".equalsIgnoreCase(taskDto.getStatus().toString())) {
            taskDto.setStatus(TaskStatus.IN_PROGRESS);
        }
        else if("completed".equalsIgnoreCase(taskDto.getStatus().toString())) {
            taskDto.setStatus(TaskStatus.COMPLETED);
        }
        Task task = TaskMapper.mapToTask(taskDto, user);
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(taskDto.getStatus());
        task.setDueDate(taskDto.getDueDate());
        task.setUser(user);

        return TaskMapper.mapToTaskDto(taskRepository.save(task));
    }

    @Override
    public List<TaskDto> getAllTask() {
        return taskRepository.findAll().stream()
                .map(TaskMapper::mapToTaskDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> getTaskByUserId(Long userId) {
        return taskRepository.findByUserId(userId).stream()
                .map(TaskMapper::mapToTaskDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDto updateTask(Long userId, TaskDto taskDto) {
        Task task = taskRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User not exist with given id: " + taskDto.getUserId())
        );
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(taskDto.getStatus());
        task.setDueDate(taskDto.getDueDate());
        task.setUpdatedAt(LocalDateTime.now());
        return TaskMapper.mapToTaskDto(taskRepository.save(task));
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
