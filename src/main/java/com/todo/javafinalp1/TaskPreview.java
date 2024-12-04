package com.todo.javafinalp1;

import java.io.Serializable;
import java.time.LocalDate;

public class TaskPreview implements Serializable {
    private int taskId;
    private String title;
    private Status currentStatus;
    private LocalDate dueDate;
    private static final long serialVersionUID = 2L; // Optional, ensures compatibility

    public TaskPreview(int id, Task task) {
        this.taskId = id;
        this.title = task.getTitle();
        this.currentStatus = task.getCurrentStatus();
        this.dueDate = task.getDueDate();
    }

    public int getTaskId() {
        return taskId;
    }

    public String getTitle() {
        return title;
    }

    public Status getCurrentStatus(){
        return currentStatus;
    }

    public LocalDate getDueDate() { return dueDate; }
}
