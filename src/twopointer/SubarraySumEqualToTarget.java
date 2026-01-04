package twopointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.stream.IntStream;

/**
 * Problem: Find subarray with given sum (return 1-based indices)
 * Various approaches with different time and space complexities
 */
public class SubarraySumEqualToTarget {
    
    public static void main(String[] args) {
        // Test cases
        int[] test1 = {1, 2, 3, 7, 5};
        int target1 = 12;
        
        int[] test2 = {1, 2, 3, 4, 5};
        int target2 = 9;
        
        int[] test3 = {1, -1, 1, 1, 1};
        int target3 = 2;
        
        int[] test4 = {10, 2, -2, -20, 10};
        int target4 = -10;
        
        int[] test5 = {1, 4, 20, 3, 10, 5};
        int target5 = 33;
        
        System.out.println("Testing all approaches:");
        System.out.println("Test 1: " + Arrays.toString(test1) + ", Target: " + target1);
        System.out.println("Test 2: " + Arrays.toString(test2) + ", Target: " + target2);
        System.out.println("Test 3: " + Arrays.toString(test3) + ", Target: " + target3);
        System.out.println("Test 4: " + Arrays.toString(test4) + ", Target: " + target4);
        System.out.println("Test 5: " + Arrays.toString(test5) + ", Target: " + target5);
        System.out.println();
        
        // Test all approaches
        testApproach("HashMap Optimal", test1, target1, SubarraySumEqualToTarget::hashMapOptimal);
        testApproach("HashMap Optimal", test2, target2, SubarraySumEqualToTarget::hashMapOptimal);
        testApproach("HashMap Optimal", test3, target3, SubarraySumEqualToTarget::hashMapOptimal);
        testApproach("HashMap Optimal", test4, target4, SubarraySumEqualToTarget::hashMapOptimal);
        testApproach("HashMap Optimal", test5, target5, SubarraySumEqualToTarget::hashMapOptimal);
        System.out.println();
        
        testApproach("Sliding Window", test1, target1, SubarraySumEqualToTarget::slidingWindow);
        testApproach("Sliding Window", test2, target2, SubarraySumEqualToTarget::slidingWindow);
        testApproach("Sliding Window", test5, target5, SubarraySumEqualToTarget::slidingWindow);
        System.out.println();
        
        testApproach("HashMap Early Check", test1, target1, SubarraySumEqualToTarget::hashMapEarlyCheck);
        testApproach("HashMap Early Check", test3, target3, SubarraySumEqualToTarget::hashMapEarlyCheck);
        System.out.println();
        
        testApproach("Brute Force Optimized", test1, target1, SubarraySumEqualToTarget::bruteForceOptimized);
        testApproach("Brute Force Optimized", test2, target2, SubarraySumEqualToTarget::bruteForceOptimized);
        System.out.println();
        
        // Test more approaches as needed...
        
        // Print complexity table
        printComplexityTable();
        
        // Run benchmarks
        benchmarkApproaches();
    }
    
    private static void testApproach(String name, int[] arr, int target, 
                                   BiFunction<int[], Integer, ArrayList<Integer>> approach) {
        try {
            ArrayList<Integer> result = approach.apply(arr, target);
            System.out.println(name + ": " + result + " | Test: " + Arrays.toString(arr) + ", Target: " + target);
        } catch (Exception e) {
            System.out.println(name + ": Error - " + e.getMessage() + " | Test: " + Arrays.toString(arr));
        }
    }
    
    // ==================== SOLUTION 1: HashMap Optimal ====================
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * Works for: All numbers (positive, negative, zero)
     * Technique: Prefix Sum + HashMap
     */
    public static ArrayList<Integer> hashMapOptimal(int[] arr, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0); // prefix sum 0 at index 0
        
        int prefixSum = 0;
        for (int i = 0; i < arr.length; i++) {
            prefixSum += arr[i];
            
            if (map.containsKey(prefixSum - target)) {
                return new ArrayList<>(Arrays.asList(
                    map.get(prefixSum - target) + 1, 
                    i + 1
                ));
            }
            
            map.putIfAbsent(prefixSum, i + 1);
        }
        
        return new ArrayList<>(Arrays.asList(-1));
    }
    
    // ==================== SOLUTION 2: Sliding Window ====================
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * Works for: Positive numbers only
     * Technique: Two pointers/Sliding window
     */
    public static ArrayList<Integer> slidingWindow(int[] arr, int target) {
        int left = 0, currentSum = 0;
        
        for (int right = 0; right < arr.length; right++) {
            currentSum += arr[right];
            
            while (currentSum > target && left <= right) {
                currentSum -= arr[left];
                left++;
            }
            
            if (currentSum == target) {
                return new ArrayList<>(Arrays.asList(left + 1, right + 1));
            }
        }
        
        return new ArrayList<>(Arrays.asList(-1));
    }
    
    // ==================== SOLUTION 3: HashMap with Early Check ====================
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * Works for: All numbers
     * Technique: Prefix Sum with immediate check
     */
    public static ArrayList<Integer> hashMapEarlyCheck(int[] arr, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int prefixSum = 0;
        
        for (int i = 0; i < arr.length; i++) {
            prefixSum += arr[i];
            
            // Check if current prefix sum equals target
            if (prefixSum == target) {
                return new ArrayList<>(Arrays.asList(1, i + 1));
            }
            
            // Check if (prefixSum - target) exists
            if (map.containsKey(prefixSum - target)) {
                return new ArrayList<>(Arrays.asList(
                    map.get(prefixSum - target) + 2, 
                    i + 1
                ));
            }
            
            map.put(prefixSum, i);
        }
        
        return new ArrayList<>(Arrays.asList(-1));
    }
    
    // ==================== SOLUTION 4: Brute Force Optimized ====================
    /**
     * Time Complexity: O(n²)
     * Space Complexity: O(1)
     * Works for: All numbers
     * Technique: Nested loops with early termination
     */
    public static ArrayList<Integer> bruteForceOptimized(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            int currentSum = 0;
            for (int j = i; j < arr.length; j++) {
                currentSum += arr[j];
                if (currentSum == target) {
                    return new ArrayList<>(Arrays.asList(i + 1, j + 1));
                }
                if (Math.abs(currentSum) > Math.abs(target) * 2 && target != 0) {
                    break; // Early termination for large deviations
                }
            }
        }
        return new ArrayList<>(Arrays.asList(-1));
    }
    
    // ==================== SOLUTION 5: Cumulative Sum Array ====================
    /**
     * Time Complexity: O(n²)
     * Space Complexity: O(n)
     * Works for: All numbers
     * Technique: Precompute prefix sums
     */
    public static ArrayList<Integer> cumulativeSumArray(int[] arr, int target) {
        int n = arr.length;
        int[] prefixSum = new int[n + 1];
        
        // Precompute prefix sums
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + arr[i];
        }
        
        // Check all subarrays
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (prefixSum[j + 1] - prefixSum[i] == target) {
                    return new ArrayList<>(Arrays.asList(i + 1, j + 1));
                }
            }
        }
        
        return new ArrayList<>(Arrays.asList(-1));
    }
    
    // ==================== SOLUTION 6: HashMap with Array Storage ====================
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * Works for: All numbers with known range
     * Technique: Array-based hash storage
     */
    public static ArrayList<Integer> hashMapArrayStorage(int[] arr, int target) {
        int maxSum = Arrays.stream(arr).map(Math::abs).sum() * 2 + 1;
        int offset = maxSum / 2;
        int[] indexMap = new int[maxSum];
        Arrays.fill(indexMap, -1);
        
        indexMap[offset] = 0; // prefix sum 0 at index 0
        int prefixSum = 0;
        
        for (int i = 0; i < arr.length; i++) {
            prefixSum += arr[i];
            int mappedIndex = prefixSum + offset;
            
            if (mappedIndex - target >= 0 && mappedIndex - target < maxSum && 
                indexMap[mappedIndex - target] != -1) {
                return new ArrayList<>(Arrays.asList(
                    indexMap[mappedIndex - target] + 1, 
                    i + 1
                ));
            }
            
            if (indexMap[mappedIndex] == -1) {
                indexMap[mappedIndex] = i + 1;
            }
        }
        
        return new ArrayList<>(Arrays.asList(-1));
    }
    
    // ==================== SOLUTION 7: Two Pointers Variant ====================
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * Works for: Positive numbers with early expansion
     * Technique: Expand right, contract left
     */
    public static ArrayList<Integer> twoPointersVariant(int[] arr, int target) {
        int left = 0, right = 0;
        int currentSum = 0;
        
        while (right < arr.length) {
            currentSum += arr[right];
            
            if (currentSum == target) {
                return new ArrayList<>(Arrays.asList(left + 1, right + 1));
            }
            
            while (currentSum > target && left < right) {
                currentSum -= arr[left];
                left++;
                if (currentSum == target) {
                    return new ArrayList<>(Arrays.asList(left + 1, right + 1));
                }
            }
            
            right++;
        }
        
        return new ArrayList<>(Arrays.asList(-1));
    }
    
    // ==================== SOLUTION 8: HashMap with Concurrent Modification ====================
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * Works for: All numbers (thread-safe version)
     * Technique: ConcurrentHashMap for thread safety
     */
    public static ArrayList<Integer> concurrentHashMap(int[] arr, int target) {
        ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
        map.put(0, 0);
        int prefixSum = 0;
        
        for (int i = 0; i < arr.length; i++) {
            prefixSum += arr[i];
            
            if (map.containsKey(prefixSum - target)) {
                return new ArrayList<>(Arrays.asList(
                    map.get(prefixSum - target) + 1, 
                    i + 1
                ));
            }
            
            map.putIfAbsent(prefixSum, i + 1);
        }
        
        return new ArrayList<>(Arrays.asList(-1));
    }
    
    // ==================== SOLUTION 9: Recursive Approach ====================
    /**
     * Time Complexity: O(n²)
     * Space Complexity: O(n) - recursion stack
     * Works for: All numbers
     * Technique: Divide and conquer (not optimal)
     */
    public static ArrayList<Integer> recursiveApproach(int[] arr, int target) {
        int[] result = recursiveHelper(arr, target, 0, arr.length - 1);
        if (result[0] == -1) {
            return new ArrayList<>(Arrays.asList(-1));
        }
        return new ArrayList<>(Arrays.asList(result[0] + 1, result[1] + 1));
    }
    
    private static int[] recursiveHelper(int[] arr, int target, int start, int end) {
        if (start > end) return new int[]{-1, -1};
        
        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum += arr[i];
            if (sum == target) {
                return new int[]{start, i};
            }
        }
        
        int[] left = recursiveHelper(arr, target, start + 1, end);
        int[] right = recursiveHelper(arr, target, start, end - 1);
        
        return left[0] != -1 ? left : right;
    }
    
    // ==================== SOLUTION 10: Stream API Approach ====================
    /**
     * Time Complexity: O(n²)
     * Space Complexity: O(n)
     * Works for: All numbers
     * Technique: Java 8 Streams (functional programming)
     */
    public static ArrayList<Integer> streamApproach(int[] arr, int target) {
        return IntStream.range(0, arr.length)
                .boxed()
                .flatMap(i -> IntStream.range(i, arr.length)
                        .mapToObj(j -> new int[]{i, j, Arrays.stream(arr, i, j + 1).sum()}))
                .filter(pair -> pair[2] == target)
                .findFirst()
                .map(pair -> new ArrayList<>(Arrays.asList(pair[0] + 1, pair[1] + 1)))
                .orElse(new ArrayList<>(Arrays.asList(-1)));
    }
    
    // ==================== UTILITY METHODS ====================
    
    public static void benchmarkApproaches() {
        int[] largeArray = new int[10000];
        Arrays.fill(largeArray, 1);
        int target = 5000;
        
        System.out.println("\nBenchmarking approaches with array size: " + largeArray.length);
        
        long startTime = System.nanoTime();
        hashMapOptimal(largeArray, target);
        long endTime = System.nanoTime();
        System.out.println("HashMap Optimal: " + (endTime - startTime) / 1000000 + "ms");
        
        startTime = System.nanoTime();
        slidingWindow(largeArray, target);
        endTime = System.nanoTime();
        System.out.println("Sliding Window: " + (endTime - startTime) / 1000000 + "ms");
        
        startTime = System.nanoTime();
        hashMapEarlyCheck(largeArray, target);
        endTime = System.nanoTime();
        System.out.println("HashMap Early Check: " + (endTime - startTime) / 1000000 + "ms");
    }
    
    public static void printComplexityTable() {
        System.out.println("\n=== Time and Space Complexity Table ===");
        System.out.println("1. HashMap Optimal: O(n) time, O(n) space");
        System.out.println("2. Sliding Window: O(n) time, O(1) space (positive only)");
        System.out.println("3. HashMap Early Check: O(n) time, O(n) space");
        System.out.println("4. Brute Force Optimized: O(n²) time, O(1) space");
        System.out.println("5. Cumulative Sum Array: O(n²) time, O(n) space");
        System.out.println("6. HashMap Array Storage: O(n) time, O(n) space");
        System.out.println("7. Two Pointers Variant: O(n) time, O(1) space (positive only)");
        System.out.println("8. Concurrent HashMap: O(n) time, O(n) space (thread-safe)");
        System.out.println("9. Recursive Approach: O(n²) time, O(n) space");
        System.out.println("10. Stream API: O(n²) time, O(n) space");
    }
}