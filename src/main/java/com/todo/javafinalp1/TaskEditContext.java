package com.todo.javafinalp1;

public class TaskEditContext {
    private static TaskEditContext instance = new TaskEditContext();
    private int currentTaskId;
    private Task currentTask;

    protected TaskEditContext() {}

    public static TaskEditContext getInstance() {
        return instance;
    }

    public Task getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(Task task) {
        this.currentTask = task;
    }

    public int getCurrentTaskId() { return currentTaskId; }

    public void setCurrentTaskId(int taskId) { this.currentTaskId = taskId; }
}

