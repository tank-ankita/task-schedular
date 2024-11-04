package org.scheduler;
// Ankita Tank
// Project - Task Scheduler
// November 2024

import org.junit.Assert;
import org.junit.Test;

public class DeveloperTest {
    @Test
    public void testDeveloperCreation() {
        Developer developer = new Developer(1, "Developer1");
        Assert.assertEquals("Developer1", developer.name);
        Assert.assertEquals(1, developer.id);
    }
}
