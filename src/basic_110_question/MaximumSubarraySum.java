package basic_110_question;

import java.util.*;

public class MaximumSubarraySum {
    
    // Solution 1: Kadane's Algorithm (Basic)
    public static int maxSubarraySumKadane(int[] arr) {
        if (arr.length == 0) return 0;
        
        int maxCurrent = arr[0];
        int maxGlobal = arr[0];
        
        for (int i = 1; i < arr.length; i++) {
            maxCurrent = Math.max(arr[i], maxCurrent + arr[i]);
            maxGlobal = Math.max(maxGlobal, maxCurrent);
        }
        
        return maxGlobal;
    }
    
    // Solution 2: Kadane's with indices (get subarray also)
    public static SubarrayResult maxSubarrayWithIndices(int[] arr) {
        if (arr.length == 0) return new SubarrayResult(0, -1, -1);
        
        int maxCurrent = arr[0];
        int maxGlobal = arr[0];
        int tempStart = 0, start = 0, end = 0;
        
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > maxCurrent + arr[i]) {
                maxCurrent = arr[i];
                tempStart = i;
            } else {
                maxCurrent = maxCurrent + arr[i];
            }
            
            if (maxCurrent > maxGlobal) {
                maxGlobal = maxCurrent;
                start = tempStart;
                end = i;
            }
        }
        
        return new SubarrayResult(maxGlobal, start, end);
    }
    
    // Solution 3: Brute Force (O(n³))
    public static int maxSubarraySumBruteForce(int[] arr) {
        int maxSum = Integer.MIN_VALUE;
        
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                }
                maxSum = Math.max(maxSum, sum);
            }
        }
        
        return maxSum;
    }
    
    // Solution 4: Optimized Brute Force (O(n²))
    public static int maxSubarraySumOptimizedBrute(int[] arr) {
        int maxSum = Integer.MIN_VALUE;
        
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                maxSum = Math.max(maxSum, sum);
            }
        }
        
        return maxSum;
    }
    
    // Solution 5: Divide and Conquer
    public static int maxSubarraySumDivideConquer(int[] arr) {
        return maxSubarraySumDC(arr, 0, arr.length - 1);
    }
    
    private static int maxSubarraySumDC(int[] arr, int left, int right) {
        if (left == right) return arr[left];
        
        int mid = left + (right - left) / 2;
        
        int leftMax = maxSubarraySumDC(arr, left, mid);
        int rightMax = maxSubarraySumDC(arr, mid + 1, right);
        int crossMax = maxCrossingSum(arr, left, mid, right);
        
        return Math.max(Math.max(leftMax, rightMax), crossMax);
    }
    
    private static int maxCrossingSum(int[] arr, int left, int mid, int right) {
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        
        for (int i = mid; i >= left; i--) {
            sum += arr[i];
            leftSum = Math.max(leftSum, sum);
        }
        
        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        
        for (int i = mid + 1; i <= right; i++) {
            sum += arr[i];
            rightSum = Math.max(rightSum, sum);
        }
        
        return leftSum + rightSum;
    }
    
    // Solution 6: For circular array
    public static int maxSubarraySumCircular(int[] arr) {
        int maxKadane = maxSubarraySumKadane(arr);
        
        int totalSum = 0;
        for (int i = 0; i < arr.length; i++) {
            totalSum += arr[i];
            arr[i] = -arr[i];
        }
        
        int maxInverted = maxSubarraySumKadane(arr);
        int maxCircular = totalSum + maxInverted;
        
        // Restore array
        for (int i = 0; i < arr.length; i++) {
            arr[i] = -arr[i];
        }
        
        if (maxCircular == 0) return maxKadane;
        return Math.max(maxKadane, maxCircular);
    }
    
    static class SubarrayResult {
        int sum;
        int start;
        int end;
        
        SubarrayResult(int sum, int start, int end) {
            this.sum = sum;
            this.start = start;
            this.end = end;
        }
    }
    
    public static void main(String[] args) {
        int[][] testArrays = {
            {-2, -3, 4, -1, -2, 1, 5, -3},
            {1, 2, 3, 4, 5},
            {-1, -2, -3, -4},
            {5, -4, -2, 6, -1, 3},
            {-2, 1, -3, 4, -1, 2, 1, -5, 4}
        };
        
        for (int i = 0; i < testArrays.length; i++) {
            int[] arr = testArrays[i].clone();
            System.out.println("\n--- Test Array " + (i + 1) + ": " + Arrays.toString(arr) + " ---");
            
            System.out.println("1. Kadane's Algorithm: " + maxSubarraySumKadane(arr));
            
            SubarrayResult result = maxSubarrayWithIndices(arr);
            System.out.println("2. With Indices: Sum=" + result.sum + 
                             ", Subarray=" + Arrays.toString(Arrays.copyOfRange(arr, result.start, result.end + 1)));
            
            System.out.println("3. Brute Force: " + maxSubarraySumBruteForce(arr));
            System.out.println("4. Optimized Brute: " + maxSubarraySumOptimizedBrute(arr));
            System.out.println("5. Divide & Conquer: " + maxSubarraySumDivideConquer(arr));
            System.out.println("6. Circular Array: " + maxSubarraySumCircular(arr.clone()));
        }
    }
}