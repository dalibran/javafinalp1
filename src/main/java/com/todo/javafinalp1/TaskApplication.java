package com.todo.javafinalp1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TaskApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        Parent listView = FXMLLoader.load(getClass().getResource("TaskListView.fxml"));
        Parent addView = FXMLLoader.load(getClass().getResource("TaskAddView.fxml"));

        // Set up the scenes
        Scene listScene = new Scene(listView, 600, 500);
        Scene addScene = new Scene(addView, 600, 500);

        // Create references to scenes in different controllers
        TaskListController.setAddScene(addScene);
        TaskAddController.setListScene(listScene);
        TaskEditController.setListScene(listScene);

        primaryStage.setTitle("Task Manager");
        primaryStage.setScene(listScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
