package com.leetcode;

public class LongestSubarrayOfOnesAfterDeletingOneElement {

    public static void main(String[] args) {
        // Test cases
        int[] test1 = {1,1,0,1}; // Expected: 3
        int[] test2 = {0,1,1,1,0,1,1,0,1}; // Expected: 5
        int[] test3 = {1,1,1}; // Expected: 2
        int[] test4 = {1,0,1,0,1,0}; // Expected: 2
        int[] test5 = {0,0,0}; // Expected: 0

        System.out.println("Brute Force:");
        System.out.println(longestSubarrayBruteForce(test1) + " (Expected: 3)");
        System.out.println(longestSubarrayBruteForce(test2) + " (Expected: 5)");
        System.out.println(longestSubarrayBruteForce(test3) + " (Expected: 2)");
        
        System.out.println("\nSliding Window:");
        System.out.println(longestSubarraySlidingWindow(test1) + " (Expected: 3)");
        System.out.println(longestSubarraySlidingWindow(test2) + " (Expected: 5)");
        System.out.println(longestSubarraySlidingWindow(test3) + " (Expected: 2)");
        
        System.out.println("\nPrefix & Suffix Arrays:");
        System.out.println(longestSubarrayPrefixSuffix(test1) + " (Expected: 3)");
        System.out.println(longestSubarrayPrefixSuffix(test2) + " (Expected: 5)");
        System.out.println(longestSubarrayPrefixSuffix(test3) + " (Expected: 2)");
        
        System.out.println("\nOptimized Space Approach:");
        System.out.println(longestSubarrayOptimizedSpace(test1) + " (Expected: 3)");
        System.out.println(longestSubarrayOptimizedSpace(test2) + " (Expected: 5)");
        System.out.println(longestSubarrayOptimizedSpace(test3) + " (Expected: 2)");
    }

    // Approach 1: Brute Force
    // Time: O(n²) - We check all possible subarrays for each deleted element
    // Space: O(1) - No extra space used
    public static int longestSubarrayBruteForce(int[] nums) {
        int maxLen = 0;
        for (int i = 0; i < nums.length; i++) {
            int currentLen = 0;
            for (int j = 0; j < nums.length; j++) {
                if (j == i) continue; // skip the deleted element
                if (nums[j] == 1) {
                    currentLen++;
                    maxLen = Math.max(maxLen, currentLen);
                } else {
                    currentLen = 0;
                }
            }
        }
        return maxLen;
    }

    // Approach 2: Sliding Window (Optimal)
    // Time: O(n) - Single pass through the array
    // Space: O(1) - Constant space for pointers and counters
    public static int longestSubarraySlidingWindow(int[] nums) {
        int left = 0;
        int zeroCount = 0;
        int maxLen = 0;
        
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                zeroCount++;
            }
            
            // Shrink window until we have at most one zero
            while (zeroCount > 1) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }
            
            maxLen = Math.max(maxLen, right - left);
        }
        
        // If all 1's, we must delete one element
        return maxLen == nums.length ? maxLen - 1 : maxLen;
    }

    // Approach 3: Prefix and Suffix Arrays
    // Time: O(n) - Three passes through the array
    // Space: O(n) - Additional arrays to store prefix and suffix counts
    public static int longestSubarrayPrefixSuffix(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        
        int[] prefix = new int[n];
        int[] suffix = new int[n];
        
        // Build prefix array (count of consecutive 1's up to index i)
        prefix[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = nums[i] == 1 ? prefix[i - 1] + 1 : 0;
        }
        
        // Build suffix array (count of consecutive 1's from index i to end)
        suffix[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = nums[i] == 1 ? suffix[i + 1] + 1 : 0;
        }
        
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            int left = i > 0 ? prefix[i - 1] : 0;
            int right = i < n - 1 ? suffix[i + 1] : 0;
            maxLen = Math.max(maxLen, left + right);
        }
        
        return maxLen;
    }

    // Approach 4: Optimized Space Approach
    // Time: O(n) - Single pass through the array
    // Space: O(1) - Constant space for tracking lengths
    public static int longestSubarrayOptimizedSpace(int[] nums) {
        int prevLen = 0; // Length of previous segment of 1's
        int currLen = 0; // Length of current segment of 1's
        int maxLen = 0;
        boolean hasZero = false;
        
        for (int num : nums) {
            if (num == 1) {
                currLen++;
            } else {
                // When we hit a 0, combine previous and current segments
                maxLen = Math.max(maxLen, prevLen + currLen);
                prevLen = currLen;
                currLen = 0;
                hasZero = true;
            }
        }
        // Check one last time in case array ends with 1's
        maxLen = Math.max(maxLen, prevLen + currLen);
        
        // If no zeros, we must delete one 1
        return hasZero ? maxLen : maxLen - 1;
    }
}