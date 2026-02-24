package basic_110_question;

public class ReverseString {
    // Method 1: Using StringBuilder
    public static String reverseWithBuilder(String str) {
        return new StringBuilder(str).reverse().toString();
    }
    
    // Method 2: Using character array
    public static String reverseWithArray(String str) {
        char[] charArray = str.toCharArray();
        int left = 0, right = charArray.length - 1;
        
        while (left < right) {
            // Swap characters
            char temp = charArray[left];
            charArray[left] = charArray[right];
            charArray[right] = temp;
            left++;
            right--;
        }
        
        return new String(charArray);
    }
    
    // Method 3: Recursive approach
    public static String reverseRecursive(String str) {
        if (str.isEmpty()) {
            return str;
        }
        return reverseRecursive(str.substring(1)) + str.charAt(0);
    }
    
    public static void main(String[] args) {
        String input = "Hello World";
        System.out.println("Original: " + input);
        System.out.println("Using Builder: " + reverseWithBuilder(input));
        System.out.println("Using Array: " + reverseWithArray(input));
        System.out.println("Using Recursion: " + reverseRecursive(input));
    }
}