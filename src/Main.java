import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// Main class to run the Task Scheduler
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskScheduler scheduler = new TaskScheduler();

        while (true) {
            System.out.println("--------------------------------------");
            System.out.println("1. Add Developer");
            System.out.println("2. Create New Task");
            System.out.println("3. Execute Task");
            System.out.println("4. Execute All Tasks");
            System.out.println("5. Show Developer Incomplete Tasks");
            System.out.println("6. Search Task by Id");
            System.out.println("7. Remove Task");
            System.out.println("8. Show Tasks by Epic");
            System.out.println("10. Search Task by Name");
            System.out.println("9. Exit");
            System.out.println("--------------------------------------");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: //done
                    System.out.print("Enter Developer Name: ");
                    String name = scanner.nextLine();
                    scheduler.addUser(name);
                    break;

                case 2:
                    System.out.print("Enter Assigned Developer: ");
                    String assignedUser = scanner.nextLine();

                    System.out.print("Enter EpicId: ");
                    String epicId = scanner.nextLine();

                    System.out.print("Enter Task Description: ");
                    String description = scanner.nextLine();

                    System.out.print("Enter Task Priority (1 = highest, 5 = lowest): ");
                    int priority = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter Task Dependencies (comma-separated Task IDs, leave empty if none): ");
                    String dependenciesInput = scanner.nextLine();

                    List<String> dependencies = Arrays.asList(dependenciesInput.split(","));
                    if (dependencies.size() == 1 && dependencies.getFirst().isEmpty()) dependencies = new ArrayList<>();

                    scheduler.addTask(Integer.parseInt(epicId), description, priority, dependencies, assignedUser);
                    break;

                case 3:
                    scheduler.executeTask();
                    break;

                case 10:
                    System.out.print("Enter Developer Name: ");
                    String devName = scanner.nextLine();
                    scheduler.showUserTasks(devName);
                    break;

                case 5:
                    System.out.print("Enter Developer Name: ");
                    String userCompleted = scanner.nextLine();
                    scheduler.showCompletedTasks(userCompleted);
                    break;

                case 6:
                    System.out.print("Enter TaskId: ");
                    String taskId = scanner.nextLine();
                    scheduler.searchTask(Integer.parseInt(taskId));
                    break;

                case 7:
                    System.out.print("Enter TaskId: ");
                    String removeTaskId = scanner.nextLine();
                    scheduler.removeTask(Integer.parseInt(removeTaskId));
                    break;

                case 8:
                    System.out.print("Enter EpicId: ");
                    String searchEpicId = scanner.nextLine();
                    scheduler.searchEpic(Integer.parseInt(searchEpicId));
                    break;

                case 9:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
