package basic_110_question;

import java.util.*;
import java.util.stream.IntStream;

public class ReverseArray{
    
    // Solution 1: Two Pointer Approach
    public static void reverseTwoPointer(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left < right) {
            // Swap
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            
            left++;
            right--;
        }
    }
    
    // Solution 2: Using Recursion
    public static void reverseRecursive(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        
        // Swap
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
        
        // Recursive call
        reverseRecursive(arr, left + 1, right - 1);
    }
    
    // Solution 3: Using Stack
    public static void reverseUsingStack(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        
        // Push all elements to stack
        for (int num : arr) {
            stack.push(num);
        }
        
        // Pop back to array
        for (int i = 0; i < arr.length; i++) {
            arr[i] = stack.pop();
        }
    }
    
    // Solution 4: Using List and Collections
    public static void reverseUsingList(int[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int num : arr) {
            list.add(num);
        }
        
        Collections.reverse(list);
        
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }
    }
    
    // Solution 5: Using XOR Swap (No temporary variable)
    public static void reverseUsingXOR(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left < right) {
            // XOR swap
            arr[left] = arr[left] ^ arr[right];
            arr[right] = arr[left] ^ arr[right];
            arr[left] = arr[left] ^ arr[right];
            
            left++;
            right--;
        }
    }
    
    // Solution 6: Using Java 8 Streams (Creates new array)
    public static int[] reverseUsingStreams(int[] arr) {
        return IntStream.rangeClosed(1, arr.length)
                .map(i -> arr[arr.length - i])
                .toArray();
    }
    
    // Bonus: Reverse only part of array
    public static void reversePartial(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
    
    // Bonus: Reverse in groups
    public static void reverseInGroups(int[] arr, int groupSize) {
        for (int i = 0; i < arr.length; i += groupSize) {
            int end = Math.min(i + groupSize - 1, arr.length - 1);
            reversePartial(arr, i, end);
        }
    }
    
    public static void main(String[] args) {
        // Test array
        int[] original = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        
        System.out.println("Original Array: " + Arrays.toString(original));
        
        // Test Solution 1
        int[] arr1 = original.clone();
        reverseTwoPointer(arr1);
        System.out.println("\n1. Two Pointer: " + Arrays.toString(arr1));
        
        // Test Solution 2
        int[] arr2 = original.clone();
        reverseRecursive(arr2, 0, arr2.length - 1);
        System.out.println("2. Recursive: " + Arrays.toString(arr2));
        
        // Test Solution 3
        int[] arr3 = original.clone();
        reverseUsingStack(arr3);
        System.out.println("3. Using Stack: " + Arrays.toString(arr3));
        
        // Test Solution 4
        int[] arr4 = original.clone();
        reverseUsingList(arr4);
        System.out.println("4. Using List: " + Arrays.toString(arr4));
        
        // Test Solution 5
        int[] arr5 = original.clone();
        reverseUsingXOR(arr5);
        System.out.println("5. Using XOR: " + Arrays.toString(arr5));
        
        // Test Solution 6
        int[] arr6 = reverseUsingStreams(original);
        System.out.println("6. Using Streams: " + Arrays.toString(arr6));
        
        // Test Bonus Methods
        int[] arr7 = original.clone();
        reversePartial(arr7, 2, 6);
        System.out.println("\nPartial Reverse (2 to 6): " + Arrays.toString(arr7));
        
        int[] arr8 = original.clone();
        reverseInGroups(arr8, 3);
        System.out.println("Group Reverse (size 3): " + Arrays.toString(arr8));
        
        // Performance test
        System.out.println("\n--- Performance Test (100000 elements) ---");
        int[] largeArray = new Random().ints(100000, 1, 100000).toArray();
        int[] largeArrayCopy = largeArray.clone();
        
        long start = System.currentTimeMillis();
        reverseTwoPointer(largeArrayCopy);
        long end = System.currentTimeMillis();
        System.out.println("Two Pointer: " + (end - start) + " ms");
        
        largeArrayCopy = largeArray.clone();
        start = System.currentTimeMillis();
        reverseUsingXOR(largeArrayCopy);
        end = System.currentTimeMillis();
        System.out.println("XOR Swap: " + (end - start) + " ms");
    }
}