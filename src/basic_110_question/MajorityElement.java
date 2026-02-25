package basic_110_question;

import java.util.*;
import java.util.stream.Collectors;

public class MajorityElement {
    
    // Solution 1: Moore's Voting Algorithm
    public static Integer findMajorityMoore(int[] arr) {
        if (arr.length == 0) return null;
        
        // Find candidate
        int candidate = arr[0];
        int count = 1;
        
        for (int i = 1; i < arr.length; i++) {
            if (count == 0) {
                candidate = arr[i];
                count = 1;
            } else if (arr[i] == candidate) {
                count++;
            } else {
                count--;
            }
        }
        
        // Verify candidate
        count = 0;
        for (int num : arr) {
            if (num == candidate) count++;
        }
        
        return count > arr.length / 2 ? candidate : null;
    }
    
    // Solution 2: Using HashMap
    public static Integer findMajorityHashMap(int[] arr) {
        Map<Integer, Integer> countMap = new HashMap<>();
        
        for (int num : arr) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
            if (countMap.get(num) > arr.length / 2) {
                return num;
            }
        }
        
        return null;
    }
    
    // Solution 3: Using Sorting
    public static Integer findMajoritySorting(int[] arr) {
        if (arr.length == 0) return null;
        
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        
        int candidate = sorted[arr.length / 2];
        
        int count = 0;
        for (int num : arr) {
            if (num == candidate) count++;
        }
        
        return count > arr.length / 2 ? candidate : null;
    }
    
    // Solution 4: Bit Manipulation
    public static Integer findMajorityBitManipulation(int[] arr) {
        int majority = 0;
        
        for (int i = 0; i < 32; i++) {
            int count = 0;
            int mask = 1 << i;
            
            for (int num : arr) {
                if ((num & mask) != 0) {
                    count++;
                }
            }
            
            if (count > arr.length / 2) {
                majority |= mask;
            }
        }
        
        // Verify
        int count = 0;
        for (int num : arr) {
            if (num == majority) count++;
        }
        
        return count > arr.length / 2 ? majority : null;
    }
    
    // Solution 5: Using Streams with grouping
    public static Integer findMajorityStreams(int[] arr) {
        return Arrays.stream(arr)
                .boxed()
                .collect(Collectors.groupingBy(n -> n, Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() > arr.length / 2)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }
    
    // Solution 6: Find all elements appearing more than n/k times
    public static List<Integer> findElementsMoreThanNByK(int[] arr, int k) {
        if (arr.length == 0) return new ArrayList<>();
        
        int threshold = arr.length / k;
        Map<Integer, Integer> candidates = new HashMap<>();
        
        // Find potential candidates
        for (int num : arr) {
            if (candidates.containsKey(num)) {
                candidates.put(num, candidates.get(num) + 1);
            } else if (candidates.size() < k - 1) {
                candidates.put(num, 1);
            } else {
                for (Iterator<Map.Entry<Integer, Integer>> it = 
                     candidates.entrySet().iterator(); it.hasNext();) {
                    Map.Entry<Integer, Integer> entry = it.next();
                    entry.setValue(entry.getValue() - 1);
                    if (entry.getValue() == 0) {
                        it.remove();
                    }
                }
            }
        }
        
        // Verify candidates
        List<Integer> result = new ArrayList<>();
        for (int candidate : candidates.keySet()) {
            int count = 0;
            for (int num : arr) {
                if (num == candidate) count++;
            }
            if (count > threshold) {
                result.add(candidate);
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        int[][] testArrays = {
            {3, 3, 4, 2, 4, 4, 2, 4, 4},
            {1, 1, 2, 2, 3, 3, 3},
            {2, 2, 2, 2, 2, 3, 3, 3, 3, 3},
            {1, 2, 3, 4, 5, 6},
            {5, 5, 5, 5, 1, 2, 3}
        };
        
        for (int i = 0; i < testArrays.length; i++) {
            int[] arr = testArrays[i];
            System.out.println("\n--- Test Array " + (i + 1) + ": " + Arrays.toString(arr) + " ---");
            
            System.out.println("1. Moore's Algorithm: " + findMajorityMoore(arr));
            System.out.println("2. HashMap Method: " + findMajorityHashMap(arr));
            System.out.println("3. Sorting Method: " + findMajoritySorting(arr));
            System.out.println("4. Bit Manipulation: " + findMajorityBitManipulation(arr));
            System.out.println("5. Streams Method: " + findMajorityStreams(arr));
            System.out.println("6. Elements > n/3: " + findElementsMoreThanNByK(arr, 3));
        }
        
        // Performance test
        System.out.println("\n--- Performance Test (Large Array) ---");
        Random rand = new Random();
        int[] largeArray = new int[1000000];
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = rand.nextInt(10);
        }
        // Make sure there's a majority element
        for (int i = 0; i < largeArray.length / 2 + 1; i++) {
            largeArray[i] = 5;
        }
        
        long start = System.currentTimeMillis();
        Integer result = findMajorityMoore(largeArray);
        long end = System.currentTimeMillis();
        System.out.println("Moore's Algorithm: " + (end - start) + " ms, Result: " + result);
        
        start = System.currentTimeMillis();
        result = findMajorityHashMap(largeArray);
        end = System.currentTimeMillis();
        System.out.println("HashMap Method: " + (end - start) + " ms, Result: " + result);
    }
}