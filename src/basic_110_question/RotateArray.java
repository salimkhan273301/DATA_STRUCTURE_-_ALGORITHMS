package basic_110_question;

import java.util.*;

public class RotateArray {
    
    // Solution 1: Using Temporary Array
    public static void rotateUsingTemp(int[] arr, int k) {
        if (arr.length <= 1) return;
        
        k = k % arr.length;
        if (k == 0) return;
        
        int[] temp = new int[k];
        
        // Store last k elements
        for (int i = 0; i < k; i++) {
            temp[i] = arr[arr.length - k + i];
        }
        
        // Shift first n-k elements to the right
        for (int i = arr.length - 1; i >= k; i--) {
            arr[i] = arr[i - k];
        }
        
        // Place temp elements at the beginning
        for (int i = 0; i < k; i++) {
            arr[i] = temp[i];
        }
    }
    
    // Solution 2: Using Reversal Algorithm
    public static void rotateUsingReversal(int[] arr, int k) {
        if (arr.length <= 1) return;
        
        k = k % arr.length;
        if (k == 0) return;
        
        // Reverse entire array
        reverse(arr, 0, arr.length - 1);
        // Reverse first k elements
        reverse(arr, 0, k - 1);
        // Reverse remaining elements
        reverse(arr, k, arr.length - 1);
    }
    
    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
    
    // Solution 3: Rotate One by One (Juggling Algorithm)
    public static void rotateOneByOne(int[] arr, int k) {
        if (arr.length <= 1) return;
        
        k = k % arr.length;
        if (k == 0) return;
        
        for (int i = 0; i < k; i++) {
            rotateByOne(arr);
        }
    }
    
    private static void rotateByOne(int[] arr) {
        int last = arr[arr.length - 1];
        for (int i = arr.length - 1; i > 0; i--) {
            arr[i] = arr[i - 1];
        }
        arr[0] = last;
    }
    
    // Solution 4: Using Cyclic Replacements
    public static void rotateCyclic(int[] arr, int k) {
        if (arr.length <= 1) return;
        
        k = k % arr.length;
        if (k == 0) return;
        
        int count = 0;
        for (int start = 0; count < arr.length; start++) {
            int current = start;
            int prev = arr[start];
            
            do {
                int next = (current + k) % arr.length;
                int temp = arr[next];
                arr[next] = prev;
                prev = temp;
                current = next;
                count++;
            } while (start != current);
        }
    }
    
    // Solution 5: Using Collections (List rotation)
    public static void rotateUsingList(Integer[] arr, int k) {
        if (arr.length <= 1) return;
        
        List<Integer> list = Arrays.asList(arr);
        Collections.rotate(list, k);
    }
    
    // Solution 6: Left rotation (opposite direction)
    public static void rotateLeft(int[] arr, int k) {
        if (arr.length <= 1) return;
        
        k = k % arr.length;
        if (k == 0) return;
        
        reverse(arr, 0, k - 1);
        reverse(arr, k, arr.length - 1);
        reverse(arr, 0, arr.length - 1);
    }
    
    // Bonus: Rotate 2D matrix
    public static void rotateMatrix90Clockwise(int[][] matrix) {
        int n = matrix.length;
        
        // Transpose
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        
        // Reverse each row
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = temp;
            }
        }
    }
    
    public static void main(String[] args) {
        int[] original = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        
        System.out.println("Original Array: " + Arrays.toString(original));
        
        // Test Solution 1
        int[] arr1 = original.clone();
        rotateUsingTemp(arr1, k);
        System.out.println("\n1. Using Temp Array (rotate right by " + k + "): " + Arrays.toString(arr1));
        
        // Test Solution 2
        int[] arr2 = original.clone();
        rotateUsingReversal(arr2, k);
        System.out.println("2. Using Reversal: " + Arrays.toString(arr2));
        
        // Test Solution 3
        int[] arr3 = original.clone();
        rotateOneByOne(arr3, k);
        System.out.println("3. Rotate One by One: " + Arrays.toString(arr3));
        
        // Test Solution 4
        int[] arr4 = original.clone();
        rotateCyclic(arr4, k);
        System.out.println("4. Using Cyclic: " + Arrays.toString(arr4));
        
        // Test Solution 5
        Integer[] arr5 = {1, 2, 3, 4, 5, 6, 7};
        rotateUsingList(arr5, k);
        System.out.println("5. Using Collections: " + Arrays.toString(arr5));
        
        // Test Solution 6
        int[] arr6 = original.clone();
        rotateLeft(arr6, k);
        System.out.println("6. Left Rotation by " + k + ": " + Arrays.toString(arr6));
        
        // Test 2D rotation
        System.out.println("\n--- 2D Matrix Rotation ---");
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        System.out.println("Original Matrix:");
        printMatrix(matrix);
        rotateMatrix90Clockwise(matrix);
        System.out.println("Rotated 90° Clockwise:");
        printMatrix(matrix);
    }
    
    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}