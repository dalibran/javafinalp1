package com.todo.javafinalp1;

import java.io.Serializable;

public class TaskPreview implements Serializable {
    private int taskId;
    private String title;
    private Status currentStatus;
    private static final long serialVersionUID = 2L; // Optional, ensures compatibility

    public TaskPreview(int id, Task task) {
        this.taskId = id;
        this.title = task.title;
        this.currentStatus = task.currentStatus;
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
}
