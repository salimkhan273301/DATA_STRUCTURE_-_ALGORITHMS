package basic_110_question;

import java.util.*;
import java.util.stream.IntStream;

public class MissingNumber {
    
    // Solution 1: Using Mathematical Formula (Sum)
    public static int findMissingUsingSum(int[] arr, int n) {
        int expectedSum = n * (n + 1) / 2;
        int actualSum = 0;
        
        for (int num : arr) {
            actualSum += num;
        }
        
        return expectedSum - actualSum;
    }
    
    // Solution 2: Using XOR (Most Efficient)
    public static int findMissingUsingXOR(int[] arr, int n) {
        int xor1 = 0, xor2 = 0;
        
        // XOR of all numbers from 1 to n
        for (int i = 1; i <= n; i++) {
            xor1 ^= i;
        }
        
        // XOR of all array elements
        for (int num : arr) {
            xor2 ^= num;
        }
        
        return xor1 ^ xor2;
    }
    
    // Solution 3: Using HashSet
    public static int findMissingUsingSet(int[] arr, int n) {
        Set<Integer> set = new HashSet<>();
        for (int num : arr) {
            set.add(num);
        }
        
        for (int i = 1; i <= n; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        
        return -1;
    }
    
    // Solution 4: Using Sorting
    public static int findMissingUsingSorting(int[] arr, int n) {
        Arrays.sort(arr);
        
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] != i + 1) {
                return i + 1;
            }
        }
        
        return n;
    }
    
    // Solution 5: Using Boolean Array
    public static int findMissingUsingBooleanArray(int[] arr, int n) {
        boolean[] present = new boolean[n + 1];
        
        for (int num : arr) {
            present[num] = true;
        }
        
        for (int i = 1; i <= n; i++) {
            if (!present[i]) {
                return i;
            }
        }
        
        return -1;
    }
    
    // Solution 6: Using Java 8 Streams
    public static int findMissingUsingStreams(int[] arr, int n) {
        int expectedSum = n * (n + 1) / 2;
        int actualSum = Arrays.stream(arr).sum();
        return expectedSum - actualSum;
    }
    
    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 6, 3, 7, 8};
        int n = 8;
        
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Range: 1 to " + n);
        
        System.out.println("\n1. Using Sum Formula: " + findMissingUsingSum(arr, n));
        System.out.println("2. Using XOR: " + findMissingUsingXOR(arr, n));
        System.out.println("3. Using HashSet: " + findMissingUsingSet(arr, n));
        System.out.println("4. Using Sorting: " + findMissingUsingSorting(arr, n));
        System.out.println("5. Using Boolean Array: " + findMissingUsingBooleanArray(arr, n));
        System.out.println("6. Using Streams: " + findMissingUsingStreams(arr, n));
    }
}