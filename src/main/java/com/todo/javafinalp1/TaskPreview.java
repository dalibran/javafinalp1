package com.todo.javafinalp1;

public class TaskPreview {
    protected int taskId;
    protected String title;
    protected Status currentStatus;

    public TaskPreview(Task task) {
        this.taskId = task.id;
        this.title = task.title;
        this.currentStatus = task.currentStatus;
    }

    public String getTitle() {
        return title;
    }

    public Status getCurrentStatus(){
        return currentStatus;
    }
}
