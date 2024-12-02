package com.todo.javafinalp1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        // Test task objs
        Task task1 = new Task.Builder("My 2nd Task")
                .description("This is a test")
                .build();
        task1.addComment(new Comment("This is a comment"));

        Task task2 = new Task.Builder("My 3rd Task")
                .description("This is a test")
                .build();

        Task task3 = new Task.Builder("My 2nd Task - Updated")
                .description("This is a test")
                .build();

        TaskService client;
        try {
            client = new TaskService();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ArrayList<TaskPreview> taskList = client.getTaskList();
        for (TaskPreview task : taskList) {
            System.out.println("It works!");
            System.out.println(task.getTitle());
        }
        System.out.println("This is addTask: " + client.addTask(task1).getTitle());
        System.out.println("This is addTask: " + client.addTask(task2).getTitle());
        System.out.println("This is getTask: " + client.getTask(1).getTitle());
        System.out.println("This is updateTask: " + client.updateTask(2, task3).getTitle());
        if (client.deleteTask(3)) {
            System.out.println("Was deleted");
        }
        taskList = new ArrayList<>();
        taskList = client.getTaskList();
        for (TaskPreview task : taskList) {
            System.out.println(task.getTitle());
        }

//        System.out.println("This is deleteTask: " + client.deleteTask(1);
    }
}
