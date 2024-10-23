import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class TaskScheduler {
    private final TaskPriorityQueue taskQueue;
    private final HashMap<Integer, Task> taskMap;
    private final HashMap<String, Integer> developerList;
    private final HashMap<String, List<Integer>> userTasks;
    private final HashMap<String, List<Integer>> completedTasks;

    public TaskScheduler() {
        taskMap = new HashMap<>();
        userTasks = new HashMap<>();
        developerList = new HashMap<>();
        completedTasks = new HashMap<>();
        taskQueue = new TaskPriorityQueue();
    }

    private void printFormattedOutput(String output) {
        System.out.println("=> " + output);
    }

    //done
    public void addUser(String name) {
        if(developerList.containsKey(name)) {
            printFormattedOutput("User already exists");
            return;
        }

        int userListCount = developerList.size() + 1;
        Developer newDeveloper = new Developer(userListCount, name);
        developerList.put(name, userListCount);

        userTasks.putIfAbsent(name, new ArrayList<>());
        completedTasks.putIfAbsent(name, new ArrayList<>());

        printFormattedOutput("'" +newDeveloper.name + "' has been added to the team with id: '" + newDeveloper.id + "'");
    }

    // done
    public void addTask(String description, int priority, List<String> dependencies, String assignedUser) {
        if (!userTasks.containsKey(assignedUser)) {
            printFormattedOutput("User not found. Adding '"+ assignedUser+ "' to the team.");
            addUser(assignedUser);
        }

        int currentTasksListSize = taskMap.size() + 1;

        Task newTask = new Task(currentTasksListSize, description, priority, dependencies, assignedUser);
        taskQueue.insert(newTask);
        taskMap.put(currentTasksListSize, newTask);
        userTasks.get(assignedUser).add(currentTasksListSize);
        printFormattedOutput("Task " + currentTasksListSize + " added for user '" + assignedUser + "'.");
    }

    // done
    public void searchTask(int taskId) {
        if(!(taskMap.containsKey(taskId))) {
            printFormattedOutput("Task not found");
            return;
        }

        Task task = taskMap.get(taskId);
        printFormattedOutput("Task ID: " + task.id + " || Description: " + task.description + " || Priority: " + task.priority);
    }

    // done
    public void showUserTasks(String userName) {
        if (!userTasks.containsKey(userName)) {
            printFormattedOutput("Team member not found.");
            return;
        }

        printFormattedOutput("Pending tasks for developer " + userName + ":");
        for (Integer taskId : userTasks.get(userName)) {
            Task task = taskMap.get(taskId);
            printFormattedOutput("Task ID: " + task.id + " || Description: " + task.description + " || Priority: " + task.priority);
        }
    }

    // not done
    public void executeTasks() {
        if (taskQueue.isEmpty()) {
            printFormattedOutput("No tasks to execute.");
            return;
        }

        Task taskToExecute = taskQueue.extractMin(); // Extract task with highest priority
        String assignedUser = taskToExecute.developer;

        // Check if all dependencies are met
        boolean dependenciesMet = true;
        for (String dependency : taskToExecute.dependencies) {
            int depTaskId = Integer.parseInt(dependency);
            if (!completedTasks.get(assignedUser).contains(depTaskId)) {
                dependenciesMet = false;
                printFormattedOutput("Task " + taskToExecute.id +
                        " cannot be executed. Dependency " + depTaskId + " not completed.");
                taskQueue.insert(taskToExecute); // Re-insert task to check again later
                return;
            }
        }

        // If all dependencies are met, execute the task
        printFormattedOutput("Executing Task: " + taskToExecute.id + " for user " + assignedUser);
        completedTasks.get(assignedUser).add(taskToExecute.id); // Mark task as completed

        // Remove task from user's pending task list
        userTasks.get(assignedUser).remove(Integer.valueOf(taskToExecute.id));
    }

    public void executeTaskById(int taskId) {
        // Check if the task exists in the task map
        if (!taskMap.containsKey(taskId)) {
            printFormattedOutput("Task " + taskId + " not found.");
            return;
        }

        Task taskToExecute = taskMap.get(taskId);
        String assignedUser = taskToExecute.developer;

        // Check if all dependencies are met
        boolean dependenciesMet = true;
        List<Integer> unmetDependencies = new ArrayList<>();

        for (String dependency : taskToExecute.dependencies) {
            int depTaskId = Integer.parseInt(dependency);

            // If the dependent task isn't marked as completed
            if (!completedTasks.getOrDefault(assignedUser, new ArrayList<>()).contains(depTaskId)) {
                dependenciesMet = false;
                unmetDependencies.add(depTaskId);  // Store unmet dependencies for reporting
            }
        }

        // If dependencies are not met, print a message and return
        if (!dependenciesMet) {
            printFormattedOutput("Task " + taskToExecute.id + " cannot be executed. " +
                    "Unmet dependencies: " + unmetDependencies);
            return;
        }

        // If all dependencies are met, execute the task
        printFormattedOutput("Executing Task: " + taskToExecute.id + " for user " + assignedUser);
        completedTasks.get(assignedUser).add(taskToExecute.id); // Mark task as completed

        // Remove task from user's pending task list
        userTasks.get(assignedUser).remove(Integer.valueOf(taskToExecute.id));
    }

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
}
