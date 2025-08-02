package com.taskify.todoWebApp.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TaskStatus {

    PENDING,
    IN_PROGRESS,
    COMPLETED;

    @JsonCreator
    public static TaskStatus fromString(String value) {
        return TaskStatus.valueOf(value.toUpperCase());
    }
}
