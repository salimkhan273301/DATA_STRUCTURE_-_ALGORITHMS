package fileHandling;

public class MobileEmailVerification {
    private PropertyReaderSinglton propertyReader;
    
    public MobileEmailVerification() {
        propertyReader = PropertyReaderSinglton.getInstance();
    }

    public void sendOTP() {
        String sendOTPUrl = propertyReader.getProperty("send_otp_url");
        int readTimeout = propertyReader.getIntProperty("read_timeout");
        
        // Use sendOTPUrl and readTimeout in your sendOTP logic
        System.out.println("Sending OTP using URL: " + sendOTPUrl);
        System.out.println("Read Timeout: " + readTimeout + " milliseconds");
    }

    public void verifyOTP() {
        String verifyOTPUrl = propertyReader.getProperty("verify_otp_url");
        
        // Use verifyOTPUrl in your verifyOTP logic
        System.out.println("Verifying OTP using URL: " + verifyOTPUrl);
    }
}
