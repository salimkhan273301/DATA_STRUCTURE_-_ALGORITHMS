package generalproblem;

public class MaxSubarraySumCircular {

    // Solution 1: Kadane's Algorithm with Circular Check
    public int maxSubarraySumCircular1(int[] arr) {
        int total = 0, maxSum = Integer.MIN_VALUE, currentMax = 0;
        int minSum = Integer.MAX_VALUE, currentMin = 0;
        
        for (int num : arr) {
            total += num;
            
            currentMax = Math.max(num, currentMax + num);
            maxSum = Math.max(maxSum, currentMax);
            
            currentMin = Math.min(num, currentMin + num);
            minSum = Math.min(minSum, currentMin);
        }
        
        if (maxSum < 0) {
            return maxSum;
        }
        
        return Math.max(maxSum, total - minSum);
    }

    // Solution 2: Brute Force with Circular Check
    public int maxSubarraySumCircular2(int[] arr) {
        int maxSum = Integer.MIN_VALUE;
        int n = arr.length;
        
        for (int i = 0; i < n; i++) {
            int currentSum = 0;
            for (int j = 0; j < n; j++) {
                int index = (i + j) % n;
                currentSum += arr[index];
                if (currentSum > maxSum) {
                    maxSum = currentSum;
                }
                if (currentSum < 0) {
                    currentSum = 0;
                }
            }
        }
        
        return maxSum;
    }

    // Solution 3: Prefix Sum and Suffix Sum
    public int maxSubarraySumCircular3(int[] arr) {
        int n = arr.length;
        int[] prefixSum = new int[n];
        int[] suffixSum = new int[n];
        int[] maxPrefix = new int[n];
        int[] maxSuffix = new int[n];
        
        prefixSum[0] = arr[0];
        maxPrefix[0] = arr[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + arr[i];
            maxPrefix[i] = Math.max(maxPrefix[i - 1], prefixSum[i]);
        }
        
        suffixSum[n - 1] = arr[n - 1];
        maxSuffix[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + arr[i];
            maxSuffix[i] = Math.max(maxSuffix[i + 1], suffixSum[i]);
        }
        
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            maxSum = Math.max(maxSum, maxPrefix[i]);
            if (i + 1 < n) {
                maxSum = Math.max(maxSum, maxPrefix[i] + maxSuffix[i + 1]);
            }
        }
        
        return maxSum;
    }

    // Solution 4: Dynamic Programming with Circular Check
    public int maxSubarraySumCircular4(int[] arr) {
        int total = 0, maxSum = Integer.MIN_VALUE, currentMax = 0;
        int minSum = Integer.MAX_VALUE, currentMin = 0;
        boolean allNegative = true;
        
        for (int num : arr) {
            total += num;
            
            currentMax = Math.max(num, currentMax + num);
            maxSum = Math.max(maxSum, currentMax);
            
            currentMin = Math.min(num, currentMin + num);
            minSum = Math.min(minSum, currentMin);
            
            if (num > 0) {
                allNegative = false;
            }
        }
        
        if (allNegative) {
            return maxSum;
        }
        
        return Math.max(maxSum, total - minSum);
    }

    // Solution 5: Two-Pass Kadane's Algorithm
    public int maxSubarraySumCircular5(int[] arr) {
        int n = arr.length;
        int maxSum = Integer.MIN_VALUE, currentMax = 0;
        int total = 0;
        
        for (int num : arr) {
            total += num;
            currentMax = Math.max(num, currentMax + num);
            maxSum = Math.max(maxSum, currentMax);
        }
        
        if (maxSum < 0) {
            return maxSum;
        }
        
        int minSum = Integer.MAX_VALUE, currentMin = 0;
        for (int num : arr) {
            currentMin = Math.min(num, currentMin + num);
            minSum = Math.min(minSum, currentMin);
        }
        
        return Math.max(maxSum, total - minSum);
    }

    // Main method to test all solutions
    public static void main(String[] args) {
        MaxSubarraySumCircular solution = new MaxSubarraySumCircular();
        int[] arr1 = {8, -8, 9, -9, 10, -11, 12};
        int[] arr2 = {10, -3, -4, 7, 6, 5, -4, -1};
        int[] arr3 = {5, -2, 3, 4};
        
        System.out.println("Solution 1:");
        System.out.println(solution.maxSubarraySumCircular1(arr1)); // 22
        System.out.println(solution.maxSubarraySumCircular1(arr2)); // 23
        System.out.println(solution.maxSubarraySumCircular1(arr3)); // 12
        
        System.out.println("\nSolution 2:");
        System.out.println(solution.maxSubarraySumCircular2(arr1)); // 22
        System.out.println(solution.maxSubarraySumCircular2(arr2)); // 23
        System.out.println(solution.maxSubarraySumCircular2(arr3)); // 12
        
        System.out.println("\nSolution 3:");
        System.out.println(solution.maxSubarraySumCircular3(arr1)); // 22
        System.out.println(solution.maxSubarraySumCircular3(arr2)); // 23
        System.out.println(solution.maxSubarraySumCircular3(arr3)); // 12
        
        System.out.println("\nSolution 4:");
        System.out.println(solution.maxSubarraySumCircular4(arr1)); // 22
        System.out.println(solution.maxSubarraySumCircular4(arr2)); // 23
        System.out.println(solution.maxSubarraySumCircular4(arr3)); // 12
        
        System.out.println("\nSolution 5:");
        System.out.println(solution.maxSubarraySumCircular5(arr1)); // 22
        System.out.println(solution.maxSubarraySumCircular5(arr2)); // 23
        System.out.println(solution.maxSubarraySumCircular5(arr3)); // 12
    }
}