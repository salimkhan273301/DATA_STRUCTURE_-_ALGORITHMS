package twopointer;

import java.util.*;

public class FindPairs {

    // ---------------------- 1. Brute Force ----------------------
    // Check all pairs (i, j)
    public int countPairsBruteForce(int[] arr, int target) {
        int n = arr.length, count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] + arr[j] == target) count++;
            }
        }
        return count;
    }

    // ---------------------- 2. HashMap (frequency map) ----------------------
    // O(n) approach using frequencies
    public int countPairsHashMap(int[] arr, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int num : arr) {
            int comp = target - num;
            if (map.containsKey(comp)) {
                count += map.get(comp);
            }
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return count;
    }

    // ---------------------- 3. Two Pointer (after sorting) ----------------------
    // O(n log n) due to sorting
    public int countPairsTwoPointer(int[] arr, int target) {
        Arrays.sort(arr);
        int left = 0, right = arr.length - 1, count = 0;

        while (left < right) {
            int sum = arr[left] + arr[right];
            if (sum == target) {
                count++;
                left++;
                right--;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return count;
    }

    // ---------------------- 4. HashSet ----------------------
    // Avoids duplicates but only works for unique pairs (not frequency based).
    public int countPairsHashSet(int[] arr, int target) {
        Set<Integer> seen = new HashSet<>();
        Set<String> uniquePairs = new HashSet<>(); // to avoid duplicates
        int count = 0;

        for (int num : arr) {
            int comp = target - num;
            if (seen.contains(comp)) {
                // store sorted pair as string to avoid duplicate counting
                String pair = Math.min(num, comp) + "," + Math.max(num, comp);
                if (!uniquePairs.contains(pair)) {
                    uniquePairs.add(pair);
                    count++;
                }
            }
            seen.add(num);
        }
        return count;
    }

    // ---------------------- 5. Sorting + Binary Search ----------------------
    // For each element, search target - arr[i] using binary search
    public int countPairsBinarySearch(int[] arr, int target) {
        Arrays.sort(arr);
        int n = arr.length, count = 0;

        for (int i = 0; i < n; i++) {
            int comp = target - arr[i];
            int idx = Arrays.binarySearch(arr, i + 1, n, comp);
            if (idx > i) count++;
        }
        return count;
    }

    // ---------------------- Testing ----------------------
    public static void main(String[] args) {
    	FindPairs sol = new FindPairs();
        int[] arr = {1, 5, 7, -1, 5};
        int target = 6;

        System.out.println("Brute Force: " + sol.countPairsBruteForce(arr, target)); // 3
        System.out.println("HashMap: " + sol.countPairsHashMap(arr, target));         // 3
        System.out.println("Two Pointer: " + sol.countPairsTwoPointer(arr, target)); // 2 (unique pairs only)
        System.out.println("HashSet: " + sol.countPairsHashSet(arr, target));        // 2 (unique pairs only)
        System.out.println("Binary Search: " + sol.countPairsBinarySearch(arr, target)); // 2
    }
}
