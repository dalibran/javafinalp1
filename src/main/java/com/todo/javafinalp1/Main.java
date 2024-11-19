package com.todo.javafinalp1;

public class Main {
    public static void main(String[] args) {
        Task task1 = new Task.Builder("Finish App")
                .description("This is a test")
                .build();
        Task task2 = new Task.Builder("Get Money")
                .build();
        task1.addComment(new Comment("This is a comment"));

        System.out.println(task1.id);
        System.out.println(task1.title);
        System.out.println(task1.description);
        System.out.println(task1.createdAt);
        System.out.println(task1.currentStatus);
        System.out.println();
        System.out.println(task2.id);
        System.out.println();
        System.out.println(task1.getComment(0).id);
        System.out.println(task1.getComment(0).description);
    }
}
