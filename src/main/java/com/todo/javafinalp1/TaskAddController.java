package com.todo.javafinalp1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class TaskAddController {
    @FXML
    private TextField titleField;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private ChoiceBox statusChoiceBox;

    @FXML
    private DatePicker dueDatePicker;

    private static Scene listScene;

    public static void setListScene(Scene scene) {
        listScene = scene;
    }

    @FXML
    private void handleSaveTask(ActionEvent event) {
        String title = titleField.getText();
        String description = descriptionArea.getText();
        String status = (String) statusChoiceBox.getValue();
        LocalDate dueDate = dueDatePicker.getValue();

        String normalizedStatus = status.toUpperCase().replace(" ", "_");

        Task newTask = new Task.Builder(title)
                .description(description)
                .currentStatus(normalizedStatus)
                .dueDate(dueDate)
                .build();

        Task returnedTask = TaskService.addTask(newTask);

        loadTaskListView(event);
        System.out.println("Task was added: " + returnedTask.getTitle());
    }

    protected void loadTaskListView(ActionEvent event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Set the new scene as the content of the current stage
        currentStage.setScene(listScene);
    }
}
