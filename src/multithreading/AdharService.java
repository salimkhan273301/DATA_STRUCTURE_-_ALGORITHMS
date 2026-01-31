package multithreading;


public class AdharService {
    
    // Synchronized method - only one thread can use it at a time
    public synchronized void encryptAdhar(String adharNumber, String serviceName) {
        System.out.println(serviceName + ": Starting Aadhaar encryption for: " + maskAdhar(adharNumber));
        
        try {
            // Simulate encryption taking 70 seconds (more than 1 minute)
            System.out.println(serviceName + ": Encrypting (this takes 70 seconds)...");
            Thread.sleep(70000); // 70 seconds
            
            // Simulate encryption logic
            String encrypted = "ENC-" + adharNumber.hashCode();
            System.out.println(serviceName + ": Aadhaar encrypted successfully: " + encrypted);
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println(serviceName + ": Encryption interrupted!");
        } catch (Exception e) {
            System.err.println(serviceName + ": Encryption failed: " + e.getMessage());
        }
    }
    
    // Helper method to mask Aadhaar for logging
    private String maskAdhar(String adharNumber) {
        if (adharNumber == null || adharNumber.length() < 4) {
            return "INVALID";
        }
        return "XXXX-XXXX-" + adharNumber.substring(adharNumber.length() - 4);
    }
    
    // Other Aadhaar-related methods
    public synchronized void validateAdhar(String adharNumber, String serviceName) {
        System.out.println(serviceName + ": Validating Aadhaar...");
        try {
            Thread.sleep(5000); // 5 seconds
            System.out.println(serviceName + ": Aadhaar validated");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}