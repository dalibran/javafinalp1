package com.todo.javafinalp1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;

public class TaskController extends Application {

    //Task Service
    private TaskService client;

    //Should Launch JavaFX
    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) {
        client = new TaskService();

        // Listview for displaying Tasks
        ListView<String> taskListView = new ListView<>();


        //Button to add to our Task
        Button addButton = new Button("Add task");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Task newTask = new Task.Builder("New task").build();

                // use getter class? Check discord!!!!
                Task returnedTask = client.addTask(newTask);

                System.out.println("Task Added: " + returnedTask.getTitle());

            }
        });

        //button to get display including all tasks?
        Button getListButton = new Button("Get Task list");
        getListButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //retrieve our tasks from service
                List<TaskPreview> tasks = client.getTaskList();

                //clear list
                taskListView.getItems().clear();
                for (TaskPreview task : tasks) {
                    taskListView.getItems().add(task.getTitle());
                }

                // include print console???
                System.out.println("Current Tasks:");
                for (TaskPreview task : tasks) {
                    System.out.println(" - " + task.getTitle());
                }
            }
        });

        //Layout for TESTING (Using VBOX, NOT StackPane)
        //Note to self, StackPane doesn't like layout.setSpacing ??

        VBox layout = new VBox();
        layout.getChildren().addAll(addButton, getListButton, taskListView);

        //Adjustments?
        layout.setSpacing(20);

        //scene setup?
        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Task Manager");
        primaryStage.show();

    }
}


///// I'm back bitches




//event handlers go here
// this class contains an instance of TaskService

//package main.java;
//
//public class TaskController {
//}

