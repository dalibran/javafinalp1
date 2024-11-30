package com.todo.javafinalp1;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        Task task1 = new Task.Builder("Finish App")
                .description("This is a test")
                .build();
        task1.addComment(new Comment("This is a comment"));

        //adds
        manager.addTask(task1);
        HashMap<Integer, Task> tasks = manager.getTasks();
        System.out.println("This is tasks: " + manager.getTask(1));
    }
}
