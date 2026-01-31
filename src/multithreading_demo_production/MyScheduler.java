package multithreading_demo_production;



import java.util.concurrent.*;

public class MyScheduler {

    private static ScheduledExecutorService scheduler;
    private static ScheduledFuture<?> future;
    private static final OneToMany task = new OneToMany();

    public synchronized static void start() {

        if (future != null && !future.isCancelled()) {
            return;
        }

        scheduler = Executors.newSingleThreadScheduledExecutor(r -> {
            Thread t = new Thread(r);
            t.setName("Aadhaar-Scheduler-Thread");
            return t;
        });

        future = scheduler.scheduleAtFixedRate(
                task, 0, 1, TimeUnit.MINUTES
        );
    }

    public synchronized static void stop() {

        if (scheduler == null) return;

        future.cancel(false);
        scheduler.shutdown();

        try {
            scheduler.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
        }
    }
}

