package multithreading;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class WorkerTask implements Runnable {
    private String taskName;

    WorkerTask(String taskName) {
        this.taskName = taskName;
    }

    public void run() {
        System.out.println(taskName + " executed by " +
                Thread.currentThread().getName());
    }
}

public class ManyToManyExample {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(3);

        executor.execute(new WorkerTask("Task 1"));
        executor.execute(new WorkerTask("Task 2"));
        executor.execute(new WorkerTask("Task 3"));
        executor.execute(new WorkerTask("Task 4"));

        executor.shutdown();
    }
}

