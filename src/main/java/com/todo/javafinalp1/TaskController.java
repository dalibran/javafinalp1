package com.todo.javafinalp1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskController {
//
//    //Task Service
//    private TaskService client;
//
//    //Should Launch JavaFX
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage primaryStage) {
//        try {
//            client = new TaskService();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        // Listview for displaying Tasks
//        ListView<String> taskListView = new ListView<>();
//
//
//        //Button to add to our Task
//        Button addButton = new Button("Add task");
//        addButton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                Task newTask = new Task.Builder("New task")
//                        .description("Stuff")
//                        .build();
//
//                // use getter class? Check discord!!!!
//                Task returnedTask = client.addTask(newTask);
//
//                System.out.println("Task Added: " + returnedTask.getTitle());
//
//            }
//        });
//
//        //button to get display including all tasks?
//        Button getListButton = new Button("Get Task list");
//        getListButton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//
//                //retrieve our tasks from service
//                List<TaskPreview> tasks = client.getTaskList();
//
//                //clear list
//                taskListView.getItems().clear();
//                for (TaskPreview task : tasks) {
//                    taskListView.getItems().add(task.getTitle());
//                }
//
//                // include print console???
//                System.out.println("Current Tasks:");
//                for (TaskPreview task : tasks) {
//                    System.out.println(" - " + task.getTitle());
//                }
//            }
//        });
//
//        //Layout for TESTING (Using VBOX, NOT StackPane)
//        //Note to self, StackPane doesn't like layout.setSpacing ??
//
//        VBox layout = new VBox();
//        layout.getChildren().addAll(addButton, getListButton, taskListView);
//
//        //Adjustments?
//        layout.setSpacing(20);
//
//        //scene setup?
//        Scene scene = new Scene(layout, 300, 250);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("Task Manager");
//        primaryStage.show();
//
//    }
    @FXML
    private Text pageTitle;

    @FXML
    private ListView<HBox> taskListView;

    @FXML
    private Button addTaskButton;

    private TaskService client;
    {
        try {
            client = new TaskService();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void initialize() {
        // Populate the ListView with existing tasks from getTaskList()
        ArrayList<TaskPreview> taskList = client.getTaskList();
        for (TaskPreview task : taskList) {
            String dueDate;
            if (task.getDueDate() != null) {
                dueDate = task.getDueDate().toString();
            } else {
                dueDate = "";
            }
            HBox taskItem = createTaskItem(task.getTitle(), task.getCurrentStatus().toString(), dueDate);
            taskListView.getItems().add(taskItem);
        }
    }

    @FXML
    private void handleAddTask() {
        // Handle Add Task button click
        System.out.println("Add Task button clicked! Navigate to the Add Task view.");
        // Logic for navigation or opening a new view goes here
    }

    // Create task items for the FXML ListView
    private HBox createTaskItem(String title, String status, String dueDate) {
        HBox taskItem = new HBox(50); // Match spacing with the header
        taskItem.setStyle("-fx-padding: 5; -fx-border-color: lightgray; -fx-border-width: 0 0 1 0;");

        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-size: 12px; -fx-pref-width: 200; -fx-alignment: center-left;");

        Label statusLabel = new Label(status);
        statusLabel.setStyle("-fx-font-size: 12px; -fx-pref-width: 150; -fx-alignment: center-left;");

        Label dueDateLabel = new Label(dueDate);
        dueDateLabel.setStyle("-fx-font-size: 12px; -fx-pref-width: 150; -fx-alignment: center-left;");

        taskItem.getChildren().addAll(titleLabel, statusLabel, dueDateLabel);
        return taskItem;
    }
}


///// I'm back bitches




//event handlers go here
// this class contains an instance of TaskService

//package main.java;
//
//public class TaskController {
//}

