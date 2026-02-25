package basic_110_question;

import java.util.*;
import java.util.stream.IntStream;

public class SumOfDigits {
    
    // Solution 1: Iterative Approach
    public static int sumDigitsIterative(int num) {
        num = Math.abs(num);
        int sum = 0;
        
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        
        return sum;
    }
    
    public static int sumUntilSingleIterative(int num) {
        num = Math.abs(num);
        
        while (num >= 10) {
            num = sumDigitsIterative(num);
        }
        
        return num;
    }
    
    // Solution 2: Recursive Approach
    public static int sumUntilSingleRecursive(int num) {
        num = Math.abs(num);
        
        if (num < 10) {
            return num;
        }
        
        int sum = sumDigitsRecursive(num);
        return sumUntilSingleRecursive(sum);
    }
    
    private static int sumDigitsRecursive(int num) {
        if (num == 0) return 0;
        return num % 10 + sumDigitsRecursive(num / 10);
    }
    
    // Solution 3: Using Mathematical Formula (Digital Root)
    // Digital Root = 1 + (n - 1) % 9
    public static int digitalRootFormula(int num) {
        if (num == 0) return 0;
        num = Math.abs(num);
        return 1 + (num - 1) % 9;
    }
    
    // Solution 4: Using String Conversion
    public static int sumUntilSingleString(int num) {
        num = Math.abs(num);
        
        while (num >= 10) {
            String numStr = String.valueOf(num);
            int sum = 0;
            for (int i = 0; i < numStr.length(); i++) {
                sum += numStr.charAt(i) - '0';
            }
            num = sum;
        }
        
        return num;
    }
    
    // Solution 5: Using Streams
    public static int sumUntilSingleStreams(int num) {
        num = Math.abs(num);
        
        while (num >= 10) {
            final int currentNum = num;
            num = String.valueOf(num).chars()
                    .map(c -> c - '0')
                    .sum();
        }
        
        return num;
    }
    
    // Solution 6: Using Modular Arithmetic with Loop
    public static int sumUntilSingleModular(int num) {
        num = Math.abs(num);
        
        if (num == 0) return 0;
        if (num % 9 == 0) return 9;
        return num % 9;
    }
    
    // Bonus: Find sum of digits of large numbers
    public static int sumDigitsLargeNumber(String largeNumber) {
        int sum = largeNumber.chars()
                .map(c -> c - '0')
                .sum();
        
        while (sum >= 10) {
            sum = sumDigitsIterative(sum);
        }
        
        return sum;
    }
    
    // Bonus: Sum of digits of array elements
    public static int sumDigitsOfArray(int[] arr) {
        int totalSum = Arrays.stream(arr).sum();
        return sumUntilSingleIterative(totalSum);
    }
    
    public static void main(String[] args) {
        int[] testNumbers = {38, 12345, 999, 8765, 0, 987654, 999999, -456};
        
        for (int num : testNumbers) {
            System.out.println("\n--- Number: " + num + " ---");
            System.out.println("1. Iterative: " + sumUntilSingleIterative(num));
            System.out.println("2. Recursive: " + sumUntilSingleRecursive(num));
            System.out.println("3. Digital Root Formula: " + digitalRootFormula(num));
            System.out.println("4. String Method: " + sumUntilSingleString(num));
            System.out.println("5. Streams Method: " + sumUntilSingleStreams(num));
            System.out.println("6. Modular Method: " + sumUntilSingleModular(num));
        }
        
        System.out.println("\n--- Large Numbers ---");
        String[] largeNumbers = {
            "12345678901234567890",
            "99999999999999999999",
            "98765432109876543210"
        };
        
        for (String largeNum : largeNumbers) {
            System.out.println(largeNum + " -> " + sumDigitsLargeNumber(largeNum));
        }
        
        System.out.println("\n--- Sum of Array Elements ---");
        int[][] arrays = {
            {1, 2, 3, 4, 5},
            {10, 20, 30, 40, 50},
            {999, 999, 999, 999}
        };
        
        for (int[] arr : arrays) {
            System.out.println(Arrays.toString(arr) + " -> " + sumDigitsOfArray(arr));
        }
        
        // Performance test
        System.out.println("\n--- Performance Test (1-100000) ---");
        long start = System.currentTimeMillis();
        for (int i = 1; i <= 100000; i++) {
            digitalRootFormula(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("Digital Root Formula: " + (end - start) + " ms");
        
        start = System.currentTimeMillis();
        for (int i = 1; i <= 100000; i++) {
            sumUntilSingleIterative(i);
        }
        end = System.currentTimeMillis();
        System.out.println("Iterative Method: " + (end - start) + " ms");
    }
}