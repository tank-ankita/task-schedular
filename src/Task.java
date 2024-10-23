import java.util.ArrayList;
import java.util.List;

// Class to represent each Task
public class Task {
    int id;
    int priority;
    String description;
    String developer;
    List<String> dependencies; // Task dependencies

    public Task(int id, String description, int priority, List<String> dependencies, String developer) {
        this.id = id;
        this.priority = priority;
        this.description = description;
        this.developer = developer;
        this.dependencies = dependencies != null ? dependencies : new ArrayList<>();
    }
}