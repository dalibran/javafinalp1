package com.todo.javafinalp1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import java.time.LocalDate;

public class TaskEditController {

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField titleField;  // TextField for the task title

    @FXML
    private TextArea descriptionArea;  // TextArea for the task description

    @FXML
    private ChoiceBox<String> statusChoiceBox;  // ChoiceBox for selecting task status

    @FXML
    private DatePicker dueDatePicker;  // DatePicker for selecting the task's due date

    private Task taskToEdit;

    // Handle the Save button click
    @FXML
    private void handleSaveTask() {
        // Retrieve values from the form fields
        String taskTitle = titleField.getText();
        String taskDescription = descriptionArea.getText();
        String taskStatus = statusChoiceBox.getValue();  // Get the selected status

        // Convert task status to uppercase to match enum constants
        Status status = Status.valueOf(taskStatus.toUpperCase());  // Ensure uppercase

        LocalDate dueDate = dueDatePicker.getValue();

        // Build the updated task object
        Task updatedTask = new Task.Builder(taskTitle)
                .description(taskDescription)
                .currentStatus(taskStatus)  // Use the string status for the builder, or set it as needed
                .dueDate(dueDate)
                .build();

        // Use TaskService to update the task on the server
        Task updatedTaskResponse = TaskService.updateTask(taskToEdit.getId(), updatedTask);

        // Check for update success and close the window if successful
        if (updatedTaskResponse != null) {
            System.out.println("Task updated successfully!");
        } else {
            System.out.println("Failed to update the task.");
        }

        // Close the current window after saving
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }

    // Handle the Cancel button click
    @FXML
    private void handleCancelTask() {
        // Close the window without saving
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    // Initialize the controller with the task to be edited
    public void initialize() {
        if (taskToEdit != null) {
            // Pre-fill the form with existing task data
            titleField.setText(taskToEdit.getTitle());
            descriptionArea.setText(taskToEdit.getDescription());
            statusChoiceBox.setValue(taskToEdit.getCurrentStatus().toString());
            dueDatePicker.setValue(taskToEdit.getDueDate());
        }
    }

    // Method to set the task being edited (called from the TaskListViewController or another controller)
    public void setTaskToEdit(Task task) {
        this.taskToEdit = task;
    }
}