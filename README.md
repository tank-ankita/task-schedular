# <p align="left">Task Scheduler </p>

## Overview
The Task Scheduler is a command-line application that allows users and developers to manage tasks efficiently. It features task prioritization, dependency management, and a simple interface for executing tasks and tracking completion. This project is designed for ease of use and helps streamline task assignments for a team of developers.


## Table of Contents

- Getting Started
- Install Dependencies
- Features
- Project Structure
- Usage
- Developer Guide
- Testing
- Contributing

## Getting Started

**Prerequisites**\
Ensure you have the following installed:

- Java Development Kit (JDK) 8 or higher: Download JDK
- IntelliJ IDEA
- JUnit 4.13.2 and Hamcrest Core 1.3 for testing


##  Install Dependencies
1. Clone the Repository
```bash
git clone https://github.com/tank-ankita/task-schedular.git
```
2. Open the Project in IntelliJ:

- Launch IntelliJ IDEA and select "Open".
- Navigate to the project directory and open it.

3. Set Up JUnit:

- Right-click the project in IntelliJ and select "Add Framework Support".
- Check JUnit and download it if prompted.

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

| Functioanlity| Command |  Example|
| -------- | -------- | -------- |
| Add Developer   | 1 DeveloperName   | 1 John    |
| Create New Task    | 2 DeveloperName, Task Description, Priority, Dependency    | 2 John, Fix Bug, 1, 0    |
| Execute Task by ID    | 3 TaskId    | 3 1    |
| Execute All Tasks    | 4    | 4    |
| Show Developer Completed Tasks    | 5 DeveloperName   | 5 John   |
| Search Task by ID    | 6 TaskId    | 6 1    |
| Show Tasks Assigned to Developer    | 7 DeveloperName    | 7 John    |
| Exit the Program    | 8    | 8|



## Developer Guide
**Setting Up the Project**
- Add JUnit to Your Project: Follow the setup instructions to add JUnit and Hamcrest to your classpath.
- Compile the Code: Use your IDE’s build tool or run:

```
javac -cp .:junit-4.13.2.jar:hamcrest-core-1.3.jar src/main/java/org/scheduler/*.java
```

- Run the Application: Open Main.java and run it.

**Code Explanation**

- TaskScheduler.java: Manages tasks, developers, and the logic for executing tasks based on dependencies and priorities.
- Task.java: Represents a task with fields like id, priority, description, developer, and dependency.
- Developer.java: Represents a developer with fields id and name.
- Main.java: The application's entry point that provides a menu-based interface for users.

## Testing
**Running Tests Using IntelliJ**

- Right-click on the `test` folder and select "Run All Tests".

**Test Coverage**

- DeveloperTest.java: Tests adding and managing developers.
- TaskTest.java: Tests creating tasks and checking their properties.
- TaskSchedulerTest.java: Tests core functionality like task execution, dependency handling, and searching.



## Contributing

1. Fork the Project: Create a fork of the repository.
2. Create your feature branch ```git checkout -b feature/YourFeature```
3. Commit Your Changes: ```git commit -m "Add YourFeature"```
4. Push to the Branch:```git push origin feature/YourFeature```
5. Open a Pull Request: Submit your changes for review against the `main` branch.

    