package sliding_window;

import java.util.*;

public class ContiguasSubarrayDequeTechnique {
    // ✅ Main method that implements the sliding window + deque logic
    public long continuousSubarrays(int[] nums) {
        Deque<Integer> maxDeque = new ArrayDeque<>();
        Deque<Integer> minDeque = new ArrayDeque<>();
        long count = 0;
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            // Maintain decreasing max deque
            while (!maxDeque.isEmpty() && nums[maxDeque.peekLast()] < nums[right])
                maxDeque.pollLast();
            maxDeque.addLast(right);

            // Maintain increasing min deque
            while (!minDeque.isEmpty() && nums[minDeque.peekLast()] > nums[right])
                minDeque.pollLast();
            minDeque.addLast(right);

            // Shrink the window if invalid
            while (nums[maxDeque.peekFirst()] - nums[minDeque.peekFirst()] > 2) {
                if (maxDeque.peekFirst() == left) maxDeque.pollFirst();
                if (minDeque.peekFirst() == left) minDeque.pollFirst();
                left++;
            }

            // All subarrays from left to right are valid
            count += right - left + 1;
        }

        return count;
    }

    // ✅ Main method to run test cases
    public static void main(String[] args) {
    	ContiguasSubarrayDequeTechnique sol = new ContiguasSubarrayDequeTechnique();

        // Test Case 1
        int[] nums1 = {4, 2, 3, 6};
        System.out.println("Test 1 Output: " + sol.continuousSubarrays(nums1)); // Expected: 7

        // Test Case 2
        int[] nums2 = {5, 5, 5, 5};
        System.out.println("Test 2 Output: " + sol.continuousSubarrays(nums2)); // Expected: 10

        // Test Case 3
        int[] nums3 = {1, 2, 3, 4};
        System.out.println("Test 3 Output: " + sol.continuousSubarrays(nums3)); // Expected: 9

        // Test Case 4
        int[] nums4 = {1, 10, 1, 10};
        System.out.println("Test 4 Output: " + sol.continuousSubarrays(nums4)); // Expected: 4

        // Test Case 5: Large uniform array
        int[] nums5 = new int[100000];
        Arrays.fill(nums5, 1_000_000_000);
        System.out.println("Test 5 Output: " + sol.continuousSubarrays(nums5)); // Expected: 5000050000
    }
}
