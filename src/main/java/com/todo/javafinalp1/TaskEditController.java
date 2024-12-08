package com.todo.javafinalp1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskEditController extends Controller {
    @FXML
    private TextField titleField;  // TextField for the task title
    @FXML
    private TextArea descriptionArea;  // TextArea for the task description
    @FXML
    private ChoiceBox<String> statusChoiceBox;  // ChoiceBox for selecting task status
    @FXML
    private Label creationDateLabel;
    @FXML
    private DatePicker dueDatePicker;  // DatePicker for selecting the task's due date
    private static Scene listScene;
    private Task taskToEdit = TaskEditContext.getInstance().getCurrentTask();
    private int currentTaskId = TaskEditContext.getInstance().getCurrentTaskId();

    public static void setListScene(Scene scene) {
        listScene = scene;
    }

    public void initialize() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
        String formattedDate = taskToEdit.getCreatedAt().format(formatter);

        if (taskToEdit != null) {
            titleField.setText(taskToEdit.getTitle());
            descriptionArea.setText(taskToEdit.getDescription());
            statusChoiceBox.setValue(taskToEdit.getCurrentStatus().toString());
            dueDatePicker.setValue(taskToEdit.getDueDate());
            creationDateLabel.setText(formattedDate);
        }
    }

    // Handle the Save button click
    @FXML
    private void handleSaveTask(ActionEvent event) {
        // Retrieve values from the form fields
        String taskTitle = titleField.getText();
        String taskDescription = descriptionArea.getText();
        String taskStatus = statusChoiceBox.getValue();
        LocalDate dueDate = dueDatePicker.getValue();

        // Convert task status to uppercase to match enum constants
        String normalizedStatus = taskStatus.toUpperCase().replace(" ", "_");

        // Build the updated task object
        Task updatedTask = new Task.Builder(taskTitle)
                .description(taskDescription)
                .currentStatus(normalizedStatus)  // Use the string status for the builder, or set it as needed
                .dueDate(dueDate)
                .build();

        // Use TaskService to update the task on the server
        Task updatedTaskResponse = TaskService.updateTask(currentTaskId, updatedTask);

        // Check for update success and close the window if successful
        if (updatedTaskResponse != null) {
            System.out.println("Task updated successfully!");
        } else {
            System.out.println("Failed to update the task.");
        }

        switchScene(listScene, event);
    }

    @FXML
    private void handleDeleteTask(ActionEvent event) {
        if(TaskService.deleteTask(currentTaskId)) {
            System.out.println("Task was deleted");
            switchScene(listScene, event);
        } else {
            System.out.println("Task was not deleted");
        }
    }

    @FXML
    private void handleCancel(ActionEvent event) {
        System.out.println("No action taken");
        switchScene(listScene, event);
    }
}