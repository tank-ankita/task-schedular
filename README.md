# <p align="left">Task Scheduler </p>

## Overview
The Task Scheduler is a command-line application that allows developers to manage tasks efficiently. It features task prioritization, dependency management, and a simple interface for executing tasks and tracking completion. This project is designed for ease of use and helps streamline task assignments for a team of developers.


## Table of Contents

- Getting Started
- Installation
- Testing
- Features
- Project Structure
- Usage
- Contributing

## Getting Started

**Prerequisites**\
Ensure you have the following installed:

- Java Development Kit (JDK) 8 or higher: Download JDK
- IntelliJ IDEA or any Java IDE
- JUnit 4.13.2 and Hamcrest Core 1.3 for testing



## Installation
**1. Clone the Repository**\
Clone the project using the following command:

``` bash 
git clone https://github.com/tank-ankita/task-schedular.git
```

**2. Open the Project in IntelliJ**

- Launch IntelliJ IDEA and select "Open".
- Navigate to the project directory and open it.
- You will see an MVN build prompt; proceed to build the project through this prompt.

**3. Build the Project**

- Go to File → Build Project in IntelliJ.

### Running the Application
**Using Terminal**

Navigate to the project directory:
``` bash 
cd task-schedular
cd src/main/java/org/scheduler/
java Main.java
```
**Using IDE**

1. Open `Main.java` located in `src/main/java/org/scheduler/Main.java.`
2. Click the Run/Play button in the top right corner of the IDE with `Main.java `file.

----------
## Testing
**Running Tests Using IntelliJ**

- Navigate to the `src/test/java` directory.
- Right-click on the `org.scheduler` directory and select `"Run Tests in org.scheduler"`.

**Running Tests Using Terminal with Maven**

**1. Install Maven (if you haven't already):**

``` bash 
brew install mvn
```

**2. Navigate to the Parent Directory:**
``` bash 
cd task-schedular
```

**3. Run All Tests:**

``` bash 
mvn clean test
```

**4. Run Individual Tests:**

- To run `DeveloperTest.java`:
``` bash 
mvn clean test -Dtest=org.scheduler.DeveloperTest
``` 

- To run `TaskTest.java`:
``` bash 
mvn clean test -Dtest=org.scheduler.TaskTest
```

- To run `TaskSchedulerTest.java`:
``` bash 
mvn clean test -Dtest=org.scheduler.TaskSchedulerTest
```

- To run `PerformanceTest.java`:
``` bash 
mvn clean test -Dtest=org.scheduler.PerformanceTest
```
---------

## Features


**1. Add Developer**\
Purpose: To add a new developer to the team.

- Input Format: 1 DeveloperName
- Example: 1 John
- Instructions: Enter 1 followed by the developer's name. The application will confirm the addition or notify you if the developer already exists.

**2. Create New Task**\
Purpose: To create a new task and assign it to a developer, set its priority, and specify any dependencies.

- Input Format: 2 DeveloperName,TaskDescription,Priority, Dependency
- Example: 2 John, Fix Bug, 1, 0
- Instructions:
    - DeveloperName: Name of the developer to whom the task is assigned.
    - TaskDescription: A brief description of the task.
    - Priority: A number from 1 to 5, where 1 is the highest priority and 5 is the lowest.
    - Dependencies: The IDs of another tasks this task depends on. Use 0 if there is no dependency.
- Note: Make sure to resolve dependencies for smooth task execution.

**3. Execute Task by ID**\
Purpose: To execute a specific task by providing its ID.

- Input Format: 3 TaskId
- Example: 3 1
- Instructions: Enter 3 followed by the task ID. The application will check for unresolved dependencies and either execute the task or display a message.

**4. Execute All Tasks**\
Purpose: To execute all tasks in order of priority, while resolving dependencies.

- Input Format: 4
- Instructions: Enter 4 to attempt to execute all tasks. The application will execute as many tasks as possible and inform you if any tasks cannot be executed due to unresolved dependencies.


**5. Show Developer Completed Tasks**\
Purpose: To view all completed tasks assigned to a specific developer.

- Input Format: 5 DeveloperName
- Example: 5 John
- Instructions: Enter 5 followed by the developer's name. The application will display a list of completed tasks or inform you if none have been completed.

**6. Search Task by ID**\
Purpose: To search for and display details of a specific task by its ID.

- Input Format: 6 TaskId
- Example: 6 1
- Instructions: Enter 6 followed by the task ID. The application will display task details or notify you if the task is not found.

**7. Show Tasks Assigned to Developer**\
Purpose: To view all pending tasks assigned to a specific developer.

- Input Format: 7 DeveloperName
- Example: 7 John
- Instructions: Enter 7 followed by the developer's name. The application will list all pending tasks or inform you if the developer has no tasks assigned.

**8. Show All Tasks**\
Purpose: To view all tasks in a formatted and organized way, including their ID, description, developer, priority, and dependencies.

- Input Format: 8
- Example: 8
- Instructions: Enter 8 to list all tasks currently in the system. The application will display each task with its details formatted for clarity.

**9. Read Commands from File**\
Purpose: To load and execute a series of commands from a specified .txt file, making task management more efficient.

- Input Format: 9 FileName.txt
- Example: 9 commands.txt
- Instructions: Enter 9 followed by the name of the text file (including the .txt extension). The file should contain commands in the same format as manual input, with each command on a new line. The application will read and execute each command from the file, simplifying repetitive tasks.

**10. Exit the Program**\
Purpose: To exit the application.

- Input Format: 10
- Instructions: Enter 10 to close the application.

## Project Structure

/src\
|── /main/java/org/scheduler\
│     |── Developer.java\
│     |── Task.java\
│     |── TaskScheduler.java\
│     |── Main.java\
│── /main/java/org/scheduler/inputs\
│     |── simple.txt\
│     |── dependency.txt\
│── /main/java/org/scheduler/stdlib\
│     |── (Standard input and utility classes)\
│── /test\
│     |── DeveloperTest.java\
│     |── PerformanceTest.java\
│     |── TaskTest.java\
│     |── TaskSchedulerTest.java\



## Usage
### Application Menu
The application presents a menu with the following options:

| Functionality                    | Command                                                   | Example                   |
|----------------------------------|-----------------------------------------------------------|---------------------------|
| Add Developer                    | 1 DeveloperName                                           | 1 Tank                    |
| Create New Task                  | 2 DeveloperName, Task Description, Priority, Dependencies | 2 Tank, Fix Bug, 1, 0 2 3 |
| Execute Task by ID               | 3 TaskId                                                  | 3 1                       |
| Execute All Tasks                | 4                                                         | 4                         |
| Show Developer Completed Tasks   | 5 DeveloperName                                           | 5 Tank                    |
| Search Task by ID                | 6 TaskId                                                  | 6 1                       |
| Show Tasks Assigned to Developer | 7 DeveloperName                                           | 7 Tank                    |
| Show All Tasks                   | 8                                                         | 8                         |
| Read Commands from File          | 9 TextFile.txt                                            | 9 commands.txt            |
| Exit the Program                 | 10                                                        | 10                        |


## Contributing

1. Fork the Project: Create a fork of the repository.
2. Create your feature branch ```git checkout -b feature/YourFeature```
3. Commit Your Changes: ```git commit -m "Add YourFeature"```
4. Push to the Branch:```git push origin feature/YourFeature```
5. Open a Pull Request: Submit your changes for review against the **main** branch.

    