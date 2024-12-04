package com.todo.javafinalp1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDate;

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
    }
}
