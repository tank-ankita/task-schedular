package org.scheduler;// Ankita Tank
// Project - Task Scheduler
// November 2024

// Class to represent each Task in the Task Scheduler
public class Task implements Comparable<Task> {
    // Unique identifier for the task
    int id;

    // Priority of the task, where a lower number indicates higher priority
    int priority;

    // Description of the task for better understanding of what needs to be done
    String description;

    // Developer assigned to the task
    String developer;

    // Dependency of the task, represented by the ID of another task that must be completed first
    int dependency;

    // Constructor to initialize a new Task object with the given properties
    public Task(int id, String description, int priority, int dependency, String developer) {
        this.id = id;
        this.priority = priority;
        this.description = description;
        this.developer = developer;
        this.dependency = dependency;
    }

    // Method to compare tasks based on their priority
    // Returns a negative number if this task has a higher priority than the other task,
    // zero if they have the same priority, or a positive number if this task has a lower priority
    @Override
    public int compareTo(Task other) {
        return Integer.compare(this.priority, other.priority);
    }
}
