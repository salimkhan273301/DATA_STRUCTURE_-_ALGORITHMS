package twopointer;

import java.util.*;

public class CountPairsLessThanTarget {

    // -----------------------------
    // Solution 1: Brute Force O(n^2)
    // -----------------------------
    static int countPairsBruteForce(int arr[], int target) {
        int n = arr.length, count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] + arr[j] < target) count++;
            }
        }
        return count;
    }
    // Time: O(n^2), Space: O(1)

    // --------------------------------------
    // Solution 2: Sort + Two Pointers O(n log n)
    // --------------------------------------
    static int countPairsTwoPointers(int arr[], int target) {
        Arrays.sort(arr);
        int left = 0, right = arr.length - 1, count = 0;
        while (left < right) {
            if (arr[left] + arr[right] < target) {
                count += (right - left); // all pairs with arr[left]
                left++;
            } else {
                right--;
            }
        }
        return count;
    }
    // Time: O(n log n), Space: O(1)

    // --------------------------------------
    // Solution 3: Sort + Binary Search O(n log n)
    // --------------------------------------
    static int countPairsBinarySearch(int arr[], int target) {
        Arrays.sort(arr);
        int n = arr.length, count = 0;
        for (int i = 0; i < n; i++) {
            int low = i + 1, high = n - 1, ans = i;
            while (low <= high) {
                int mid = (low + high) / 2;
                if (arr[i] + arr[mid] < target) {
                    ans = mid;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            count += (ans - i);
        }
        return count;
    }
    // Time: O(n log n), Space: O(1)

    // --------------------------------------
    // Solution 4: Hashing (Not optimal, O(n^2))
    // --------------------------------------
    static int countPairsHashing(int arr[], int target) {
        int n = arr.length, count = 0;
        for (int i = 0; i < n; i++) {
            Set<Integer> seen = new HashSet<>();
            for (int j = i + 1; j < n; j++) {
                if (arr[i] + arr[j] < target) {
                    if (!seen.contains(arr[j])) {
                        count++;
                        seen.add(arr[j]);
                    }
                }
            }
        }
        return count;
    }
    // Time: O(n^2), Space: O(n)

    // --------------------------------------
    // Solution 5: Optimized Two Pointers (same as 2 but final go-to)
    // --------------------------------------
    static int countPairsOptimized(int arr[], int target) {
        Arrays.sort(arr);
        int left = 0, right = arr.length - 1, count = 0;
        while (left < right) {
            if (arr[left] + arr[right] < target) {
                count += (right - left);
                left++;
            } else {
                right--;
            }
        }
        return count;
    }
    // Time: O(n log n), Space: O(1)

    // -----------------------------
    // Test Cases
    // -----------------------------
    public static void main(String[] args) {
        int[][] testArrays = {
            {7, 2, 5, 3},
            {5, 2, 3, 2, 4, 1},
            {1, 1, 1, 1},
            {-1, 1, 5, 5, 7},
            {-17, -15, -13, -9, -4, 0, 0, 0, 1, 5, 9, 12, 18, 18}
        };
        int[] targets = {8, 5, 3, 6, 0};
        int[] expected = {2, 4, 6, 2, 4};

        for (int i = 0; i < testArrays.length; i++) {
            int[] arr = testArrays[i];
            int target = targets[i];
            System.out.println("Test " + (i + 1) + " | Target = " + target);
            System.out.println("Brute Force: " + countPairsBruteForce(arr.clone(), target));
            System.out.println("Two Pointers: " + countPairsTwoPointers(arr.clone(), target));
            System.out.println("Binary Search: " + countPairsBinarySearch(arr.clone(), target));
            System.out.println("Hashing: " + countPairsHashing(arr.clone(), target));
            System.out.println("Optimized Two Pointers: " + countPairsOptimized(arr.clone(), target));
            System.out.println("Expected: " + expected[i]);
            System.out.println("----");
        }
    }
}
