package basic_110_question;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FindMinMax {
    
    // Solution 1: Linear Scan
    public static MinMax findMinMaxLinear(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        
        int min = arr[0];
        int max = arr[0];
        
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        
        return new MinMax(min, max);
    }
    
    // Solution 2: Using Sorting
    public static MinMax findMinMaxUsingSorting(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        
        return new MinMax(sorted[0], sorted[sorted.length - 1]);
    }
    
    // Solution 3: Using Collections
    public static MinMax findMinMaxUsingCollections(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        
        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        return new MinMax(Collections.min(list), Collections.max(list));
    }
    
    // Solution 4: Using Java 8 Streams
    public static MinMax findMinMaxUsingStreams(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        
        IntSummaryStatistics stats = Arrays.stream(arr).summaryStatistics();
        return new MinMax(stats.getMin(), stats.getMax());
    }
    
    // Solution 5: Tournament Method (Divide and Conquer)
    public static MinMax findMinMaxTournament(int[] arr, int left, int right) {
        if (left == right) {
            return new MinMax(arr[left], arr[left]);
        }
        
        if (right == left + 1) {
            return new MinMax(
                Math.min(arr[left], arr[right]),
                Math.max(arr[left], arr[right])
            );
        }
        
        int mid = left + (right - left) / 2;
        MinMax leftResult = findMinMaxTournament(arr, left, mid);
        MinMax rightResult = findMinMaxTournament(arr, mid + 1, right);
        
        return new MinMax(
            Math.min(leftResult.min, rightResult.min),
            Math.max(leftResult.max, rightResult.max)
        );
    }
    
    // Solution 6: Using Parallel Streams (for large arrays)
    public static MinMax findMinMaxParallel(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        
        return Arrays.stream(arr)
                .parallel()
                .mapToObj(num -> new MinMax(num, num))
                .reduce((mm1, mm2) -> new MinMax(
                    Math.min(mm1.min, mm2.min),
                    Math.max(mm1.max, mm2.max)
                ))
                .orElse(null);
    }
    
    // Helper class to store min and max
    static class MinMax {
        int min;
        int max;
        
        MinMax(int min, int max) {
            this.min = min;
            this.max = max;
        }
        
        @Override
        public String toString() {
            return "Min: " + min + ", Max: " + max;
        }
    }
    
    public static void main(String[] args) {
        int[] arr = {34, 12, 56, 78, 23, 9, 45, 67, 89, 1, 99, 5};
        
        System.out.println("Array: " + Arrays.toString(arr));
        
        System.out.println("\n1. Linear Scan: " + findMinMaxLinear(arr));
        System.out.println("2. Using Sorting: " + findMinMaxUsingSorting(arr));
        System.out.println("3. Using Collections: " + findMinMaxUsingCollections(arr));
        System.out.println("4. Using Streams: " + findMinMaxUsingStreams(arr));
        System.out.println("5. Tournament Method: " + findMinMaxTournament(arr, 0, arr.length - 1));
        System.out.println("6. Using Parallel Streams: " + findMinMaxParallel(arr));
        
        // Performance comparison
        System.out.println("\n--- Performance Test ---");
        int[] largeArray = new Random().ints(1000000, 1, 1000000).toArray();
        
        long start = System.currentTimeMillis();
        findMinMaxLinear(largeArray);
        long end = System.currentTimeMillis();
        System.out.println("Linear Scan: " + (end - start) + " ms");
        
        start = System.currentTimeMillis();
        findMinMaxParallel(largeArray);
        end = System.currentTimeMillis();
        System.out.println("Parallel Streams: " + (end - start) + " ms");
    }
}