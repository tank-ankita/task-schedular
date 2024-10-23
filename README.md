# Task-schedular
A lightweight task scheduling system built in Java that allows users to manage tasks, assign them to developers, and execute them based on priority using a custom priority queue (min-heap). 
The scheduler supports task dependencies, ensuring that tasks are only executed if all dependencies are satisfied.

1. Table of Contents
2. Features
3. Technologies Used
4. Installation
5. Usage
6. Classes Overview

## Features
Add Developers: Add new developers to the team.\
Create Tasks: Add tasks with descriptions, priorities, dependencies, and assign them to developers. \
Priority-based Execution: Uses a min-heap to execute the highest-priority task. \
Dependency Management: Ensures that tasks can only be executed if all dependent tasks are completed. \
Track User Tasks: View pending and completed tasks for each developer. \
Custom Priority Queue: Implements a priority queue using a heap data structure. \

## Technologies Used  
Java 17 or higher \
Min-Heap for Priority Queue Management \ 
HashMap and ArrayList for user-task management \

## Installation
### Clone the repository:

<pre><code> 
git clone https://github.com/your-username/task-scheduler.git 
cd task-scheduler

</code></pre>

### Compile the Java files:
<pre><code> 
javac *.java

</code></pre>



### Run the application:
<pre><code> 
java Main

</code></pre>

## Usage
Below are the key functions available in the Task Scheduler.

### Add a Developer:
<pre><code> 
taskScheduler.addUser("Alice");
Output: => 'Alice' has been added to the team with id: '1'

</code></pre>

### Add a Task:
<pre><code> 
List<String> dependencies = List.of("1", "2");
taskScheduler.addTask(0, "Implement Login System", 2, dependencies, "Alice");
Output: => Task 3 added for user 'Alice'

</code></pre>


### Execute a Task by ID:
<pre><code> 
taskScheduler.executeTaskById(3);
Output: 
If dependencies are met: => Executing Task: 3 for user Alice
If dependencies are not met: => Task 3 cannot be executed. Unmet dependencies: [1, 2]

</code></pre>



### Search for a Task:
<pre><code> 
taskScheduler.searchTask(3);
Output: => Task ID: 3 || Epic ID: 0 || Description: Implement Login System || Priority: 2

</code></pre>

### Show User Tasks:
<pre><code> 
taskScheduler.showUserTasks("Alice");
Output: => Pending tasks for developer Alice:

</code></pre>

## Classes Overview
1. TaskScheduler.java\
   Manages users, tasks, and their dependencies.\
   Handles task creation, search, and execution logic.
2. Task.java\
   Represents a task with attributes such as ID, priority, epic ID, description, dependencies, and assigned user.
3. TaskPriorityQueue.java\
   A min-heap-based priority queue that ensures tasks with the lowest priority value are executed first.
   Implements insert(), extractMin(), and heapify() methods.
4. Developer.java\
   Developer information 


Let me know if you need any additional sections or improvements!