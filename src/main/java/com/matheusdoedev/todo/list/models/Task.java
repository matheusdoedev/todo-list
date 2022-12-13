package com.matheusdoedev.todo.list.models;

import java.util.Date;
import java.util.UUID;

import com.matheusdoedev.todo.list.enums.TaskStatusEnum;

public abstract class Task {

    private UUID id;
    private String description;
    private long createdAt;
    private long updatedAt;
    private TaskStatusEnum status;

    public Task(String description) {
        this.id = UUID.randomUUID();
        this.description = description;
        this.createdAt = new Date().getTime();
        this.updatedAt = new Date().getTime();
        this.status = TaskStatusEnum.PENDING;
    }

    // getters and setters
    public UUID getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatusEnum getStatus() {
        return status;
    }

    public void setStatus(TaskStatusEnum status) {
        this.status = status;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

}
