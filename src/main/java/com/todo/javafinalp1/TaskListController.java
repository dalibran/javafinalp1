package com.todo.javafinalp1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import java.io.IOException;
import java.util.ArrayList;

public class TaskListController extends Controller {
    @FXML
    private ListView<HBox> taskListView;
    private static Scene addScene;
    private static Scene editScene;
    private TaskService client;
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

    public static void setEditScene(Scene scene) {
        editScene = scene;
    }

    @FXML
    public void initialize() {
        // Populate the ListView with existing tasks from getTaskList()
        handleRefreshTaskList();
    }

    @FXML
    private void openTaskDetail(int taskId, MouseEvent event) throws IOException {
        Task task = TaskService.getTask(taskId);
        System.out.println("Task title " + task.getTitle());
        TaskEditContext.getInstance().setCurrentTask(task);
        TaskEditContext.getInstance().setCurrentTaskId(taskId);
        System.out.println(TaskEditContext.getInstance().getCurrentTask());

        // Handle Clicking on Task
        System.out.println("Edit Task button clicked! Navigate to the edit Task view.");
        dynamicallyLoadEditView(event);
    }

    @FXML
    private void handleAddTask(ActionEvent event) {
        System.out.println("Add task button clicked");
        switchScene(addScene, event);
    }

    @FXML
    protected void handleRefreshTaskList() {
        taskListView.getItems().clear();
        ArrayList<TaskPreview> taskList = TaskService.getTaskList();
        System.out.println("Got tasklist");

        // Iterate through available tasks in tasklist
        for (TaskPreview task : taskList) {
            String dueDate;
            if (task.getDueDate() != null) {
                dueDate = task.getDueDate().toString();
            } else {
                dueDate = "";
            }

            // Build task list item
            HBox taskItem = createTaskItem(task.getTitle(), task.getCurrentStatus().toString(), dueDate, task.getTaskId());

            // Allow individual tasks to be opened via mouse click
            taskItem.setOnMouseClicked(event -> {
                try {
                    openTaskDetail(task.getTaskId(), event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
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

    private void dynamicallyLoadEditView(MouseEvent event) throws IOException {
        Parent editView = FXMLLoader.load(getClass().getResource("TaskEditView.fxml"));
        Scene editScene = new Scene(editView, 600, 500);
        setEditScene(editScene);
        switchScene(editScene, event);
    }
}