package org.scheduler;
// Ankita Tank
// Project - Task Scheduler
// November 2024

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PerformanceTest {
    private TaskScheduler scheduler;

    @Before
    public void setUp() {
        scheduler = new TaskScheduler();
    }


    @Test
    public void testStressWithComplexDependencies() {
        // Add 1000 developers
        for (int i = 0; i < 1000; i++) {
            scheduler.addUser("Developer" + i);
        }

        // Create a series of tasks with  interdependencies
        // Each task depends on the next one
        for (int i = 1; i <= 5000; i++) {
            List<Integer> dependencies = new ArrayList<>();
            if (i > 1) dependencies.add(i + 1);
            scheduler.addTask("Complex Task " + i, i % 5 + 1, dependencies, "Developer" + (i % 1000));
        }

        // Measure time for executing all tasks with complex dependencies
        long startTime = System.nanoTime();
        scheduler.executeAllTasks();
        long endTime = System.nanoTime();

        long duration = endTime - startTime; // Duration in nanoseconds
        System.out.println("Execution Time with Complex Dependencies: " + (duration / 1_000_000) + " ms");

        // Check if execution is within an acceptable range
        long maxAllowedTime = 10000; // in milliseconds
        Assert.assertTrue("Execution with dependencies took too long", (duration / 1_000_000) <= maxAllowedTime);
    }
}
