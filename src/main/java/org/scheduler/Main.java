package org.scheduler;
// Ankita Tank
// Project - Task Scheduler
// November 2024

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
// Main class to run the Task Scheduler
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskScheduler scheduler = new TaskScheduler();

        while (true) {
            System.out.println();
            System.out.println("----------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-10s %-35s %-30s%n", "Option", "Description", "Expected Input Format");
            System.out.println("----------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-10s %-35s %-30s%n", "1", "Add Developer", "1 DeveloperName");
            System.out.printf("%-10s %-35s %-30s%n", "2", "Create New Task", "2 DeveloperName, TaskDescription, Priority, Dependency1 Dependency2...");
            System.out.printf("%-10s %-35s %-30s%n", "3", "Execute Task by ID", "3 TaskId");
            System.out.printf("%-10s %-35s %-30s%n", "4", "Execute All Tasks", "4");
            System.out.printf("%-10s %-35s %-30s%n", "5", "Show Developer Completed Tasks", "5 DeveloperName");
            System.out.printf("%-10s %-35s %-30s%n", "6", "Search Task by ID", "6 TaskId");
            System.out.printf("%-10s %-35s %-30s%n", "7", "Show Tasks Assigned to Developer", "7 DeveloperName");
            System.out.printf("%-10s %-35s %-30s%n", "8", "List All Tasks", "8");
            System.out.printf("%-10s %-35s %-30s%n", "9", "Read Commands from File", "9 FilePath");
            System.out.printf("%-10s %-35s %-30s%n", "10", "Exit the Program", "10");
            System.out.println("----------------------------------------------------------------------------------------------------------------------");
            System.out.println();

            String inputLine = scanner.nextLine();
            String[] inputParts = inputLine.split(" ", 2); // Split into choice and the rest of the input

            try {
                int choice = Integer.parseInt(inputParts[0]); // Parse the choice from the input
                String parameters = inputParts.length > 1 ? inputParts[1] : ""; // Get parameters if available

                switch (choice) {
                    case 1:
                        // Case 1: Add Developer
                        scheduler.addUser(parameters.trim());
                        break;
                    case 2:
                        // Case 2: Create New Task
                        String[] taskParts = parameters.split(",", 4);
                        if (taskParts.length < 4) {
                            System.out.println("Invalid input for creating a task. Please try again.");
                            break;
                        }
                        String assignedUser = taskParts[0].trim();
                        String description = taskParts[1].trim();
                        int priority = Integer.parseInt(taskParts[2].trim());
                        String[] dependencyParts = taskParts[3].trim().split(" ");
                        List<Integer> dependencies = new ArrayList<>();
                        for (String dep : dependencyParts) {
                            dependencies.add(Integer.parseInt(dep.trim()));
                        }

                        scheduler.addTask(description, priority, dependencies, assignedUser);
                        break;
                    case 3:
                        // Case 3: Execute Task
                        scheduler.executeTaskById(Integer.parseInt(parameters.trim()));
                        break;
                    case 4:
                        // Case 4: Execute All Tasks
                        System.out.println("Executing all Tasks...");
                        scheduler.executeAllTasks();
                        break;
                    case 5:
                        // Case 5: Show Developer Completed Tasks
                        scheduler.showCompletedTasks(parameters.trim());
                        break;
                    case 6:
                        // Case 6: Search Task by ID
                        scheduler.searchTask(Integer.parseInt(parameters.trim()));
                        break;
                    case 7:
                        // Case 7: Show User Tasks
                        scheduler.showUserTasks(parameters.trim());
                        break;
                    case 8:
                        // Case 8: Read Commands from File
                        scheduler.showAllTasks();
                        break;
                    case 9:
                        // Case 9: Show User Tasks
                        readCommandsFromFile(parameters.trim(), scheduler);
                        break;
                    case 10:
                        // Case 9: Exit
                        System.out.println("Thank you for using Task Scheduler...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                // Handle invalid input
                System.out.println("Invalid input. Please enter a valid option and try again.");
            }
        }
    }

    // Method to read commands from a file
    private static void readCommandsFromFile(String filePath, TaskScheduler scheduler) {
        String filePrefix = "src/main/java/org/scheduler/inputs/";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePrefix.concat(filePath)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Simulate entering commands from the file
                System.out.println("----------------------------------------------------------------------------------------------------------------------");
                System.out.println("Executing command: " + line);
                // You can split and execute commands similar to how you handle command line input
                String[] inputParts = line.split(" ", 2);
                int choice = Integer.parseInt(inputParts[0]);
                String parameters = inputParts.length > 1 ? inputParts[1] : "";

                // Repeat the same logic for handling choices
                switch (choice) {
                    case 1:
                        scheduler.addUser(parameters.trim());
                        System.out.println();
                        break;
                    case 2:
                        String[] taskParts = parameters.split(",", 4);
                        if (taskParts.length < 4) {
                            System.out.println("Invalid input for creating a task. Please try again.");
                            break;
                        }
                        String assignedUser = taskParts[0].trim();
                        String description = taskParts[1].trim();
                        int priority = Integer.parseInt(taskParts[2].trim());
                        String[] dependencyParts = taskParts[3].trim().split(" ");
                        List<Integer> dependencies = new ArrayList<>();
                        for (String dep : dependencyParts) {
                            dependencies.add(Integer.parseInt(dep.trim()));
                        }
                        scheduler.addTask(description, priority, dependencies, assignedUser);
                        System.out.println();
                        break;
                    case 3:
                        scheduler.executeTaskById(Integer.parseInt(parameters.trim()));
                        System.out.println();
                        break;
                    case 4:
                        scheduler.executeAllTasks();
                        System.out.println();
                        break;
                    case 5:
                        scheduler.showCompletedTasks(parameters.trim());
                        System.out.println();
                        break;
                    case 6:
                        scheduler.searchTask(Integer.parseInt(parameters.trim()));
                        System.out.println();
                        break;
                    case 7:
                        scheduler.showUserTasks(parameters.trim());
                        System.out.println();
                        break;
                    case 8:
                        scheduler.showAllTasks();
                        System.out.println();
                        break;
                    default:
                        System.out.println("Invalid command in file. Skipping...");
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid command format in file. Skipping...");
        }
    }
}
