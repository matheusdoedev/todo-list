package com.matheusdoedev.todo.list.entities;

import com.matheusdoedev.todo.list.enums.TaskRecurrenceTypeEnum;
import com.matheusdoedev.todo.list.models.Task;

public class RecurrentTask extends Task {
    
    private TaskRecurrenceTypeEnum recurrenceType;
    
    public RecurrentTask(int id, String descripton, TaskRecurrenceTypeEnum recurrenceType) {
        super(id, descripton);
        this.recurrenceType = recurrenceType;
    }

    public TaskRecurrenceTypeEnum getRecurrenceType() {
        return recurrenceType;
    }

    public void setRecurrenceType(TaskRecurrenceTypeEnum recurrenceType) {
        this.recurrenceType = recurrenceType;
    }
    
}
