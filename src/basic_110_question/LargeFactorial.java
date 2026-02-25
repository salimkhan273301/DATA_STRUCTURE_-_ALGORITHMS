package basic_110_question;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.LongStream;

public class LargeFactorial {
    
    // Solution 1: Using BigInteger (for very large numbers)
    public static BigInteger factorialBigInteger(int n) {
        BigInteger result = BigInteger.ONE;
        
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        
        return result;
    }
    
    // Solution 2: Using Recursion with BigInteger
    public static BigInteger factorialRecursive(int n) {
        if (n == 0 || n == 1) {
            return BigInteger.ONE;
        }
        return BigInteger.valueOf(n).multiply(factorialRecursive(n - 1));
    }
    
    // Solution 3: Using Array (Manual multiplication for extremely large numbers)
    public static int[] factorialUsingArray(int n) {
        int[] result = new int[500]; // Adjust size based on expected digits
        result[0] = 1;
        int resultSize = 1;
        
        for (int x = 2; x <= n; x++) {
            int carry = 0;
            
            for (int i = 0; i < resultSize; i++) {
                int prod = result[i] * x + carry;
                result[i] = prod % 10;
                carry = prod / 10;
            }
            
            while (carry > 0) {
                result[resultSize] = carry % 10;
                carry = carry / 10;
                resultSize++;
            }
        }
        
        return Arrays.copyOf(result, resultSize);
    }
    
    // Solution 4: Using Java 8 Streams
    public static BigInteger factorialUsingStreams(int n) {
        return LongStream.rangeClosed(2, n)
                .mapToObj(BigInteger::valueOf)
                .reduce(BigInteger.ONE, BigInteger::multiply);
    }
    
    // Solution 5: Using Memoization (for multiple factorial calculations)
    private static Map<Integer, BigInteger> cache = new HashMap<>();
    
    public static BigInteger factorialMemoized(int n) {
        if (n == 0 || n == 1) {
            return BigInteger.ONE;
        }
        
        if (cache.containsKey(n)) {
            return cache.get(n);
        }
        
        BigInteger result = BigInteger.valueOf(n).multiply(factorialMemoized(n - 1));
        cache.put(n, result);
        return result;
    }
    
    // Solution 6: Using Tail Recursion
    public static BigInteger factorialTailRecursive(int n) {
        return factorialTailHelper(n, BigInteger.ONE);
    }
    
    private static BigInteger factorialTailHelper(int n, BigInteger accumulator) {
        if (n == 0 || n == 1) {
            return accumulator;
        }
        return factorialTailHelper(n - 1, accumulator.multiply(BigInteger.valueOf(n)));
    }
    
    // Helper to print array result
    public static void printArrayAsNumber(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            System.out.print(digits[i]);
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        int[] testNumbers = {5, 10, 20, 25, 50};
        
        for (int n : testNumbers) {
            System.out.println("\n--- Factorial of " + n + " ---");
            
            System.out.println("1. Using BigInteger: " + factorialBigInteger(n));
            System.out.println("2. Using Recursion: " + factorialRecursive(n));
            System.out.println("3. Using Array: ");
            int[] result = factorialUsingArray(n);
            printArrayAsNumber(result);
            System.out.println("   (Number of digits: " + result.length + ")");
            System.out.println("4. Using Streams: " + factorialUsingStreams(n));
            System.out.println("5. Using Memoization: " + factorialMemoized(n));
            System.out.println("6. Using Tail Recursion: " + factorialTailRecursive(n));
        }
        
        // Performance test for large number
        System.out.println("\n--- Performance Test (Factorial 1000) ---");
        long start = System.currentTimeMillis();
        BigInteger result = factorialBigInteger(1000);
        long end = System.currentTimeMillis();
        System.out.println("Time taken: " + (end - start) + " ms");
        System.out.println("Number of digits: " + result.toString().length());
    }
}
