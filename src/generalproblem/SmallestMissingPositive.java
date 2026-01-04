package generalproblem;

import java.util.HashSet;
import java.util.Set;

public class SmallestMissingPositive {

    // Method to find the smallest missing positive integer in the array
    public static int firstMissingPositive(int[] nums) {
        // Create a HashSet to store all positive numbers from the array
        Set<Integer> set = new HashSet<>();
        
        // Iterate through each element in the array
        for (int num : nums) {
            // Only add positive numbers to the set
            if (num > 0) {
                set.add(num);
            }
        }
        
        // Check for the smallest missing positive integer starting from 1
        for (int i = 1; i <= nums.length + 1; i++) {
            // If the current number is not in the set, it's the missing number
            if (!set.contains(i)) {
                return i;
            }
        }
        
        // If all numbers from 1 to nums.length + 1 are present, return nums.length + 1
        return nums.length + 1;
    }

    public static void main(String[] args) {
        // Test Case 1: Smallest missing positive is 3
        int[] arr1 = {2, -3, 4, 1, 1, 7};
        System.out.println("Test Case 1:");
        System.out.println("Input: [2, -3, 4, 1, 1, 7]");
        System.out.println("Expected Output: 3");
        System.out.println("Actual Output: " + firstMissingPositive(arr1));
        System.out.println();

        // Test Case 2: Smallest missing positive is 4
        int[] arr2 = {5, 3, 2, 5, 1};
        System.out.println("Test Case 2:");
        System.out.println("Input: [5, 3, 2, 5, 1]");
        System.out.println("Expected Output: 4");
        System.out.println("Actual Output: " + firstMissingPositive(arr2));
        System.out.println();

        // Test Case 3: Smallest missing positive is 1 (all non-positive numbers)
        int[] arr3 = {-8, 0, -1, -4, -3};
        System.out.println("Test Case 3:");
        System.out.println("Input: [-8, 0, -1, -4, -3]");
        System.out.println("Expected Output: 1");
        System.out.println("Actual Output: " + firstMissingPositive(arr3));
        System.out.println();

        // Test Case 4: Smallest missing positive is 2 (1 is present)
        int[] arr4 = {1, 3, 4};
        System.out.println("Test Case 4:");
        System.out.println("Input: [1, 3, 4]");
        System.out.println("Expected Output: 2");
        System.out.println("Actual Output: " + firstMissingPositive(arr4));
        System.out.println();

        // Test Case 5: All numbers from 1 to n are present, missing is n+1
        int[] arr5 = {1, 2, 3, 4, 5};
        System.out.println("Test Case 5:");
        System.out.println("Input: [1, 2, 3, 4, 5]");
        System.out.println("Expected Output: 6");
        System.out.println("Actual Output: " + firstMissingPositive(arr5));
        System.out.println();
    }
}