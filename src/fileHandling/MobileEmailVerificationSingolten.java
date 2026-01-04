package fileHandling;

public class MobileEmailVerificationSingolten {
    private SingletonDemoBySalim propertyLoader;

    public MobileEmailVerificationSingolten() {
        propertyLoader = SingletonDemoBySalim.getInstance();
    }

    public void sendOTP() {
        String sendOTPUrl = propertyLoader.getProperty("send_otp_url");
        int readTimeout = propertyLoader.getIntProperty("read_timeout");

        // Use sendOTPUrl and readTimeout in your sendOTP logic
        System.out.println("Sending OTP using URL: " + sendOTPUrl);
        System.out.println("Read Timeout: " + readTimeout + " milliseconds");
    }

    public void verifyOTP() {
        String verifyOTPUrl = propertyLoader.getProperty("verify_otp_url");

        // Use verifyOTPUrl in your verifyOTP logic
        System.out.println("Verifying OTP using URL: " + verifyOTPUrl);
    }

    public static void main(String[] args) {
        MobileEmailVerification verification = new MobileEmailVerification();
        verification.sendOTP();
        verification.verifyOTP();
    }
}

