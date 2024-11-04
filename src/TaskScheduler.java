// Ankita Tank
// Project - Task Scheduler
// November 2024

import stdlib.MinPQ;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Comparator;

// Class to manage task scheduling and execution
class TaskScheduler {
    // Maps to store task details, developer list, and tasks for each developer
    private final HashMap<Integer, Task> taskMap;
    private final HashMap<String, Integer> developerList;
    private final HashMap<String, List<Integer>> userTasks;
    private final HashMap<String, List<Integer>> completedTasks;

    // Priority queue to handle task execution order
    private final MinPQ<Task> taskQueue;

    // Constructor to initialize data structures
    public TaskScheduler() {
        taskMap = new HashMap<>();
        userTasks = new HashMap<>();
        developerList = new HashMap<>();
        completedTasks = new HashMap<>();
        taskQueue = new MinPQ<>(Comparator.comparingInt(task -> task.priority));
    }

    // Utility method to format and print output
    private void printFormattedOutput(String output) {
        System.out.println("=> " + output);
    }

    // Method to add a new developer
    public void addUser(String name) {
        if (developerList.containsKey(name)) {
            printFormattedOutput("User already exists");
            return;
        }

        // Create and add new developer
        int userListCount = developerList.size() + 1;
        Developer newDeveloper = new Developer(userListCount, name);
        developerList.put(name, userListCount);

        // Initialize tasks lists for the developer
        userTasks.putIfAbsent(name, new ArrayList<>());
        completedTasks.putIfAbsent(name, new ArrayList<>());

        printFormattedOutput("'" + newDeveloper.name + "' has been added to the team with id: '" + newDeveloper.id + "'");
    }

    // Method to add a new task and assign it to a developer
    public void addTask(String description, int priority, int dependency, String assignedUser) {
        if (!userTasks.containsKey(assignedUser)) {
            printFormattedOutput("User not found. Adding '" + assignedUser + "' to the team.");
            addUser(assignedUser); // Automatically add user if they don't exist
        }

        // Create and add the task to the system
        int currentTasksListSize = taskMap.size() + 1;
        Task newTask = new Task(currentTasksListSize, description, priority, dependency, assignedUser);
        taskMap.put(currentTasksListSize, newTask);
        userTasks.get(assignedUser).add(currentTasksListSize);
        taskQueue.insert(newTask); // Insert task into the priority queue

        printFormattedOutput("Task " + currentTasksListSize + " added for user '" + assignedUser + "'.");
    }

    // Method to search for a task by its ID and display details
    public void searchTask(int taskId) {
        if (!taskMap.containsKey(taskId)) {
            printFormattedOutput("Task not found");
            return;
        }

        Task task = taskMap.get(taskId);
        printFormattedOutput("Task ID: " + task.id + " || Description: " + task.description +
                " || Developer: " + task.developer + " || Priority: " + task.priority);
    }

    // Method to show pending tasks assigned to a developer
    public void showUserTasks(String userName) {
        if (!userTasks.containsKey(userName)) {
            printFormattedOutput("Team member not found.");
            return;
        }

        printFormattedOutput("Pending tasks for developer " + userName + ":");
        for (Integer taskId : userTasks.get(userName)) {
            Task task = taskMap.get(taskId);
            printFormattedOutput("Task ID: " + task.id + " || Description: " + task.description +
                    " || Priority: " + task.priority);
        }
    }

    // Method to show completed tasks for a developer
    public void showCompletedTasks(String devName) {
        if (!completedTasks.containsKey(devName)) {
            printFormattedOutput("Developer not found.");
            return;
        }

        List<Integer> tasks = completedTasks.get(devName);
        if (tasks.isEmpty()) {
            printFormattedOutput("No tasks completed by " + devName + ".");
            return;
        }

        printFormattedOutput("Completed tasks for user " + devName + ":");
        for (Integer taskId : tasks) {
            Task task = taskMap.get(taskId);
            printFormattedOutput("Task ID: " + task.id + " || Description: " + task.description);
        }
    }

    // Method to execute all tasks in the priority order, resolving dependencies
    public void executeAllTasks() {
        boolean allDependenciesResolved = true;

        while (!taskQueue.isEmpty()) {
            List<Task> incompleteTasks = new ArrayList<>();
            boolean taskExecutedInThisPass = false;

            // Attempt to execute tasks in order of priority
            while (!taskQueue.isEmpty()) {
                Task task = taskQueue.delMin();
                if (areDependenciesResolved(task)) {
                    markTaskAsCompleted(task);
                    taskExecutedInThisPass = true; // Mark if a task was executed
                } else {
                    incompleteTasks.add(task); // Store tasks with unresolved dependencies
                }
            }

            // Check if any tasks were executed in this pass
            if (!taskExecutedInThisPass) {
                allDependenciesResolved = false;
                break; // Exit loop if dependencies prevent further execution
            }

            // Re-add incomplete tasks for another pass
            for (Task task : incompleteTasks) {
                taskQueue.insert(task);
            }
        }

        if (allDependenciesResolved) {
            printFormattedOutput("All possible tasks have been executed successfully.");
        } else {
            printFormattedOutput("Some tasks could not be executed due to unresolved dependencies.");
        }
    }

    // Method to execute a specific task by ID, checking dependencies
    public void executeTaskById(int taskId) {
        if (!taskMap.containsKey(taskId)) {
            printFormattedOutput("Task not found.");
            return;
        }

        Task task = taskMap.get(taskId);
        if (!areDependenciesResolved(task)) {
            printFormattedOutput("Cannot execute task " + taskId + " due to unresolved dependencies.");
            return;
        }

        markTaskAsCompleted(task);
    }

    // Helper method to get a task by its ID
    private Task getTaskById(int taskId) {
        return taskMap.getOrDefault(taskId, null);
    }

    // Method to check if all dependencies of a task are resolved
    private boolean areDependenciesResolved(Task task) {
        int dependencyId = task.dependency;
        if (dependencyId == 0) return true; // No dependencies

        Task dependentTask = getTaskById(dependencyId);
        if (dependentTask == null || !completedTasks.get(dependentTask.developer).contains(dependencyId)) {
            return false; // Dependency not completed
        }
        return true;
    }

    // Method to mark a task as completed
    private void markTaskAsCompleted(Task task) {
        userTasks.get(task.developer).remove(Integer.valueOf(task.id));
        completedTasks.get(task.developer).add(task.id);
        printFormattedOutput("Task " + task.id + " executed successfully.");
    }
}
