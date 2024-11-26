package com.todo.javafinalp1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private static TaskManager taskManager = new TaskManager();
    private static ArrayList<TaskPreview> taskList;

    private static ArrayList<TaskPreview> getTaskList() {
        taskList = taskManager.getTaskList();
        return taskList;
    }

    private static Task getTask(int id) {
        Task task = taskManager.getTask(id);
        return task;
    }

    private static Task addTask(Task task) {
        Task newTask = taskManager.addTask(task);
        return newTask;
    }

    private static Task updateTask(int id, Task task) {
        Task updatedTask = taskManager.updateTask(id, task);
        return updatedTask;
    }

    private boolean deleteTask(int id) {
        return taskManager.deleteTask(id);
    }

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(8080)) {
            System.out.println("server started, waiting for connection...");

            while (true) {
                Socket socket = server.accept();
                System.out.println("connection established, listening...");

                try (ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                     ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {

                    boolean keepConnectionOpen = true;
                    while (keepConnectionOpen) {
                        try {
                            String action;
                            action = (String) ois.readObject();

                            switch (action) {
                                case "getTaskList":
                                    taskList = getTaskList();
                                    for (TaskPreview task : taskList) {
                                        System.out.println(task.getTitle());
                                    }
                                    oos.reset();
                                    oos.writeObject(taskList);
                                    oos.flush();
                                    System.out.println("Task List sent to client");
                                    break;
                                case "addTask":
                                    Task newTask = (Task) ois.readObject();
                                    System.out.println("Received task: " + newTask.getTitle());
                                    Task returnedTask = addTask(newTask);
                                    oos.writeObject(returnedTask);
                                    oos.flush();
                                    oos.reset();
                                    System.out.println("Task created and returned to client");
                                    break;
                                case "exit":
                                    keepConnectionOpen = false;
                                    break;
                                default:
                                    System.out.println("Unknown request type.");
                                    break;
                            }
                        } catch (ClassNotFoundException | IOException e) {
                            System.err.println("Error during request processing: " + e.getMessage());
                            break;
                        }
                    }
                } catch (IOException e) {
                    System.err.println("Error with client connection: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Server shutting down");
    }
}