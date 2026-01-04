package generalproblem;

import java.util.*;
import java.util.stream.*;

public class PushZerosToEnd {

    // Method 1: Two-pointer technique (original fixed version)
    public static void pushZerosToEnd1(int[] arr) {
        int start = 0;
        for (int end = 0; end < arr.length; end++) {
            if (arr[end] != 0) {
                int temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;
                start++;
            }
        }
    }

    // Method 2: Counting non-zero elements first
    public static void pushZerosToEnd2(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                arr[count++] = arr[i];
            }
        }
        while (count < arr.length) {
            arr[count++] = 0;
        }
    }

    // Method 3: Using separate index for non-zero elements
    public static void pushZerosToEnd3(int[] arr) {
        int nonZeroIndex = 0;
        for (int num : arr) {
            if (num != 0) {
                arr[nonZeroIndex++] = num;
            }
        }
        while (nonZeroIndex < arr.length) {
            arr[nonZeroIndex++] = 0;
        }
    }

    // Method 4: Using Java Streams
    public static void pushZerosToEnd4(int[] arr) {
        int[] nonZeros = Arrays.stream(arr)
                             .filter(num -> num != 0)
                             .toArray();
        System.arraycopy(nonZeros, 0, arr, 0, nonZeros.length);
        Arrays.fill(arr, nonZeros.length, arr.length, 0);
    }

    // Method 5: Using List manipulation
    public static void pushZerosToEnd5(int[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int num : arr) {
            if (num != 0) {
                list.add(num);
            }
        }
        int zerosToAdd = arr.length - list.size();
        for (int i = 0; i < zerosToAdd; i++) {
            list.add(0);
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }
    }

    // Method 6: In-place with while loop
    public static void pushZerosToEnd6(int[] arr) {
        int left = 0, right = 0;
        while (right < arr.length) {
            if (arr[right] != 0) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
            }
            right++;
        }
    }

    // Helper method to print array
    public static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    // Test cases
    public static void main(String[] args) {
        int[][] testCases = {
            {0, 1, 0, 3, 12},
            {1, 0, 2, 0, 0, 3},
            {0, 0, 0, 1},
            {1, 2, 3, 4},
            {0, 0, 0},
            {1}
        };

        String[] methodNames = {
            "Two-pointer technique",
            "Counting non-zeros",
            "Separate index",
            "Java Streams",
            "List manipulation",
            "While loop"
        };

        for (int i = 0; i < testCases.length; i++) {
            System.out.println("\nTest Case " + (i+1) + ": Original - " + Arrays.toString(testCases[i]));
            
            // Make copies of the original array for each method
            int[][] copies = new int[6][];
            for (int j = 0; j < 6; j++) {
                copies[j] = Arrays.copyOf(testCases[i], testCases[i].length);
            }
            
            // Apply each method
            pushZerosToEnd1(copies[0]);
            pushZerosToEnd2(copies[1]);
            pushZerosToEnd3(copies[2]);
            pushZerosToEnd4(copies[3]);
            pushZerosToEnd5(copies[4]);
            pushZerosToEnd6(copies[5]);
            
            // Print results
            for (int j = 0; j < 6; j++) {
                System.out.printf("%-20s: %s\n", methodNames[j], Arrays.toString(copies[j]));
            }
        }
    }
}