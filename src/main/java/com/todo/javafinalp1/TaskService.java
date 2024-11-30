package com.todo.javafinalp1;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class TaskService {
    private static Socket socket;
    private static ObjectOutputStream oos;
    private static ObjectInputStream ois;

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
                System.out.println("4. Get a Task");
                System.out.println("5. Delete a Task");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");

                String choice = userInput.readLine();

                switch(choice) {
                    case "1":
                        ArrayList<TaskPreview> taskList = getTaskList();
                        assert taskList != null;
                        for (TaskPreview task : taskList) {
                            System.out.println(task.getTitle());
                        }
                        break;
                    case "2":
                        Task newTask = addTask(userInput);
                        System.out.println("My new task: " + newTask.getTitle());
                        break;
                    case "3":
                        Task updatedTask = updateTask(userInput);
                        System.out.println("My updated task: " + updatedTask.getTitle());
                        break;
                    case "4":
                        Task retrievedTask = getTask(userInput);
                        System.out.println("My retrieved task: " + retrievedTask.getTitle());
                        break;
                    case "5":
                        boolean isDeleted = deleteTask(userInput);
                        if(isDeleted) {
                            System.out.println("My task was deleted");
                        } else {
                            System.out.println("Wasn't deleted");
                        }
                        break;
                    case "6":
                        break;
                    default:
                        System.out.println("Invalid entry.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<TaskPreview> getTaskList() {
        try {
            // Send the request to the server
            oos.writeObject("getTaskList");  // send fetchAll request
            oos.flush();

            // Receive the task list from the server
            ArrayList<TaskPreview> taskList = (ArrayList<TaskPreview>) ois.readObject();
            return taskList;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Task getTask(BufferedReader userInput) {
        Task retrievedTask = null;
        try {
            // Send the request to the server
            oos.writeObject("getTask");
            oos.flush();

            //Accept user input and create Task obj
            System.out.println("Enter id of Task to Retrieve: ");
            String taskId = userInput.readLine();
            oos.writeObject(taskId);
            oos.flush();

            //Receive task response
            retrievedTask = (Task) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return retrievedTask;
    }

    public static Task addTask(BufferedReader userInput) {
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

    public static Task updateTask(BufferedReader userInput) {
        Task updatedTask = null;
        try {
            // Send the request to the server
            oos.writeObject("updateTask");
            oos.flush();

            //Accept user input and create Task obj
            System.out.println("Enter Task Id of Task to Update: ");
            String title = userInput.readLine();
            updatedTask = null;

            //Send new task obj to server
            System.out.println("Sending task: " + updatedTask.getTitle());
            oos.writeObject(updatedTask);
            oos.flush();

            //Receive task response
            updatedTask = (Task) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return updatedTask;
    }

    public static boolean deleteTask(BufferedReader userInput) {
        boolean isDeleted = false;
        try {
            //Send the request to the server
            oos.writeObject("deleteTask");
            oos.flush();

            //Accept user input and create Task obj
            System.out.println("Enter id of Task to Delete: ");
            String taskId = userInput.readLine();
            oos.writeObject(taskId);
            oos.flush();

            //Receive delete status from server
            isDeleted = ois.readBoolean();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isDeleted;
    }
}