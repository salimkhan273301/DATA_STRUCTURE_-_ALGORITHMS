package multithreading;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class MyScheduler {
    
    private static ScheduledExecutorService scheduler;
    private static ScheduledFuture<?> task;
    
    // Start the scheduler
    public static void start() {
        if (isRunning()) {
            System.out.println("Scheduler already running");
            return;
        }
        
        // Create single-thread scheduler
        scheduler = Executors.newSingleThreadScheduledExecutor(r -> {
            Thread t = new Thread(r);
            t.setName("Service-Rotator-Thread");
            return t;
        });
        
        // Schedule task to run every minute
        task = scheduler.scheduleAtFixedRate(
            new OneToMany(),  // What to run
            0,                // Start immediately
            1,                // Run every
            TimeUnit.MINUTES  // minutes
        );
        
        System.out.println("✅ Scheduler started. Running every minute.");
    }
    
    // Stop the scheduler
    public static void stop() {
        if (!isRunning()) {
            System.out.println("Scheduler not running");
            return;
        }
        
        // Cancel the scheduled task
        if (task != null) {
            task.cancel(false);
        }
        
        // Shutdown the scheduler
        scheduler.shutdown();
        
        try {
            // Wait for tasks to finish
            if (!scheduler.awaitTermination(10, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
        }
        
        System.out.println("⏹️ Scheduler stopped");
    }
    
    // Check if scheduler is running
    public static boolean isRunning() {
        return scheduler != null && !scheduler.isShutdown() 
               && task != null && !task.isCancelled();
    }
    
    // Get scheduler status
    public static String getStatus() {
        if (!isRunning()) {
            return "Stopped";
        }
        return "Running - Services rotate every minute";
    }
}