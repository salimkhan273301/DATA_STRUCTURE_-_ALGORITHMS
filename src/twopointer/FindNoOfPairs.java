package twopointer;

import java.util.HashMap;

/**
 * Solution class with 5 different approaches to count pairs summing to target
 * Time and Space Complexity analysis for each method
 */
 class FindNoOfPair {
    
    /**
     * Solution 1: Two Pointers (Optimal for Sorted Arrays)
     * Technique: Two pointers moving towards each other
     * Time Complexity: O(n) - Single pass through array
     * Space Complexity: O(1) - Constant extra space
     */
    int countPairsTwoPointers(int arr[], int target) {
        int count = 0;
        int left = 0, right = arr.length - 1;
        
        while (left < right) {
            int sum = arr[left] + arr[right];
            
            if (sum == target) {
                // If all elements between left and right are same
                if (arr[left] == arr[right]) {
                    int n = right - left + 1;          // Count of same elements
                    count += n * (n - 1) / 2;         // nC2 combinations
                    break;                             // Exit since all pairs counted
                }
                
                // Count duplicates on left side
                int leftCount = 1;
                while (left + 1 < right && arr[left] == arr[left + 1]) {
                    leftCount++;  // Increment duplicate count
                    left++;       // Move left pointer
                }
                
                // Count duplicates on right side
                int rightCount = 1;
                while (right - 1 > left && arr[right] == arr[right - 1]) {
                    rightCount++; // Increment duplicate count
                    right--;      // Move right pointer
                }
                
                count += leftCount * rightCount; // All combinations of left and right duplicates
                left++;   // Move to next distinct left element
                right--;  // Move to next distinct right element
            } 
            else if (sum < target) {
                left++;   // Need larger sum, move left pointer right
            } 
            else {
                right--;  // Need smaller sum, move right pointer left
            }
        }
        return count;
    }
    
    /**
     * Solution 2: Hash Map (Optimal for Unsorted Arrays)
     * Technique: Frequency counting using hash map
     * Time Complexity: O(n) - Single pass through array
     * Space Complexity: O(n) - Storage for frequency map
     */
    int countPairsHashMap(int arr[], int target) {
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        int count = 0;
        
        for (int num : arr) {
            int complement = target - num;            // Calculate required complement
            if (freqMap.containsKey(complement)) {
                count += freqMap.get(complement);     // Add all previous occurrences of complement
            }
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1); // Update frequency
        }
        
        return count;
    }
    
    /**
     * Solution 3: Binary Search (For Sorted Arrays)
     * Technique: Binary search for complement for each element
     * Time Complexity: O(n log n) - n elements × log n search each
     * Space Complexity: O(1) - No extra space
     */
    int countPairsBinarySearch(int arr[], int target) {
        int count = 0;
        int n = arr.length;
        
        for (int i = 0; i < n - 1; i++) {
            int complement = target - arr[i];         // Required complement
            int left = i + 1, right = n - 1;
            
            // Find first occurrence of complement using binary search
            int first = -1;
            while (left <= right) {
                int mid = left + (right - left) / 2;  // Avoid overflow
                if (arr[mid] >= complement) {
                    first = mid;      // Potential first occurrence
                    right = mid - 1;  // Search left half
                } else {
                    left = mid + 1;   // Search right half
                }
            }
            
            // If complement not found, continue
            if (first == -1 || arr[first] != complement) continue;
            
            // Find last occurrence of complement
            left = first;
            right = n - 1;
            int last = first;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (arr[mid] <= complement) {
                    last = mid;       // Potential last occurrence
                    left = mid + 1;   // Search right half
                } else {
                    right = mid - 1;  // Search left half
                }
            }
            
            count += (last - first + 1); // Count all occurrences between first and last
        }
        
        return count;
    }
    
    /**
     * Solution 4: Brute Force (Simple but Inefficient)
     * Technique: Check all possible pairs
     * Time Complexity: O(n²) - Nested loops
     * Space Complexity: O(1) - No extra space
     */
    int countPairsBruteForce(int arr[], int target) {
        int count = 0;
        int n = arr.length;
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {        // j starts from i+1 to avoid same index
                if (arr[i] + arr[j] == target) {
                    count++;                         // Count valid pair
                }
            }
        }
        
        return count;
    }
    
    /**
     * Solution 5: Two Pointers with Frequency Count (Enhanced)
     * Technique: Two pointers with separate frequency counting
     * Time Complexity: O(n) - Single pass
     * Space Complexity: O(1) - Constant space
     */
    int countPairsTwoPointersFreq(int arr[], int target) {
        int count = 0;
        int left = 0, right = arr.length - 1;
        
        while (left < right) {
            int sum = arr[left] + arr[right];
            
            if (sum == target) {
                // Store current values and count frequencies separately
                int leftVal = arr[left];
                int leftCount = 0;
                
                // Count all duplicates of left value
                while (left <= right && arr[left] == leftVal) {
                    leftCount++;
                    left++;  // Move left pointer while counting
                }
                
                int rightVal = arr[right];
                int rightCount = 0;
                
                // Count all duplicates of right value
                while (right >= left - 1 && arr[right] == rightVal) {
                    rightCount++;
                    right--; // Move right pointer while counting
                }
                
                count += leftCount * rightCount; // All combinations
            } 
            else if (sum < target) {
                left++;   // Need larger sum
            } 
            else {
                right--;  // Need smaller sum
            }
        }
        
        return count;
    }
}

/**
 * Test class to validate all solutions with various test cases
 */
public class FindNoOfPairs {
    public static void main(String[] args) {
    	FindNoOfPair sol = new FindNoOfPair();
        
        // Test Case 1: Normal case with duplicates
        int[] arr1 = {-1, 1, 5, 5, 7};
        System.out.println("Test Case 1 [-1, 1, 5, 5, 7], target=6");
        System.out.println("Two Pointers: " + sol.countPairsTwoPointers(arr1, 6)); // Expected: 3
        System.out.println("Hash Map: " + sol.countPairsHashMap(arr1, 6));         // Expected: 3
        System.out.println("Binary Search: " + sol.countPairsBinarySearch(arr1, 6)); // Expected: 3
        System.out.println("Brute Force: " + sol.countPairsBruteForce(arr1, 6));   // Expected: 3
        System.out.println("Two Pointers Freq: " + sol.countPairsTwoPointersFreq(arr1, 6)); // Expected: 3
        System.out.println();
        
        // Test Case 2: All same elements
        int[] arr2 = {1, 1, 1, 1};
        System.out.println("Test Case 2 [1, 1, 1, 1], target=2");
        System.out.println("Two Pointers: " + sol.countPairsTwoPointers(arr2, 2)); // Expected: 6
        System.out.println("Hash Map: " + sol.countPairsHashMap(arr2, 2));         // Expected: 6
        System.out.println("Binary Search: " + sol.countPairsBinarySearch(arr2, 2)); // Expected: 6
        System.out.println("Brute Force: " + sol.countPairsBruteForce(arr2, 2));   // Expected: 6
        System.out.println("Two Pointers Freq: " + sol.countPairsTwoPointersFreq(arr2, 2)); // Expected: 6
        System.out.println();
        
        // Test Case 3: Consecutive numbers
        int[] arr3 = {1, 2, 3, 4, 5, 6};
        System.out.println("Test Case 3 [1, 2, 3, 4, 5, 6], target=7");
        System.out.println("Two Pointers: " + sol.countPairsTwoPointers(arr3, 7)); // Expected: 3
        System.out.println("Hash Map: " + sol.countPairsHashMap(arr3, 7));         // Expected: 3
        System.out.println("Binary Search: " + sol.countPairsBinarySearch(arr3, 7)); // Expected: 3
        System.out.println("Brute Force: " + sol.countPairsBruteForce(arr3, 7));   // Expected: 3
        System.out.println("Two Pointers Freq: " + sol.countPairsTwoPointersFreq(arr3, 7)); // Expected: 3
        System.out.println();
        
        // Test Case 4: Multiple duplicates
        int[] arr4 = {1, 3, 3, 3, 5};
        System.out.println("Test Case 4 [1, 3, 3, 3, 5], target=6");
        System.out.println("Two Pointers: " + sol.countPairsTwoPointers(arr4, 6)); // Expected: 4
        System.out.println("Hash Map: " + sol.countPairsHashMap(arr4, 6));         // Expected: 4
        System.out.println("Binary Search: " + sol.countPairsBinarySearch(arr4, 6)); // Expected: 4
        System.out.println("Brute Force: " + sol.countPairsBruteForce(arr4, 6));   // Expected: 4
        System.out.println("Two Pointers Freq: " + sol.countPairsTwoPointersFreq(arr4, 6)); // Expected: 4
        System.out.println();
        
        // Test Case 5: Single element
        int[] arr5 = {1};
        System.out.println("Test Case 5 [1], target=2");
        System.out.println("Two Pointers: " + sol.countPairsTwoPointers(arr5, 2)); // Expected: 0
        System.out.println("Hash Map: " + sol.countPairsHashMap(arr5, 2));         // Expected: 0
        System.out.println("Binary Search: " + sol.countPairsBinarySearch(arr5, 2)); // Expected: 0
        System.out.println("Brute Force: " + sol.countPairsBruteForce(arr5, 2));   // Expected: 0
        System.out.println("Two Pointers Freq: " + sol.countPairsTwoPointersFreq(arr5, 2)); // Expected: 0
        System.out.println();
        
        // Test Case 6: Empty array
        int[] arr6 = {};
        System.out.println("Test Case 6 [], target=5");
        System.out.println("Two Pointers: " + sol.countPairsTwoPointers(arr6, 5)); // Expected: 0
        System.out.println("Hash Map: " + sol.countPairsHashMap(arr6, 5));         // Expected: 0
        System.out.println("Binary Search: " + sol.countPairsBinarySearch(arr6, 5)); // Expected: 0
        System.out.println("Brute Force: " + sol.countPairsBruteForce(arr6, 5));   // Expected: 0
        System.out.println("Two Pointers Freq: " + sol.countPairsTwoPointersFreq(arr6, 5)); // Expected: 0
    }
}
