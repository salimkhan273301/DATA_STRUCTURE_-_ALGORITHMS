package twopointer;

import java.util.*;

public class SumPairClosestToTarget {

    // ---------------------- 1. Brute Force ----------------------
    // Check every pair and track the closest.
    // TC: O(n^2), SC: O(1)
    public ArrayList<Integer> bruteForce(int[] arr, int target) {
        ArrayList<Integer> res = new ArrayList<>();
        int bestDiff = Integer.MAX_VALUE;
        int maxAbsDiff = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int sum = arr[i] + arr[j];
                int diff = Math.abs(sum - target);

                if (diff < bestDiff || (diff == bestDiff && Math.abs(arr[j] - arr[i]) > maxAbsDiff)) {
                    bestDiff = diff;
                    maxAbsDiff = Math.abs(arr[j] - arr[i]);
                    res.clear();
                    res.add(Math.min(arr[i], arr[j]));
                    res.add(Math.max(arr[i], arr[j]));
                }
            }
        }
        return res;
    }

    // ---------------------- 2. Two Pointer (Sorted) ----------------------
    // Sort + shrink window.
    // TC: O(n log n), SC: O(1)
    public ArrayList<Integer> twoPointer(int[] arr, int target) {
        ArrayList<Integer> res = new ArrayList<>();
        Arrays.sort(arr);
        int left = 0, right = arr.length - 1;
        int bestDiff = Integer.MAX_VALUE;
        int maxAbsDiff = Integer.MIN_VALUE;

        while (left < right) {
            int sum = arr[left] + arr[right];
            int diff = Math.abs(sum - target);

            if (diff < bestDiff || (diff == bestDiff && Math.abs(arr[right] - arr[left]) > maxAbsDiff)) {
                bestDiff = diff;
                maxAbsDiff = Math.abs(arr[right] - arr[left]);
                res.clear();
                res.add(arr[left]);
                res.add(arr[right]);
            }

            if (sum < target) left++;
            else right--;
        }
        return res;
    }

    // ---------------------- 3. Binary Search ----------------------
    // Sort and for each element, binary search its best complement.
    // TC: O(n log n), SC: O(1)
    public ArrayList<Integer> binarySearchApproach(int[] arr, int target) {
        ArrayList<Integer> res = new ArrayList<>();
        Arrays.sort(arr);
        int bestDiff = Integer.MAX_VALUE;
        int maxAbsDiff = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            int comp = target - arr[i];
            int idx = Arrays.binarySearch(arr, i + 1, arr.length, comp);
            if (idx < 0) idx = -idx - 1; // insertion point

            // check neighbors
            if (idx < arr.length) {
                int sum = arr[i] + arr[idx];
                int diff = Math.abs(sum - target);
                if (diff < bestDiff || (diff == bestDiff && Math.abs(arr[idx] - arr[i]) > maxAbsDiff)) {
                    bestDiff = diff;
                    maxAbsDiff = Math.abs(arr[idx] - arr[i]);
                    res.clear();
                    res.add(Math.min(arr[i], arr[idx]));
                    res.add(Math.max(arr[i], arr[idx]));
                }
            }
            if (idx - 1 > i) {
                int sum = arr[i] + arr[idx - 1];
                int diff = Math.abs(sum - target);
                if (diff < bestDiff || (diff == bestDiff && Math.abs(arr[idx - 1] - arr[i]) > maxAbsDiff)) {
                    bestDiff = diff;
                    maxAbsDiff = Math.abs(arr[idx - 1] - arr[i]);
                    res.clear();
                    res.add(Math.min(arr[i], arr[idx - 1]));
                    res.add(Math.max(arr[i], arr[idx - 1]));
                }
            }
        }
        return res;
    }

    // ---------------------- 4. HashSet Based ----------------------
    // Try to track closest using hash lookups (less reliable than 2P).
    // TC: O(n^2) worst, SC: O(n)
    public ArrayList<Integer> hashSetApproach(int[] arr, int target) {
        ArrayList<Integer> res = new ArrayList<>();
        int bestDiff = Integer.MAX_VALUE;
        int maxAbsDiff = Integer.MIN_VALUE;

        Set<Integer> seen = new HashSet<>();
        for (int num : arr) {
            for (int other : seen) {
                int sum = num + other;
                int diff = Math.abs(sum - target);
                if (diff < bestDiff || (diff == bestDiff && Math.abs(num - other) > maxAbsDiff)) {
                    bestDiff = diff;
                    maxAbsDiff = Math.abs(num - other);
                    res.clear();
                    res.add(Math.min(num, other));
                    res.add(Math.max(num, other));
                }
            }
            seen.add(num);
        }
        return res;
    }

    // ---------------------- 5. Priority Queue (Heap) ----------------------
    // Keep all pairs in a heap sorted by closeness to target.
    // TC: O(n^2 log(n^2)) ≈ O(n^2 log n), SC: O(n^2)
    public ArrayList<Integer> heapApproach(int[] arr, int target) {
        ArrayList<Integer> res = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> {
                int diff1 = Math.abs((a[0] + a[1]) - target);
                int diff2 = Math.abs((b[0] + b[1]) - target);
                if (diff1 != diff2) return diff1 - diff2;
                return Math.abs(b[0] - b[1]) - Math.abs(a[0] - a[1]);
            }
        );

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                pq.add(new int[]{arr[i], arr[j]});
            }
        }
        if (!pq.isEmpty()) {
            int[] best = pq.poll();
            res.add(Math.min(best[0], best[1]));
            res.add(Math.max(best[0], best[1]));
        }
        return res;
    }

    // ---------------------- Test Cases ----------------------
    public static void main(String[] args) {
        SumPairClosestToTarget sol = new SumPairClosestToTarget();

        int[][] testArrays = {
            {10, 30, 20, 5},
            {5, 2, 7, 1, 4},
            {1, 3, 8, 10},
            {1, 2},
            {}
        };
        int[] targets = {25, 10, 15, 10, 5};

        for (int i = 0; i < testArrays.length; i++) {
            System.out.println("Array: " + Arrays.toString(testArrays[i]) + ", Target: " + targets[i]);
            System.out.println("BruteForce -> " + sol.bruteForce(testArrays[i], targets[i]));
            System.out.println("TwoPointer -> " + sol.twoPointer(testArrays[i], targets[i]));
            System.out.println("BinarySearch -> " + sol.binarySearchApproach(testArrays[i], targets[i]));
            System.out.println("HashSet -> " + sol.hashSetApproach(testArrays[i], targets[i]));
            System.out.println("Heap -> " + sol.heapApproach(testArrays[i], targets[i]));
            System.out.println("--------------------------------------------------");
        }
    }
}
