package org.scheduler;
// Ankita Tank
// Project - Task Scheduler
// November 2024

import org.junit.Test;
import org.scheduler.stdlib.In;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TaskTest {
    @Test
    public void testTaskCreation() {
        Task task = new Task(1, "Fix Bug", 1, new ArrayList<>(List.of(0)), "Developer1");
        assertEquals(1, task.id);
        assertEquals("Fix Bug", task.description);
        assertEquals(1, task.priority);
        assertEquals(List.of(0), task.dependencies);
        assertEquals("Developer1", task.developer);
    }

    @Test
    public void testTaskPriorityComparison() {
        Task highPriorityTask = new Task(1, "High Priority", 1, new ArrayList<>(List.of(0)), "Developer1");
        Task lowPriorityTask = new Task(2, "Low Priority", 5, new ArrayList<>(List.of(0)), "Developer2");

        assertTrue(highPriorityTask.compareTo(lowPriorityTask) < 0);
        assertTrue(lowPriorityTask.compareTo(highPriorityTask) > 0);
    }
}
