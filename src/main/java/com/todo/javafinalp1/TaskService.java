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

            // Send updated task obj to server
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