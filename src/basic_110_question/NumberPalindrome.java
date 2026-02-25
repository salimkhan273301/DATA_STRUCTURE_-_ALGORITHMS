package basic_110_question;

import java.util.Deque;
import java.util.LinkedList;

public class NumberPalindrome {
    
    // Solution 1: Reverse the number
    public static boolean isPalindromeReverse(int num) {
        if (num < 0) return false;
        
        int original = num;
        int reversed = 0;
        
        while (num > 0) {
            int digit = num % 10;
            reversed = reversed * 10 + digit;
            num /= 10;
        }
        
        return original == reversed;
    }
    
    // Solution 2: Using String conversion
    public static boolean isPalindromeString(int num) {
        if (num < 0) return false;
        
        String str = String.valueOf(num);
        String reversed = new StringBuilder(str).reverse().toString();
        return str.equals(reversed);
    }
    
    // Solution 3: Compare half digits (without full reversal)
    public static boolean isPalindromeHalfCompare(int num) {
        if (num < 0 || (num % 10 == 0 && num != 0)) {
            return false;
        }
        
        int reversedHalf = 0;
        
        while (num > reversedHalf) {
            reversedHalf = reversedHalf * 10 + num % 10;
            num /= 10;
        }
        
        return num == reversedHalf || num == reversedHalf / 10;
    }
    
    // Solution 4: Using Recursion
    public static boolean isPalindromeRecursive(int num) {
        if (num < 0) return false;
        String str = String.valueOf(num);
        return isPalindromeRecursiveHelper(str, 0, str.length() - 1);
    }
    
    private static boolean isPalindromeRecursiveHelper(String str, int left, int right) {
        if (left >= right) return true;
        if (str.charAt(left) != str.charAt(right)) return false;
        return isPalindromeRecursiveHelper(str, left + 1, right - 1);
    }
    
    // Solution 5: Using character array
    public static boolean isPalindromeCharArray(int num) {
        if (num < 0) return false;
        
        char[] digits = String.valueOf(num).toCharArray();
        int left = 0;
        int right = digits.length - 1;
        
        while (left < right) {
            if (digits[left] != digits[right]) {
                return false;
            }
            left++;
            right--;
        }
        
        return true;
    }
    
    // Solution 6: Using Deque
    public static boolean isPalindromeDeque(int num) {
        if (num < 0) return false;
        
        Deque<Character> deque = new LinkedList<>();
        String str = String.valueOf(num);
        
        for (char c : str.toCharArray()) {
            deque.addLast(c);
        }
        
        while (deque.size() > 1) {
            if (!deque.pollFirst().equals(deque.pollLast())) {
                return false;
            }
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        int[] testNumbers = {121, 12321, 12345, -121, 0, 11, 123321, 1234321};
        
        for (int num : testNumbers) {
            System.out.println("\nNumber: " + num);
            System.out.println("1. Reverse Method: " + isPalindromeReverse(num));
            System.out.println("2. String Method: " + isPalindromeString(num));
            System.out.println("3. Half Compare: " + isPalindromeHalfCompare(num));
            System.out.println("4. Recursive: " + isPalindromeRecursive(num));
            System.out.println("5. Char Array: " + isPalindromeCharArray(num));
            System.out.println("6. Deque Method: " + isPalindromeDeque(num));
        }
    }
}	
