package com.todo.javafinalp1;

import java.util.ArrayList;

public class TaskManager {
    protected ArrayList<TaskPreview> taskList = new ArrayList<>();
    protected ArrayList<Task> tasks = new ArrayList<>();

    public ArrayList<TaskPreview> getTaskList() {
        return taskList;
    }

    public Task getTask(int id) {
        Task returnTask = null;
        for (Task task : tasks) {
            if (task.id == id) {
                returnTask = task;
                break;
            }
        }
        return returnTask;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

}
