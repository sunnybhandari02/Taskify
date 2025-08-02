package com.taskify.todoWebApp.controller;

import com.taskify.todoWebApp.dto.TaskDto;
import com.taskify.todoWebApp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // Build Create Task REST API
    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        TaskDto savedTask = taskService.createTask(taskDto);
        return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
    }

    // Build Get All Task REST API
    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTask() {
        List<TaskDto> tasks = taskService.getAllTask();
        return ResponseEntity.ok(tasks);
    }

    // Build Get Task REST API
    @GetMapping("{id}")
    public ResponseEntity<List<TaskDto>> getTaskByUser(@PathVariable("id") Long userId) {
        List<TaskDto> tasks = taskService.getTaskByUserId(userId);
        return ResponseEntity.ok(tasks);
    }

    // Build Update Task REST API
    @PutMapping("{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable("id") Long taskId,
                                              @RequestBody TaskDto taskDto) {
        TaskDto task = taskService.updateTask(taskId, taskDto);
        return ResponseEntity.ok(task);
    }

    // Build Delete Task REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.ok("Task Deleted Successfully!!");
    }
}
