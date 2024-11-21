package com.todo.javafinalp1;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private ArrayList<TaskPreview> taskList = new ArrayList<>();
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private static int counter = 0;
    private int incrementId() {
        return ++counter;
    }

    public TaskManager() {
        Task exampleTask = new Task.Builder("Create First Task")
                .description("This is a an example task used to guide you through the process of creating your first task.")
                .build();

        this.addTask(exampleTask);
    }

    public ArrayList<TaskPreview> getTaskList() {
        tasks.forEach((key, value) -> {
            TaskPreview taskPreview = new TaskPreview(key, value);
            taskList.add(taskPreview);
        });

        return taskList;
    }

    public Task getTask(int id) {
        return tasks.get(id);
    }

    public Task addTask(Task task) {
        int id = incrementId();
        tasks.put(id, task);
        return tasks.get(id);
    }

    public Task updateTask(int id, Task task) {
        tasks.put(id, task);
        return tasks.get(id);
    }

    public boolean deleteTask(int id) {
        Task deletedTask = tasks.get(id);
        tasks.remove(id);
        return tasks.containsValue(deletedTask);
    }
}
