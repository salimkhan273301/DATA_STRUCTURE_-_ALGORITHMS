package generalproblem;

import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

public class ArrayReverser {

    // Method 1: Two-pointer approach (iterative)
    public static void reverse1(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            // Swap elements
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    // Method 2: Using a stack (not in-place, but included for comparison)
    public static void reverse2(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        for (int num : arr) {
            stack.push(num);
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = stack.pop();
        }
    }

    // Method 3: Recursive approach
    public static void reverse3(int[] arr) {
        reverseHelper(arr, 0, arr.length - 1);
    }
    
    private static void reverseHelper(int[] arr, int start, int end) {
        if (start >= end) return;
        // Swap elements
        int temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
        reverseHelper(arr, start + 1, end - 1);
    }

    // Method 4: Using Collections.reverse() (for Integer arrays)
    public static void reverse4(Integer[] arr) {
        Collections.reverse(Arrays.asList(arr));
    }

    // Method 5: Using XOR swap (alternative swap mechanism)
    public static void reverse5(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            // XOR swap
            arr[left] ^= arr[right];
            arr[right] ^= arr[left];
            arr[left] ^= arr[right];
            left++;
            right--;
        }
    }

    // Test cases
    public static void main(String[] args) {
        int[][] testCases = {
            {1, 4, 3, 2, 6, 5},
            {4, 5, 2},
            {1},
            {1, 2, 3, 4, 5},
            {9, 8, 7, 6}
        };

        String[] methodNames = {
            "Two-pointer approach",
            "Using stack",
            "Recursive approach",
            "Collections.reverse()",
            "XOR swap"
        };

        for (int[] testCase : testCases) {
            System.out.println("\nOriginal array: " + Arrays.toString(testCase));
            
            // Make copies for each method
            int[] copy1 = Arrays.copyOf(testCase, testCase.length);
            int[] copy2 = Arrays.copyOf(testCase, testCase.length);
            int[] copy3 = Arrays.copyOf(testCase, testCase.length);
            Integer[] copy4 = Arrays.stream(testCase).boxed().toArray(Integer[]::new);
            int[] copy5 = Arrays.copyOf(testCase, testCase.length);
            
            // Apply each method
            reverse1(copy1);
            reverse2(copy2);
            reverse3(copy3);
            reverse4(copy4);
            reverse5(copy5);
            
            // Print results
            System.out.println(methodNames[0] + ": " + Arrays.toString(copy1));
            System.out.println(methodNames[1] + ": " + Arrays.toString(copy2));
            System.out.println(methodNames[2] + ": " + Arrays.toString(copy3));
            System.out.println(methodNames[3] + ": " + Arrays.toString(copy4));
            System.out.println(methodNames[4] + ": " + Arrays.toString(copy5));
        }
    }
}