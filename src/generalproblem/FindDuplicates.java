package generalproblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindDuplicates {

    // 1. Brute Force (Nested Loops)
    // Time Complexity: O(n²)
    // Space Complexity: O(1)
    public static List<Integer> findDuplicatesBruteForce(int[] arr) {
        List<Integer> duplicates = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j] && !duplicates.contains(arr[i])) {
                    duplicates.add(arr[i]);
                    break;
                }
            }
        }
        return duplicates;
    }

    // 2. Sorting and Adjacent Check
    // Time Complexity: O(n log n)
    // Space Complexity: O(1) (if modifying the input array) or O(n) (if not)
    public static List<Integer> findDuplicatesSorting(int[] arr) {
        List<Integer> duplicates = new ArrayList<>();
        Arrays.sort(arr);
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1] && (duplicates.isEmpty() || duplicates.get(duplicates.size() - 1) != arr[i])) {
                duplicates.add(arr[i]);
            }
        }
        return duplicates;
    }

    // 3. Hash Set
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public static List<Integer> findDuplicatesHashSet(int[] arr) {
        List<Integer> duplicates = new ArrayList<>();
        Set<Integer> seen = new HashSet<>();
        for (int num : arr) {
            if (seen.contains(num)) {
                duplicates.add(num);
            } else {
                seen.add(num);
            }
        }
        return duplicates;
    }

    // 4. Frequency Array
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public static List<Integer> findDuplicatesFrequencyArray(int[] arr) {
        List<Integer> duplicates = new ArrayList<>();
        int[] frequency = new int[arr.length + 1];
        for (int num : arr) {
            frequency[num]++;
        }
        for (int i = 1; i < frequency.length; i++) {
            if (frequency[i] == 2) {
                duplicates.add(i);
            }
        }
        return duplicates;
    }

    // 5. Marking Visited Elements (In-place)
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public static List<Integer> findDuplicatesInPlace(int[] arr) {
        List<Integer> duplicates = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            int index = Math.abs(arr[i]) - 1;
            if (arr[index] < 0) {
                duplicates.add(Math.abs(arr[i]));
            } else {
                arr[index] *= -1;
            }
        }
        return duplicates;
    }

    public static void main(String[] args) {
        int[][] testCases = {
            {2, 3, 1, 2, 3}, // Expected: [2, 3]
            {3, 1, 2},       // Expected: []
            {1, 1, 2, 3, 3}, // Expected: [1, 3]
            {4, 3, 2, 7, 8, 2, 3, 1}, // Expected: [2, 3]
            {1, 2, 3, 4, 5}, // Expected: []
            {5, 5, 5, 5},   // Expected: [5]
            {1},             // Expected: []
            {1, 1},          // Expected: [1]
            {2, 2, 1, 1},    // Expected: [1, 2]
            {1, 2, 3, 4, 5, 5, 4} // Expected: [4, 5]
        };

        for (int[] testCase : testCases) {
            System.out.println("Input: " + Arrays.toString(testCase));
            System.out.println("Brute Force: " + findDuplicatesBruteForce(testCase.clone()));
            System.out.println("Sorting: " + findDuplicatesSorting(testCase.clone()));
            System.out.println("Hash Set: " + findDuplicatesHashSet(testCase.clone()));
            System.out.println("Frequency Array: " + findDuplicatesFrequencyArray(testCase.clone()));
            System.out.println("In-place Marking: " + findDuplicatesInPlace(testCase.clone()));
            System.out.println();
        }
    }
}
