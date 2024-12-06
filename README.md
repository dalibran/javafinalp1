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
members in a team lead capacity would review the pull requests to ensure nothing important would be deleted as new code was merged.
2. Using the server lecture as a baseline, converting the functionality to pass objects back and forth was initially very challenging.
It took a while to figure out the cadence between sending objects from the client and receiving them on the server. Where you define your
readObject() and writeObject() has to be precise, otherwise you'll get unexpected results when passing objects back and forth.
3. Switching views in JavaFX was also very challenging, and it took a lot of trial and error to discover that using a single controller
to manage all the views was impractical. Moving to individual controllers for each view had a big impact on ease of implementation. Also,
defining scenes for each view within TaskApplication made it significantly easier to switch views, and the resulting implementation
was easier to read and maintain.

## Anything Not Working?

1. The JavaFX views currently don't work and haven't been connected to the event handler. The data, data storage, client, and
server classes have all been implemented, so the event handler class (TaskController) can pass requests to the client, and those
requests are returned accurately. However, as of now, that information isn't presented in the JavaFX view.
