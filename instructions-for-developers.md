## Overview
The Task Scheduler is a command-line and IDE-compatible Java application designed for managing tasks and developers efficiently. It features task prioritization, dependency management, and options to execute or search for tasks. This document provides instructions for developers to set up, build, and test the project.

## Installation
**1. Clone the Repository**\
Clone the project using the following command:

``` bash 
git clone https://github.com/tank-ankita/task-schedular.git
```


**2. Open the Project in IntelliJ**

- Launch IntelliJ IDEA and select "Open".
- Navigate to the project directory and open it.
- You will see an MVN build prompt; proceed to build the project through this prompt.

**3. Build the Project**

- Go to File → Build Project in IntelliJ.

### Running the Application
**Using Terminal**

Navigate to the project directory:
``` bash 
cd task-schedular
cd src/main/java/org/scheduler/
java Main.java
```
**Using IDE**

1. Open `Main.java` located in `src/main/java/org/scheduler/Main.java.`
2. Click the Run/Play button in the top right corner of the IDE with `Main.java `file.

----------

### Testing
**Running Tests Using IntelliJ**

- Navigate to the `src/test/java` directory.
- Right-click on the `org.scheduler` directory and select `"Run Tests in org.scheduler"`.

**Running Tests Using Terminal with Maven**

**1. Install Maven (if you haven't already):**

``` bash 
brew install mvn
```

**2. Navigate to the Parent Directory:**
``` bash 
cd task-schedular
```

**3. Run All Tests:**

``` bash 
mvn clean test
```

**4. Run Individual Tests:**

- To run `DeveloperTest.java`:
``` bash 
mvn clean test -Dtest=org.scheduler.DeveloperTest
``` 

- To run `TaskTest.java`:
``` bash 
mvn clean test -Dtest=org.scheduler.TaskTest
```

- To run `TaskSchedulerTest.java`:
``` bash 
mvn clean test -Dtest=org.scheduler.TaskSchedulerTest
```

---------
### Notes

- Ensure that Maven is correctly installed and configured in your environment.
- If you encounter any issues running tests through the terminal, double-check your Maven setup or consider running tests directly in IntelliJ for convenience.
