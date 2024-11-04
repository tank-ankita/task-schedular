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

        printFormattedOutput("'" + newDeveloper.name + "' has been added to the team.");
    }

    // Method to add a new task and assign it to a developer
    public void addTask(String description, int priority, int dependency, String developerName) {
        if (!userTasks.containsKey(developerName)) {
            printFormattedOutput("User not found. Please add developer " + developerName + " to the team");
            return;
        }

        // Create and add the task to the system
        int currentTasksListSize = taskMap.size() + 1;
        Task newTask = new Task(currentTasksListSize, description, priority, dependency, developerName);
        taskMap.put(currentTasksListSize, newTask);
        userTasks.get(developerName).add(currentTasksListSize);
        taskQueue.insert(newTask); // Insert task into the priority queue

        printFormattedOutput("New Task ID: " + currentTasksListSize + " || Description: " + description + " || Developer: " + developerName + " || Priority: " + priority + " || Dependency on TaskId: " + dependency);

    }

    // Method to search for a task by its ID and display details
    public void searchTask(int taskId) {
        Task task = getTaskById(taskId);
        if(task == null) {
            printFormattedOutput("Task not found");
            return;
        }
        printFormattedOutput("Task Found");
        printFormattedOutput("Task ID: " + task.id + " || Description: " + task.description + " || Developer: " + task.developer + " || Priority: " + task.priority + " || Dependency on TaskId: " + task.dependency);
    }

    // Method to show tasks assigned to a developer
    public void showUserTasks(String devName) {
        List<Integer> tasks = getTaskIdsByName(devName);
        if (tasks == null) {
            printFormattedOutput("Developer not found.");
            return;
        }

        if (tasks.isEmpty()) {
            printFormattedOutput("No pending tasks for developer " + devName + ".");
            return;
        }

        printFormattedOutput("Tasks for developer " + devName + ":");
        for (Integer taskId : tasks) {
            Task task = taskMap.get(taskId);
            printFormattedOutput("Task ID: " + task.id + " || Description: " + task.description + " || Priority: " + task.priority + " || Dependency on TaskId: " + task.dependency);
        }
    }

    // Method to show completed tasks for a developer
    public void showCompletedTasks(String devName) {
        if (!completedTasks.containsKey(devName)) {
            printFormattedOutput("Developer not found.");
            return;
        }

        List<Integer> tasks = getCompletedTasksByName(devName);
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
            printFormattedOutput("Cannot execute task " + taskId + " due to unresolved dependencies. Resolve task " + task.dependency + " and try again.");
            return;
        }

        markTaskAsCompleted(task);
    }

    // Method to check if all dependencies of a task are resolved
    public boolean areDependenciesResolved(Task task) {
        int dependencyId = task.dependency;
        if (dependencyId == 0) return true; // No dependencies

        Task dependentTask = getTaskById(dependencyId);
        return dependentTask != null && completedTasks.get(dependentTask.developer).contains(dependencyId);
    }

    // Method to mark a task as completed
    public void markTaskAsCompleted(Task task) {
        userTasks.get(task.developer).remove(Integer.valueOf(task.id));
        completedTasks.get(task.developer).add(task.id);
        printFormattedOutput("Task " + task.id + " executed successfully.");
    }

    // Method to get tasks by id
    public Task getTaskById(int taskId) {
        return taskMap.getOrDefault(taskId, null);
    }

    // Method to get completed tasks by developer name
    public List<Integer> getCompletedTasksByName(String devName) {
        return completedTasks.getOrDefault(devName, new ArrayList<>());
    }

    // Method to get tasks by developer name
    public List<Integer> getTaskIdsByName(String devName) {
        if (!userTasks.containsKey(devName)) { return null; }

        return userTasks.get(devName);
    }

    // Method to get list of all developers
    public HashMap<String, Integer> getDeveloperList() {
        return developerList;
    }

    // Method to get tasks all tasks
    public HashMap<Integer, Task> getTaskMap() {
        return taskMap;
    }

    // Method to get tasks by developer name as a map
    public HashMap<String, List<Integer>> getUserTasks() {
        return userTasks;
    }
}
