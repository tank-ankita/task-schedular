## Pre-Requisites

**IMPORTANT:** \
Before proceeding with the following commands, ensure that you have followed the project installation guide thoroughly and verified that your project is running without any errors. Once your setup is complete, run the Main.java file to proceed with the commands outlined below.

----------
## Features and How to Use Them

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
    - TaskDescription: A brief description of the task enclosed in quotes.
    - Priority: A number from 1 to 5, where 1 is the highest priority and 5 is the lowest.
    - Dependency: The ID of another task this task depends on. Use 0 if there is no dependency.
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

**8. Exit the Program**\
Purpose: To exit the application.

- Input Format: 8
- Instructions: Enter 8 to close the application.


----------


### Notes on Task Management

- **Priorities**: Use priority levels wisely to ensure that the most critical tasks are completed first.
- **Dependencies**: When creating tasks with dependencies, ensure that the dependent tasks are executed in the correct order.
- **Error Handling**: The application provides error messages for invalid input, unresolved dependencies, or if a developer or task is not found.


----------


### Example User flow
1. Adding Developers and Creating Tasks:
    - Input: 1 Tank
    - Input: 2 Tank, Develop Feature X, 2, 0
    - Input: 2 Tank,Fix Bug Y, 1, 1

2. Executing Tasks:

    - Input: 3 1  (Executes "Fix Bug Y" if dependencies are resolved)
    - Input: 4   (Attempts to execute all tasks)

3. Searching and Viewing Tasks:
- Input: 6 1 (Searches for task with ID 1)
- Input: 7 Tank (Shows all tasks assigned to Tank)
- Input: 5 Tank (Shows all completed tasks for Tank)


----------


### Troubleshooting

- Invalid Input: If you enter an incorrect command or input format, the application will display an error message and prompt you to try again.
- Unresolved Dependencies: Tasks with unresolved dependencies cannot be executed until their dependencies are completed. Manage dependencies carefully to ensure smooth task execution.
