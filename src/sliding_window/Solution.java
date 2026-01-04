package sliding_window;

import java.util.*;

public class Solution {

    // 1. Sliding Window (Best Overall)
    public int slidingWindow(int[] nums, int k) {
        if (k <= 1) return 0;

        int prod = 1, left = 0, count = 0;

        for (int right = 0; right < nums.length; right++) {
            prod *= nums[right];

            while (prod >= k) {
                prod /= nums[left++];
            }

            count += right - left + 1;
        }

        return count;
    }

    // 2. Brute Force
    public int bruteForce(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int prod = 1;
            for (int j = i; j < nums.length; j++) {
                prod *= nums[j];
                if (prod < k) count++;
                else break;
            }
        }
        return count;
    }

    // 3. Prefix Log + Binary Search
    public int prefixLogBinarySearch(int[] nums, int k) {
        if (k <= 1) return 0;

        int n = nums.length;
        double logK = Math.log(k);
        double[] prefixLog = new double[n + 1];

        for (int i = 0; i < n; i++) {
            prefixLog[i + 1] = prefixLog[i] + Math.log(nums[i]);
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            int low = i + 1, high = n + 1;
            while (low < high) {
                int mid = (low + high) / 2;
                if (prefixLog[mid] - prefixLog[i] < logK)
                    low = mid + 1;
                else
                    high = mid;
            }
            count += low - i - 1;
        }

        return count;
    }

    // 4. Recursive (for learning, not efficient)
    public int recursiveSubarrays(int[] nums, int k) {
        return recursiveHelper(nums, 0, k);
    }

    private int recursiveHelper(int[] nums, int start, int k) {
        if (start >= nums.length) return 0;

        int prod = 1;
        int count = 0;

        for (int i = start; i < nums.length; i++) {
            prod *= nums[i];
            if (prod < k) count++;
            else break;
        }

        return count + recursiveHelper(nums, start + 1, k);
    }

    // 5. Dynamic Programming (Similar to Brute Force but with storage, inefficient)
    public int dpApproach(int[] nums, int k) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        int count = 0;

        for (int i = 0; i < n; i++) {
            dp[i][i] = nums[i];
            if (dp[i][i] < k) count++;
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = dp[i][j - 1] * nums[j];
                if (dp[i][j] < k) count++;
                else break;
            }
        }

        return count;
    }

    // 🔹 Test runner
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] nums1 = {10, 5, 2, 6};
        int k1 = 100;

        System.out.println("Sliding Window: " + sol.slidingWindow(nums1, k1));            // 8
        System.out.println("Brute Force: " + sol.bruteForce(nums1, k1));                  // 8
        System.out.println("Prefix Log + Binary Search: " + sol.prefixLogBinarySearch(nums1, k1)); // 8
        System.out.println("Recursive: " + sol.recursiveSubarrays(nums1, k1));             // 8
        System.out.println("DP-Based (Inefficient): " + sol.dpApproach(nums1, k1));        // 8

        // You can add more test cases as needed:
        int[] nums2 = {1, 2, 3};
        int k2 = 0;
        System.out.println("\nTest case 2 (k=0): " + sol.slidingWindow(nums2, k2));        // 0

        int[] nums3 = {1, 1, 1};
        int k3 = 2;
        System.out.println("Test case 3 (all ones): " + sol.slidingWindow(nums3, k3));     // 6
    }
}
