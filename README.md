# CS56 Final Project - Group B
> A task management app written in Java and JavaFX.

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![JavaFX](https://img.shields.io/badge/javafx-%23FF0000.svg?style=for-the-badge&logo=javafx&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)

Our task management app allows users to create tasks that contain titles, descriptions, due dates, 
creation dates, and comments. Users can view a list of their tasks, create tasks,
edit tasks, and delete tasks.

## Group Members

- Dana Alibrandi
- Luis Ramirez 
- Austin Lo 
- Jesse Guadron 
- Georgios Mainieri

## Java Version & IDE

- Java Version: OpenJDK v23.0.1 
- IDE: IntelliJ IDEA

## Running the Application

After cloning the repo to your local machine and opening the project in your IDE, you first want to start the server.

- Run the Main() in Server.java

Then, you can start the JavaFX views.

- Run the Main() TaskApplication.java

## Challenges

1. Collaborating on a shared codebase was a significant challenge, as most of us didn't have experience doing so. This
 was eventually mitigated by using Github and learning about feature branches and pull requests. One of our group
members acted as a team lead and would review pull requests to ensure nothing important would be deleted as new code was merged.
2. Using the lecture as a baseline, converting the server functionality to pass objects back and forth was initially very challenging.
It took a while to figure out the cadence between sending objects from the client and receiving them on the server. Where you define your
readObject() and writeObject() methods has to be precise, otherwise you'll get unexpected results when passing objects back and forth.
3. Switching views in JavaFX was also very challenging, and it took a lot of trial and error to discover that using a single controller
to manage all the views was impractical. Moving to individual controllers for each view had a big impact on ease of implementation. Also,
defining scenes for each view within TaskApplication made it significantly easier to switch views, and the resulting implementation
was easier to read and maintain.
4. When running a JavaFX app, all views initialize at runtime, which was a problem when attempting to edit a task. At runtime, we don't know
which task we want to edit, so we can't initialize that view with the correct information. Eventually, we had to dynamically load
the TaskEditView upon clicking a task, which allowed us to initialize the view properly.
5. Originally, there was no way for TaskEditController to know which task a user chose from TaskListView to edit. When the app switched JavaFX views,
we didn't know how to pass the desired task information so the view could initialize properly. Eventually, we 
created a new class TaskEditContext, which uses the singleton design pattern and acts as a medium to share
Task details between the TaskListView and the TaskDetailView. 

## Anything Not Working?

1. We didn't have time to implement comments within the JavaFX UI, although the underlying functionality exists and works
correctly on the backend.
