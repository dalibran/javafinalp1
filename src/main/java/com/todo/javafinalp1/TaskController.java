package com.todo.javafinalp1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

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
    private static Scene addScene;
    private TaskService client;
    private Scene editScene;

    {
        try {
            client = new TaskService();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setAddScene(Scene scene) {
        addScene = scene;
    }

    @FXML
    public void initialize() {
        // Populate the ListView with existing tasks from getTaskList()
        handleRefreshTaskList();
    }

    @FXML
    private void openTaskDetail(int taskId) {
        Task task = TaskService.getTask(taskId);
        System.out.println("Task title " + task.getTitle());

        // Handle Add Task button click
        System.out.println("Edit Task button clicked! Navigate to the edit Task view.");
        // Logic for navigation or opening a new view goes here
    }

    @FXML
    private void handleAddTask(ActionEvent event) {
        System.out.println("Add task button clicked");
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(addScene);
        //copy and paste?
    }
    @FXML
    private void handleEditTask(ActionEvent event) {
        System.out.println("Edit task button clicked");

        // Load the edit scene only if it hasn't been loaded yet
        if (editScene == null) {
            try {
                // Load the TaskEditView.fxml
                FXMLLoader loader = new FXMLLoader(getClass().getResource("TaskEditView.fxml"));
                Parent editRoot = loader.load();  // Load the FXML
                editScene = new Scene(editRoot);  // Create the scene
            } catch (IOException e) {
                e.printStackTrace();  // Handle loading error
                return;  // Exit if the scene cannot be loaded
            }
        }

        // Get the current stage and switch to the edit scene
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(editScene);  // Set the loaded edit scene
    }

    @FXML
    private void handleRefreshTaskList() {
        taskListView.getItems().clear();
        ArrayList<TaskPreview> taskList = TaskService.getTaskList();
        System.out.println("Got tasklist");
        for (TaskPreview task : taskList) {
            String dueDate;
            if (task.getDueDate() != null) {
                dueDate = task.getDueDate().toString();
            } else {
                dueDate = "";
            }

            HBox taskItem = createTaskItem(task.getTitle(), task.getCurrentStatus().toString(), dueDate, task.getTaskId());

            taskItem.setOnMouseClicked(event -> {
                openTaskDetail(task.getTaskId());
            });

            taskListView.getItems().add(taskItem);
        }
    }

    private HBox createTaskItem(String title, String status, String dueDate, int taskId) {
        HBox taskItem = new HBox(50); // Match spacing with the header
        taskItem.setStyle("-fx-padding: 5; -fx-border-color: lightgray; -fx-border-width: 0 0 1 0;");

        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-size: 12px; -fx-pref-width: 200; -fx-alignment: center-left;");

        Label statusLabel = new Label(status);
        statusLabel.setStyle("-fx-font-size: 12px; -fx-pref-width: 150; -fx-alignment: center-left;");

        Label dueDateLabel = new Label(dueDate);
        dueDateLabel.setStyle("-fx-font-size: 12px; -fx-pref-width: 150; -fx-alignment: center-left;");

        Label taskIdLabel = new Label(String.valueOf(taskId));
        taskIdLabel.setVisible(false);

        taskItem.getChildren().addAll(titleLabel, statusLabel, dueDateLabel, taskIdLabel);
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

