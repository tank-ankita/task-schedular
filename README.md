# <p align="left">Task Scheduler </p>

## Overview
The Task Scheduler is a command-line application that allows users and developers to manage tasks efficiently. It features task prioritization, dependency management, and a simple interface for executing tasks and tracking completion. This project is designed for ease of use and helps streamline task assignments for a team of developers.



## Table of Contents

- Getting Started
- Installation
- Testing
- Features
- Project Structure
- Usage
- Developer Guide

- Contributing
- License


## Getting Started

**Prerequisites**\
Ensure you have the following installed:

- Java Development Kit (JDK) 8 or higher: Download JDK
- IntelliJ IDEA or any Java IDE
- JUnit 4.13.2 and Hamcrest Core 1.3 for testing


##  Installation
1. Clone the Repository
```bash
git clone https://github.com/tank-ankita/task-schedular.git
```
2. Open the Project in IntelliJ:

- Launch IntelliJ IDEA and select "Open".
- Navigate to the project directory and open it.
- you will see an MVN build prompt, go ahead and build the project through the prompt.

3. Build project. File →  Build Project

Using Terminal
```bash
cd task-schedular
cd src/main/java/org/scheduler/
java Main.java
```
Using IDE\
Open `Main.java` under directory `src/main/java/org/scheduler/Main.java`\
Click the `Play/Run button` at the Top right on the `Main.java` file

## Testing
**Running Tests Using IntelliJ**

- Under directory `src/test/java` Right-click on the `org.schdeuler` directory and select `Run Tests in org.schdeuler`

**Running Tests Using Terminal through MVN**

```bash
brew install mvn
```

Once Installed, move to the parent directory
```bash
cd/task-schedular

// to run all test run below code
mvn clean test 

// to run individual tests run below code
mvn clean test -Dtest=org.scheduler.DeveloperTest.java
mvn clean test -Dtest=org.scheduler.TaskTest.java
mvn clean test -Dtest=org.scheduler.TaskSchedulerTest.java
```

**Test Coverage**

- DeveloperTest.java: Tests creating developers and checking their properties.
- TaskTest.java: Tests creating tasks and checking their properties.
- TaskSchedulerTest.java: Tests core functionality like task execution, dependency handling, and searching.


## Features

- Add Developers: Easily add new developers to the team.
- Create Tasks: Assign tasks to developers, set priorities, and manage dependencies.
- Execute Tasks: Execute tasks individually or all simultaneously, with dependency checks.
- Search Tasks: Quickly search for tasks by ID or developer name.
- Track Completed Tasks: View tasks completed by developers.

## Project Structure

/src\
|── /main/java/org/scheduler\
│     |── Developer.java\
│     |── Task.java\
│     |── TaskScheduler.java\
│     |── Main.java\
|── /main/java/org/scheduler/stdlib\
│     |── (Standard input and utility classes)\
|── /test\
│     |── DeveloperTest.java\
│     |── TaskTest.java\
│     |── TaskSchedulerTest.java\



## Usage
### Application Menu
The application presents a menu with the following options:

| Functioanlity| Command                                                   | Example                   |
| -------- |-----------------------------------------------------------|---------------------------|
| Add Developer   | 1 DeveloperName                                           | 1 Tank                    |
| Create New Task    | 2 DeveloperName, Task Description, Priority, Dependencies | 2 Tank, Fix Bug, 1, 0 2 3 |
| Execute Task by ID    | 3 TaskId                                                  | 3 1                       |
| Execute All Tasks    | 4                                                         | 4                         |
| Show Developer Completed Tasks    | 5 DeveloperName                                           | 5 Tank                    |
| Search Task by ID    | 6 TaskId                                                  | 6 1                       |
| Show Tasks Assigned to Developer    | 7 DeveloperName                                           | 7 Tank                    |
| Exit the Program    | 8                                                         | 8                         |


## Developer Guide
**Setting Up the Project**
- Add JUnit to Your Project: Follow the setup instructions.
- Compile the Code: Use your IDE’s build tool or run:
- Run the Application: Open Main.java and run it.

**Code Explanation**

- TaskScheduler.java: Manages tasks, developers, and the logic for executing tasks based on dependencies and priorities.
- Task.java: Represents a task with fields like id, priority, description, developer, and dependency.
- Developer.java: Represents a developer with fields id and name.
- Main.java: The application's entry point that provides a menu-based interface for users.


## Contributing

1. Fork the Project: Create a fork of the repository.
2. Create your feature branch ```git checkout -b feature/YourFeature```
3. Commit Your Changes: ```git commit -m "Add YourFeature"```
4. Push to the Branch:```git push origin feature/YourFeature```
5. Open a Pull Request: Submit your changes for review against the **main** branch.

    