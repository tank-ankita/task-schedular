import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Class to represent each Task
class Task {
    int id;
    int priority;
    int epicId;
    String description;
    String assignedUser;
    List<String> dependencies; // Task dependencies

    public Task(int id, int epicId, String description, int priority, List<String> dependencies, String assignedUser) {
        this.id = id;
        this.epicId = epicId;
        this.priority = priority;
        this.description = description;
        this.assignedUser = assignedUser;
        this.dependencies = dependencies != null ? dependencies : new ArrayList<>();
    }
}


class Developer {
    int id;
    String name;

    public Developer(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

// Min-Heap-based Priority Queue for Task management
class TaskPriorityQueue {
    private List<Task> heap;

    public TaskPriorityQueue() {
        heap = new ArrayList<>();
    }

    // Function to get the index of the parent of a node
    private int parent(int i) {
        return (i - 1) / 2;
    }

    // Function to get the index of the left child of a node
    private int leftChild(int i) {
        return 2 * i + 1;
    }

    // Function to get the index of the right child of a node
    private int rightChild(int i) {
        return 2 * i + 2;
    }

    // Function to insert a task into the heap
    public void insert(Task task) {
        heap.add(task);
        int current = heap.size() - 1;

        // Bubble up the new task to maintain the heap property
        while (current > 0 && heap.get(parent(current)).priority > heap.get(current).priority) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    // Function to remove and return the task with the highest priority (root)
    public Task extractMin() {
        if (heap.isEmpty()) {
            System.out.println("No tasks in the queue.");
            return null;
        }

        // Get the root task (highest priority)
        Task minTask = heap.get(0);
        Task lastTask = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, lastTask);
            heapify(0);
        }

        return minTask;
    }

    // Function to maintain the heap property after extracting the minimum element
    private void heapify(int i) {
        int left = leftChild(i);
        int right = rightChild(i);
        int smallest = i;

        if (left < heap.size() && heap.get(left).priority < heap.get(smallest).priority) {
            smallest = left;
        }

        if (right < heap.size() && heap.get(right).priority < heap.get(smallest).priority) {
            smallest = right;
        }

        if (smallest != i) {
            swap(i, smallest);
            heapify(smallest);
        }
    }

    // Function to swap two elements in the heap
    private void swap(int i, int j) {
        Task temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    // Function to check if the heap is empty
    public boolean isEmpty() {
        return heap.isEmpty();
    }
}

// Task Scheduler that integrates the custom Priority Queue
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
    public void addTask(int epicId, String description, int priority, List<String> dependencies, String assignedUser) {
        if (!userTasks.containsKey(assignedUser)) {
            printFormattedOutput("User not found. Adding '"+ assignedUser+ "' to the team.");
            addUser(assignedUser);
        }

        int currentTasksListSize = taskMap.size() + 1;

        Task newTask = new Task(currentTasksListSize, epicId, description, priority, dependencies, assignedUser);
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
        printFormattedOutput("Task ID: " + task.id + " || Epic ID: " + task.epicId + " || Description: " + task.description + " || Priority: " + task.priority);
    }

    // not done
    public void removeTask(int taskId) {
        if(!(taskMap.containsKey(taskId))) {
            printFormattedOutput("Task not found");
            return;
        }

        // TODO: this task should also be removed from the taskQueue which will be replaced by MinPQ
        taskMap.remove(taskId);
        printFormattedOutput("Task with id " +taskId + " removed");
    }

    // done
    public void searchEpic(int epidId) {
        for(Task task: taskMap.values()) {
            if(task.epicId == epidId) {
                printFormattedOutput("Epic ID: " + task.epicId + " || Task ID: " + task.id + " || Description: " + task.description + " || Priority: " + task.priority);
            }
        }
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
            printFormattedOutput("Task ID: " + task.id + " || Epic ID: " + task.epicId + " || Description: " + task.description + " || Priority: " + task.priority);
        }
    }

    // not done
    public void executeTask() {
//        if (taskQueue.isEmpty()) {
//            System.out.println("No tasks to execute.");
//            return;
//        }
//
//        Task taskToExecute = taskQueue.extractMin();
//        String assignedUser = taskToExecute.assignedUser;
//
//        // Check if dependencies are met
//        boolean dependenciesMet = true;
//        for (String dependency : taskToExecute.dependencies) {
//            if (!completedTasks.get(assignedUser).contains(dependency)) {
//                dependenciesMet = false;
//                System.out.println("Task " + taskToExecute.id + " cannot be executed. Dependency " + dependency + " not completed.");
//                taskQueue.insert(taskToExecute); // Re-insert the task to check again later
//                return;
//            }
//
//        }
//
//        if (dependenciesMet) {
//            System.out.println("Executing Task: " + taskToExecute.id + " for user " + assignedUser);
//            completedTasks.get(assignedUser).add(taskToExecute.id); // Mark task as completed
//        }
    }

    // not done
    public void showCompletedTasks(String userId) {
        if (!completedTasks.containsKey(userId)) {
            System.out.println("Developer not found.");
            return;
        }
        printFormattedOutput("Completed tasks for user " + userId + ":");
        for (Integer taskId : completedTasks.get(userId)) {
            printFormattedOutput("Task ID: " + taskId);
        }
    }
}
