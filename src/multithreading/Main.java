package multithreading;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Simple Service Scheduler ===");
        System.out.println("Commands: start | stop | status | exit");
        
        while (true) {
            System.out.print("\nEnter command: ");
            String command = scanner.nextLine().trim().toLowerCase();
            
            switch (command) {
                case "start":
                    MyScheduler.start();
                    break;
                    
                case "stop":
                    MyScheduler.stop();
                    break;
                    
                case "status":
                    System.out.println("Status: " + MyScheduler.getStatus());
                    break;
                    
                case "exit":
                    MyScheduler.stop();
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                    
                default:
                    System.out.println("Unknown command. Try: start, stop, status, exit");
            }
        }
    }
}
