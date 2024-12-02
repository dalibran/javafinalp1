package com.todo.javafinalp1;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class TaskService {
    private static ObjectOutputStream oos;
    private static ObjectInputStream ois;

    public TaskService() throws IOException {
        Socket socket = new Socket("localhost", 8080);
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());
        oos.flush();
    }

//    public static void main(String[] args) {
//        try {
//            socket = new Socket("localhost", 8080);
//            System.out.println("Connected to the server.");
//
//            oos = new ObjectOutputStream(socket.getOutputStream());
//            ois = new ObjectInputStream(socket.getInputStream());
//            oos.flush();
//
//            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
//
//            while (true) {
//                System.out.println("Please choose an action:");
//                System.out.println("1. View All Tasks");
//                System.out.println("2. Add a New Task");
//                System.out.println("3. Update a Task");
//                System.out.println("4. Get a Task");
//                System.out.println("5. Delete a Task");
//                System.out.println("6. Exit");
//                System.out.print("Enter your choice: ");
//
//                String choice = userInput.readLine();
//
//                switch(choice) {
//                    case "1":
//                        ArrayList<TaskPreview> taskList = getTaskList();
//                        assert taskList != null;
//                        for (TaskPreview task : taskList) {
//                            System.out.println(task.getTitle());
//                        }
//                        break;
//                    case "2":
//                        Task newTask = addTask(userInput);
//                        System.out.println("My new task: " + newTask.getTitle());
//                        break;
//                    case "3":
//                        Task updatedTask = updateTask(userInput);
//                        System.out.println("My updated task: " + updatedTask.getTitle());
//                        break;
//                    case "4":
//                        Task retrievedTask = getTask(userInput);
//                        System.out.println("My retrieved task: " + retrievedTask.getTitle());
//                        break;
//                    case "5":
//                        boolean isDeleted = deleteTask(userInput);
//                        if(isDeleted) {
//                            System.out.println("My task was deleted");
//                        } else {
//                            System.out.println("Wasn't deleted");
//                        }
//                        break;
//                    case "6":
//                        break;
//                    default:
//                        System.out.println("Invalid entry.");
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public synchronized static ArrayList<TaskPreview> getTaskList() {
        try {
            // Send the request to the server
            oos.writeObject("getTaskList");
            oos.flush();

            // Receive the task list from the server
            return (ArrayList<TaskPreview>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error fetching task list: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public synchronized static Task getTask(int taskId) {
        try {
            // Send the request to the server
            oos.writeObject("getTask");
            oos.flush();

            // Send id of task obj to retrieve
            oos.writeInt(taskId);
            oos.flush();

            // Receive task response
            return (Task) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new Task.Builder("This task is an error").build();
        }
    }

    public synchronized static Task addTask(Task newTask) {
        try {
            // Send the request to the server
            oos.writeObject("addTask");
            oos.flush();

            // Send new task obj to server
            oos.writeObject(newTask);
            oos.flush();

            // Receive task response
            return (Task) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new Task.Builder("This task is an error").build();
        }
    }

    public synchronized static Task updateTask(int taskId, Task updatedTask) {
        try {
            // Send the request to the server
            oos.writeObject("updateTask");
            oos.flush();

            // Send taskId to the server
            oos.writeInt(taskId);
            oos.flush();

            //Send updated task obj to server
            oos.writeObject(updatedTask);
            oos.flush();

            // Receive task response
            return (Task) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new Task.Builder("This task is an error").build();
        }
    }

    public synchronized static boolean deleteTask(int taskId) {
        try {
            // Send the request to the server
            oos.writeObject("deleteTask");
            oos.flush();

            // Send id of task to be deleted
            oos.writeInt(taskId);
            oos.flush();

            // Receive delete status from server
            return ois.readBoolean();
        } catch (IOException e) {
            System.err.println("Error deleting task: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}