import java.util.ArrayList;
import java.util.List;

class TaskPriorityQueue {
    private final List<Task> heap;

    public TaskPriorityQueue() {
        heap = new ArrayList<>();
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int leftChild(int i) {
        return 2 * i + 1;
    }

    private int rightChild(int i) {
        return 2 * i + 2;
    }

    public void insert(Task task) {
        heap.add(task);
        int current = heap.size() - 1;

        while (current > 0 && heap.get(parent(current)).priority > heap.get(current).priority) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    public Task extractMin() {
        if (heap.isEmpty()) {
            System.out.println("No tasks in the queue.");
            return null;
        }

        Task minTask = heap.getFirst();
        Task lastTask = heap.removeLast();

        if (!heap.isEmpty()) {
            heap.set(0, lastTask);
            heapify(0);
        }

        return minTask;
    }

    private void heapify(int i) {
        int left = leftChild(i);
        int right = rightChild(i);
        int smallest = i;

        if (left < heap.size() && heap.get(left).priority < heap.get(smallest).priority) {
            smallest = left;
        }

        if (right < heap.size() && heap.get(right).priority < heap.get(smallest).priority) {
            smallest = right;
        }

        if (smallest != i) {
            swap(i, smallest);
            heapify(smallest);
        }
    }

    private void swap(int i, int j) {
        Task temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }
}
