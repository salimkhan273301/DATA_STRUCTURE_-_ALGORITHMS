package basic_110_question;

public class PalindromeCheck {
    public static boolean isPalindrome(String str) {
        if (str == null || str.isEmpty()) {
            return true;
        }
        
        str = str.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
        int left = 0;
        int right = str.length() - 1;
        
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    
    public static void main(String[] args) {
        String[] testCases = {"racecar", "A man, a plan, a canal: Panama", "hello"};
        
        for (String test : testCases) {
            System.out.println(test + " is palindrome? " + isPalindrome(test));
        }
    }
}