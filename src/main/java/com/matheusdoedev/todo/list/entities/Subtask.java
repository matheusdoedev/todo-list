package com.matheusdoedev.todo.list.entities;

import java.util.UUID;

import com.matheusdoedev.todo.list.models.Task;

public class Subtask extends Task {
    
    private UUID parentTaskID;
    
    public Subtask(String description, UUID parentId) {
        super(description);
        this.parentTaskID = parentId;
    }

    public UUID getParentTaskID() {
        return parentTaskID;
    }

    public void setParentTaskID(UUID parentTaskID) {
        this.parentTaskID = parentTaskID;
    }
    
    
}
