package org.scheduler;
// Ankita Tank
// Project - Task Scheduler
// November 2024

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TaskSchedulerTest {
    private TaskScheduler scheduler;

    @Before
    public void setUp() {
        scheduler = new TaskScheduler();
    }

    @Test
    public void testAddUser() {
        scheduler.addUser("Developer1");
        scheduler.addUser("Developer2");

        // Check if users are added correctly
        assertEquals(2, scheduler.getDeveloperList().size());
        assertTrue(scheduler.getDeveloperList().containsKey("Developer1"));
        assertTrue(scheduler.getDeveloperList().containsKey("Developer2"));

        // Attempt to add a duplicate user
        scheduler.addUser("Developer1");

        // Verify that the developer list size has not increased
        assertEquals(2, scheduler.getDeveloperList().size());
    }

    @Test
    public void testAddTask() {
        // First, add a developer
        scheduler.addUser("Developer1");

        // Add tasks for the developer
        scheduler.addTask("Fix Bug", 1, new ArrayList<>(List.of(0)), "Developer1");
        scheduler.addTask("Implement Feature", 3, new ArrayList<>(List.of(0)), "Developer1");

        // Check if tasks are correctly added to taskMap
        assertEquals(2, scheduler.getTaskMap().size());
        assertNotNull(scheduler.getTaskMap().get(1));
        assertNotNull(scheduler.getTaskMap().get(2));

        // Check task details
        Task task1 = scheduler.getTaskMap().get(1);
        assertEquals("Fix Bug", task1.description);
        assertEquals(1, task1.priority);
        assertEquals(List.of(0), task1.dependencies);
        assertEquals("Developer1", task1.developer);

        Task task2 = scheduler.getTaskMap().get(2);
        assertEquals("Implement Feature", task2.description);
        assertEquals(3, task2.priority);
        assertEquals(List.of(0), task2.dependencies);
        assertEquals("Developer1", task2.developer);

        // Check if tasks are associated with the developer in userTasks
        List<Integer> developer1Tasks = scheduler.getUserTasks().get("Developer1");
        assertEquals(2, developer1Tasks.size());
        assertTrue(developer1Tasks.contains(1));
        assertTrue(developer1Tasks.contains(2));
    }

    @Test
    public void testGetTaskById() {
        // Add a developer and tasks to the scheduler
        scheduler.addUser("Developer1");
        scheduler.addTask("Fix Bug", 1,  new ArrayList<>(List.of(0)), "Developer1");

        // Test getting a task that exists
        // The task should not be null
        Task task = scheduler.getTaskById(1);
        assertNotNull(task);
        assertEquals(1, task.id);
        assertEquals("Fix Bug", task.description);
        assertEquals(1, task.priority);
        assertEquals(List.of(0), task.dependencies);
        assertEquals("Developer1", task.developer);

        // Test getting a task that does not exist
        // The task should be null
        Task nonExistentTask = scheduler.getTaskById(99);
        assertNull(nonExistentTask);
    }

    @Test
    public void testGetTaskIdsByName() {
        // Add a developer and tasks
        scheduler.addUser("Developer1");
        scheduler.addTask("Fix Bug", 1,  new ArrayList<>(List.of(0)), "Developer1");
        scheduler.addTask("Implement Feature", 2,  new ArrayList<>(List.of(0)), "Developer1");

        // Test getting task IDs for an existing developer
        List<Integer> taskIds = scheduler.getTaskIdsByName("Developer1");
        assertNotNull(taskIds);
        assertEquals(2, taskIds.size());
        assertTrue(taskIds.contains(1));
        assertTrue(taskIds.contains(2));

        // Test getting task IDs for a non-existent developer
        // should return null for a non-existent developer
        List<Integer> nonExistentTaskIds = scheduler.getTaskIdsByName("Developer2");
        assertNull(nonExistentTaskIds);
    }

    @Test
    public void testGetCompletedTasksByName() {
        // Add a developer and tasks to the scheduler
        scheduler.addUser("Developer1");
        scheduler.addTask("Fix Bug", 1,  new ArrayList<>(List.of(0)), "Developer1");
        scheduler.addTask("Implement Feature", 2,  new ArrayList<>(List.of(0)), "Developer1");

        // Simulate completing tasks
        scheduler.executeTaskById(1); // Mark task ID 1 as completed

        // Test getting completed tasks for a developer with completed tasks
        // The list should not be null
        // There should be 1 completed task
        // The list should contain task ID 1
        List<Integer> developer1CompletedTasks = scheduler.getCompletedTasksByName("Developer1");
        assertNotNull(developer1CompletedTasks);
        assertEquals(1, developer1CompletedTasks.size());
        assertTrue(developer1CompletedTasks.contains(1));


        // Test getting completed tasks for a developer with no completed tasks
        // The list should not be null
        // The list should be empty
        scheduler.addUser("Developer2");
        List<Integer> developer2CompletedTasks = scheduler.getCompletedTasksByName("Developer2");
        assertNotNull(developer2CompletedTasks);
        assertTrue(developer2CompletedTasks.isEmpty());

        // Test getting completed tasks for a non-existent developer
        // The list should not be null
        // The list should be empty
        List<Integer> nonExistentCompletedTasks = scheduler.getCompletedTasksByName("Developer3");
        assertNotNull(nonExistentCompletedTasks);
        assertTrue(nonExistentCompletedTasks.isEmpty());
    }

    @Test
    public void testAreDependenciesResolved() {
        // Add developers to the scheduler
        scheduler.addUser("Developer1");

        // Test case: Task with no dependencies
        scheduler.addTask("Task with No Dependency", 1,  new ArrayList<>(List.of(0)), "Developer1");
        Task taskWithNoDependency = scheduler.getTaskById(1);
        assertTrue(scheduler.areDependenciesResolved(taskWithNoDependency));

        // Test case: Task with a resolved dependency
        // Task ID 2
        // Task ID 3 depends on Task ID 2
        // Mark Task ID 2 as completed
        scheduler.addTask("Independent Task", 2,  new ArrayList<>(List.of(0)), "Developer1");
        scheduler.addTask("Task with Resolved Dependency", 3,  new ArrayList<>(List.of(2)), "Developer1");
        scheduler.executeTaskById(2);
        Task taskWithResolvedDependency = scheduler.getTaskById(3);
        assertTrue(scheduler.areDependenciesResolved(taskWithResolvedDependency));

        // Test case: Task with an unresolved dependency
        // Task ID 4 depends on Task ID 3 (which isn't marked as completed yet)
        scheduler.addTask("Task with Unresolved Dependency", 4,  new ArrayList<>(List.of(3)), "Developer1");
        Task taskWithUnresolvedDependency = scheduler.getTaskById(4);
        assertFalse(scheduler.areDependenciesResolved(taskWithUnresolvedDependency));
    }

    @Test
    public void testExecuteAllTasks() {
        // Add developers to the scheduler
        scheduler.addUser("Developer1");

        // Test case: Tasks with no dependencies
        scheduler.addTask("Task 1", 2,  new ArrayList<>(List.of(0)), "Developer1"); // Priority 2, no dependency
        scheduler.addTask("Task 2", 1,  new ArrayList<>(List.of(0)), "Developer1"); // Priority 1, no dependency

        // Test case: Tasks with dependencies
        scheduler.addTask("Task 3", 3,  new ArrayList<>(List.of(2)), "Developer1"); // Priority 3, depends on Task 2
        scheduler.addTask("Task 4", 4,  new ArrayList<>(List.of(3)), "Developer1"); // Priority 4, depends on Task 3

        // Capture initial completed tasks
        List<Integer> initialCompletedTasks = scheduler.getCompletedTasksByName("Developer1");
        assertNotNull(initialCompletedTasks);
        assertTrue(initialCompletedTasks.isEmpty()); // No tasks should be completed yet

        // Execute all tasks
        scheduler.executeAllTasks();

        // Check if tasks were executed in the correct order and dependencies resolved
        // All 4 tasks should be completed
        List<Integer> completedTasks = scheduler.getCompletedTasksByName("Developer1");
        assertNotNull(completedTasks);
        assertEquals(4, completedTasks.size());

        // Check the order of execution
        assertEquals(Integer.valueOf(2), completedTasks.get(0)); // Task 2 should be completed first
        assertEquals(Integer.valueOf(1), completedTasks.get(1)); // Task 1 should be completed next
        assertEquals(Integer.valueOf(3), completedTasks.get(2)); // Task 3 should be completed after Task 2
        assertEquals(Integer.valueOf(4), completedTasks.get(3)); // Task 4 should be completed last
    }

    @Test
    public void testExecuteAllTasksWithUnresolvedDependencies() {
        // Add developers to the scheduler
        scheduler.addUser("Developer2");

        // Test case: Task with unresolved dependencies
        scheduler.addTask("Independent Task", 1,  new ArrayList<>(List.of(0)), "Developer2"); // Task with no dependencies
        scheduler.addTask("Dependent Task", 2,  new ArrayList<>(List.of(99)), "Developer2");  // Task depends on a non-existent Task ID 99

        // Execute all tasks
        scheduler.executeAllTasks();

        // Check that only the independent task was completed
        // Only 1 task should be completed
        // The independent task should be completed
        List<Integer> completedTasks = scheduler.getCompletedTasksByName("Developer2");
        assertNotNull(completedTasks);
        assertEquals(1, completedTasks.size());
        assertEquals(Integer.valueOf(1), completedTasks.getFirst());
    }

    @Test
    public void testMarkTaskAsCompleted() {
        // Add a developer and a task to the scheduler
        scheduler.addUser("Developer1");
        scheduler.addTask("Task 1", 1,  new ArrayList<>(List.of(0)), "Developer1");

        // Retrieve the task and mark it as completed
        Task task = scheduler.getTaskById(1);
        scheduler.markTaskAsCompleted(task);

        // Check if the task was removed from userTasks
        // Task ID 1 should not be in userTasks anymore
        List<Integer> userTaskList = scheduler.getUserTasks().get("Developer1");
        assertFalse(userTaskList.contains(1));

        // Check if the task was added to completedTasks
        // Task ID 1 should be in completedTasks
        List<Integer> completedTaskList = scheduler.getCompletedTasksByName("Developer1");
        assertTrue(completedTaskList.contains(1));
    }

    @Test
    public void testDependenciesResolved() {
        // Add developers to the scheduler
        scheduler.addUser("Developer1");
        scheduler.addUser("Developer2");
        // Create tasks with no dependencies
        scheduler.addTask("Task 1", 1, new ArrayList<>(List.of(0)), "Developer1");
        scheduler.addTask("Task 2", 2, new ArrayList<>(List.of(1)), "Developer2");

        // Manually mark Task 1 as completed
        Task task1 = scheduler.getTaskById(1);
        scheduler.markTaskAsCompleted(task1);

        // Check if Task 2 dependencies are resolved
        Task task2 = scheduler.getTaskById(2);
        boolean dependenciesResolved = scheduler.areDependenciesResolved(task2);
        assertTrue(dependenciesResolved);
    }

    @Test
    public void testMultipleDependencyTasks() {
        scheduler.addUser("Developer1");
        scheduler.addUser("Developer2");

        // Create tasks with dependencies
        scheduler.addTask("Task 1", 1,  new ArrayList<>(List.of()), "Developer1");
        scheduler.addTask("Task 2", 2,  new ArrayList<>(List.of(1, 3)), "Developer2");
        scheduler.addTask("Task 3", 3,  new ArrayList<>(List.of()), "Developer2");

        // Mark Task 1 as completed but not Task 3
        Task task1 = scheduler.getTaskById(1);
        Task task3 = scheduler.getTaskById(3);
        scheduler.markTaskAsCompleted(task1);

        // Check if Task 2 dependencies are resolved
        // Task2 should **NOT** be resolved
        Task task2 = scheduler.getTaskById(2);
        boolean dependenciesResolved = scheduler.areDependenciesResolved(task2);
        assertFalse(dependenciesResolved);

        // Resolve task3
        scheduler.markTaskAsCompleted(task3);

        // Check if Task 2 dependencies are resolved
        // Task2 **should** be resolved
        task2 = scheduler.getTaskById(2);
        dependenciesResolved = scheduler.areDependenciesResolved(task2);
        assertTrue(dependenciesResolved);
    }
}
