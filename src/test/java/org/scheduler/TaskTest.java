package org.scheduler;
// Ankita Tank
// Project - Task Scheduler
// November 2024

import org.junit.Test;

import static org.junit.Assert.*;

public class TaskTest {
    @Test
    public void testTaskCreation() {
        Task task = new Task(1, "Fix Bug", 1, 0, "Developer1");
        assertEquals(1, task.id);
        assertEquals("Fix Bug", task.description);
        assertEquals(1, task.priority);
        assertEquals(0, task.dependency);
        assertEquals("Developer1", task.developer);
    }

    @Test
    public void testTaskPriorityComparison() {
        Task highPriorityTask = new Task(1, "High Priority", 1, 0, "Developer1");
        Task lowPriorityTask = new Task(2, "Low Priority", 5, 0, "Developer2");

        assertTrue(highPriorityTask.compareTo(lowPriorityTask) < 0);
        assertTrue(lowPriorityTask.compareTo(highPriorityTask) > 0);
    }
}
