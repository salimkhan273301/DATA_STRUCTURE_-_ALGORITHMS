package com.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DuplicateFinder {

    // 1. Brute Force (Nested Loop) - O(n²)
    public int findDuplicateBruteForce(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return nums[i];
                }
            }
        }
        return -1;
    }

    // 2. Sorting Approach - O(n log n)
    public int findDuplicateSorting(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return nums[i];
            }
        }
        return -1;
    }

    // 3. HashSet - O(n) space
    public int findDuplicateHashSet(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (seen.contains(num)) {
                return num;
            }
            seen.add(num);
        }
        return -1;
    }

    // 4. Negative Marking - O(n), modifies input
    public int findDuplicateNegativeMarking(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]);
            if (nums[index] < 0) {
                return index;
            }
            nums[index] = -nums[index];
        }
        return -1;
    }

    // 5. Floyd's Tortoise and Hare - O(n), optimal
    public int findDuplicateFloyd(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];
        
        // Phase 1: Detect cycle
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        
        // Phase 2: Find duplicate (cycle entrance)
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        
        return slow;
    }

    // Test all methods
    public static void main(String[] args) {
        DuplicateFinder finder = new DuplicateFinder();
        
        // Test cases
        int[][] testCases = {
            {1, 3, 4, 2, 2},  // Small array with duplicate at end
            {3, 1, 3, 4, 2},  // Duplicate at beginning
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 5},  // Large array with duplicate in middle
            {2, 2, 2, 2, 2}   // All elements same
        };
        
        String[] methodNames = {
            "Brute Force",
            "Sorting",
            "HashSet",
            "Negative Marking",
            "Floyd's Algorithm"
        };
        
        for (int i = 0; i < testCases.length; i++) {
            System.out.println("\nTest Case " + (i+1) + ": " + Arrays.toString(testCases[i]));
            
            // Need to clone array for Negative Marking since it modifies input
            int[] nums = testCases[i].clone();
            System.out.println(methodNames[0] + ": " + finder.findDuplicateBruteForce(nums));
            
            nums = testCases[i].clone();
            System.out.println(methodNames[1] + ": " + finder.findDuplicateSorting(nums));
            
            nums = testCases[i].clone();
            System.out.println(methodNames[2] + ": " + finder.findDuplicateHashSet(nums));
            
            nums = testCases[i].clone();
            System.out.println(methodNames[3] + ": " + finder.findDuplicateNegativeMarking(nums));
            
            nums = testCases[i].clone();
            System.out.println(methodNames[4] + ": " + finder.findDuplicateFloyd(nums));
        }
    }
}