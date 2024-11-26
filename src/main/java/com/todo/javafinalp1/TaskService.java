package com.todo.javafinalp1;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class TaskService {
    private static Socket socket;
    private static ObjectOutputStream oos;
    private static ObjectInputStream ois;
    private static Task newTask;
    public static void main(String[] args) {
        try {
            socket = new Socket("localhost", 8080);
            System.out.println("Connected to the server.");

            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            oos.flush();

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.println("Please choose an action:");
                System.out.println("1. View All Tasks");
                System.out.println("2. Add a New Task");
                System.out.println("3. Update a Task");
                System.out.println("4. Delete a Task");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                String choice = userInput.readLine();

                switch(choice) {
                    case "1":
                        getTaskList();
                        break;
                    case "2":
                        newTask = addTask(userInput);
                        System.out.println("My new task: " + newTask.getTitle());
                        break;
                    default:
                        System.out.println("Invalid entry.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getTaskList() {
        try {
            // Send the request to the server
            oos.writeObject("getTaskList");  // send fetchAll request
            oos.flush();
            // Receive the task list from the server
//            ArrayList<TaskPreview> taskList = (ArrayList<TaskPreview>) ois.readObject();
            ArrayList<TaskPreview> taskList = (ArrayList<TaskPreview>) ois.readObject();
//            Object obj = ois.readObject();
            for (TaskPreview task : taskList) {
                System.out.println(task.getTitle());
            }
//            System.out.println("Received object of type: " + obj.getClass());
//            if (obj instanceof ArrayList) {
//                ArrayList<TaskPreview> taskList = (ArrayList<TaskPreview>) obj;
//                System.out.println("Task list: " + taskList);
//            } else {
//                Task task = (Task) obj;
//                System.out.println("Unexpected object received: " + task.getTitle());
//            }

            // Display the task list
//            System.out.println("\nTask List:");
//            for (TaskPreview task : taskList) {
//                System.out.println(task.getTitle());
//            }
            System.out.println();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Task addTask(BufferedReader userInput) {
        Task newTask = null;
        try {
            // Send the request to the server
            oos.writeObject("addTask");
            oos.flush();

            //Accept user input and create Task obj
            System.out.println("Enter Task Title: ");
            String title = userInput.readLine();
            newTask = new Task.Builder(title).build();

            //Send new task obj to server
            System.out.println("Sending task: " + newTask.getTitle());
            oos.writeObject(newTask);
            oos.flush();

            //Receive task response
            newTask = (Task) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return newTask;
    }
}