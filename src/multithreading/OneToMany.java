package multithreading;

import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

public class OneToMany implements Runnable {
    
    private final AtomicInteger counter = new AtomicInteger(0);
    
    @Override
    public void run() {
        // Check if within working hours (4:29 PM to 11:10 PM)
        LocalTime now = LocalTime.now();
        LocalTime start = LocalTime.of(16, 29);  // 4:29 PM
        LocalTime end = LocalTime.of(23, 59);    // 11:10 PM
        
        if (!now.isAfter(start) || now.isAfter(end)) {
            System.out.println("Outside working hours. Skipping.");
            return;
        }
        
        // Rotate through 3 services
        int serviceNumber = counter.getAndIncrement() % 3 + 1;
        
        try {
            switch (serviceNumber) {
                case 1: service1(); break;
                case 2: service2(); break;
                case 3: service3(); break;
            }
        } catch (Exception e) {
            System.err.println("Service " + serviceNumber + " error: " + e.getMessage());
            // Continue - don't stop scheduler
        }
    }
    
    private void service1() throws Exception {
        System.out.println("Service 1: Processing database...");
        // Your database logic here
        Thread.sleep(1000); // Simulate work
    }
    
    private void service2() throws Exception {
        System.out.println("Service 2: Calling API...");
        // Your API logic here
        Thread.sleep(1000); // Simulate work
    }
    
    private void service3() throws Exception {
        System.out.println("Service 3: Generating reports...");
        // Your report logic here
        Thread.sleep(1000); // Simulate work
    }
}