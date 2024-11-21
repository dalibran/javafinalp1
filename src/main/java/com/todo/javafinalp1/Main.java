package com.todo.javafinalp1;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        Task task1 = new Task.Builder("Finish App")
                .description("This is a test")
                .build();
        task1.addComment(new Comment("This is a comment"));

        //adds
        manager.addTask(task1);
        ArrayList<TaskPreview> list = manager.getTaskList();

        //prints TaskPreview objects
        for (TaskPreview preview : list) {
            System.out.println(preview.getTaskId());
            System.out.println(preview.getTitle());
            System.out.println(preview.getCurrentStatus());
            System.out.println();
        }
    }
}
