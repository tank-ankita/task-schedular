// Ankita Tank
// Project - Task Scheduler
// November 2024

import java.util.Scanner;
// Main class to run the Task Scheduler
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskScheduler scheduler = new TaskScheduler();

        while (true) {
            // Infinite loop to continuously prompt user for actions
            // Display the menu in a formatted table
            System.out.println();
            System.out.println("------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-10s %-35s %-30s%n", "Option", "Description",                 "Expected Input Format");
            System.out.println("------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-10s %-35s %-30s%n", "1", "Add Developer",                    "1 DeveloperName");
            System.out.printf("%-10s %-35s %-30s%n", "2", "Create New Task",                  "2 DeveloperName, Task Description, Priority, Dependency");
            System.out.printf("%-10s %-35s %-30s%n", "3", "Execute Task by ID",               "3 TaskId");
            System.out.printf("%-10s %-35s %-30s%n", "4", "Execute All Tasks",                "4");
            System.out.printf("%-10s %-35s %-30s%n", "5", "Show Developer Completed Tasks",   "5 DeveloperName");
            System.out.printf("%-10s %-35s %-30s%n", "6", "Search Task by ID",                "6 TaskId");
            System.out.printf("%-10s %-35s %-30s%n", "7", "Show Tasks Assigned to Developer", "7 DeveloperName");
            System.out.printf("%-10s %-35s %-30s%n", "8", "Exit the Program",                 "8");
            System.out.println("------------------------------------------------------------------------------------------------------------------");
            System.out.println();

            // Read the entire input line and split into choice and parameters
            // Split into choice and the rest of the input
            String inputLine = scanner.nextLine();
            String[] inputParts = inputLine.split(" ", 2);

            int choice = Integer.parseInt(inputParts[0]); // Parse the choice from the input
            String parameters = inputParts.length > 1 ? inputParts[1] : ""; // Get parameters if available

            switch (choice) {
                case 1:
                    // Case 1: Add a new developer
                    scheduler.addUser(parameters.trim()); // Add developer using trimmed parameters
                    break;

                case 2:
                    // Case 2: Create a new task
                    String[] taskParts = parameters.split(",", 4); // Split parameters into task details
                    if (taskParts.length < 4) { // Check if all necessary parts are provided
                        System.out.println("Invalid input for creating a task. Please try again.");
                        break;
                    }

                    // Extract task details
                    String assignedUser = taskParts[0].trim();
                    String description = taskParts[1].trim();
                    int priority = Integer.parseInt(taskParts[2].trim());
                    int dependency = Integer.parseInt(taskParts[3].trim());

                    // Add the task to the scheduler
                    scheduler.addTask(description, priority, dependency, assignedUser);
                    break;

                case 3:
                    // Case 3: Execute a specific task by ID
                    scheduler.executeTaskById(Integer.parseInt(parameters.trim()));
                    break;

                case 4:
                    // Case 4: Execute all tasks in the scheduler
                    System.out.println("Executing all Tasks...");
                    scheduler.executeAllTasks();
                    break;

                case 5:
                    // Case 5: Show completed tasks for a developer
                    scheduler.showCompletedTasks(parameters.trim());
                    break;

                case 6:
                    // Case 6: Search for a task by its ID
                    scheduler.searchTask(Integer.parseInt(parameters.trim()));
                    break;

                case 7:
                    // Case 7: Show all tasks assigned to a developer
                    scheduler.showUserTasks(parameters.trim());
                    break;

                case 8:
                    // Case 8: Exit the program
                    System.out.println("Thank you for using Task Scheduler...");
                    return;

                default:
                    // Handle invalid menu choices
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
