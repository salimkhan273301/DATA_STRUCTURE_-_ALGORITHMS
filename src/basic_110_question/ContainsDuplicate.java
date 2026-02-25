package basic_110_question;

import java.util.*;
import java.util.stream.Collectors;

public class ContainsDuplicate {
    
    // Solution 1: Using HashSet
    public static boolean containsDuplicateUsingSet(int[] arr) {
        Set<Integer> set = new HashSet<>();
        
        for (int num : arr) {
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
        }
        
        return false;
    }
    
    // Solution 2: Using Sorting
    public static boolean containsDuplicateUsingSorting(int[] arr) {
        Arrays.sort(arr);
        
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) {
                return true;
            }
        }
        
        return false;
    }
    
    // Solution 3: Using Java 8 Streams
    public static boolean containsDuplicateUsingStreams(int[] arr) {
        Set<Integer> set = new HashSet<>();
        return Arrays.stream(arr).anyMatch(num -> !set.add(num));
    }
    
    // Solution 4: Using Brute Force
    public static boolean containsDuplicateBruteForce(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    return true;
                }
            }
        }
        return false;
    }
    
    // Solution 5: Using HashMap to count frequencies
    public static boolean containsDuplicateUsingMap(int[] arr) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        
        for (int num : arr) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
            if (frequencyMap.get(num) > 1) {
                return true;
            }
        }
        
        return false;
    }
    
    // Solution 6: Using BitSet (for positive numbers within range)
    public static boolean containsDuplicateUsingBitSet(int[] arr, int maxValue) {
        BitSet bitSet = new BitSet(maxValue + 1);
        
        for (int num : arr) {
            if (bitSet.get(num)) {
                return true;
            }
            bitSet.set(num);
        }
        
        return false;
    }
    
    // Find all duplicates (bonus)
    public static List<Integer> findAllDuplicates(int[] arr) {
        List<Integer> duplicates = new ArrayList<>();
        Set<Integer> seen = new HashSet<>();
        
        for (int num : arr) {
            if (seen.contains(num)) {
                if (!duplicates.contains(num)) {
                    duplicates.add(num);
                }
            } else {
                seen.add(num);
            }
        }
        
        return duplicates;
    }
    
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 1};
        int[] arr2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        
        System.out.println("Array 1: " + Arrays.toString(arr1));
        System.out.println("Array 2: " + Arrays.toString(arr2));
        
        System.out.println("\n--- Contains Duplicate Check ---");
        System.out.println("Array 1:");
        System.out.println("1. Using HashSet: " + containsDuplicateUsingSet(arr1));
        System.out.println("2. Using Sorting: " + containsDuplicateUsingSorting(arr1.clone()));
        System.out.println("3. Using Streams: " + containsDuplicateUsingStreams(arr1));
        System.out.println("4. Using Brute Force: " + containsDuplicateBruteForce(arr1));
        System.out.println("5. Using HashMap: " + containsDuplicateUsingMap(arr1));
        System.out.println("6. Using BitSet: " + containsDuplicateUsingBitSet(arr1, 10));
        
        System.out.println("\nArray 2:");
        System.out.println("1. Using HashSet: " + containsDuplicateUsingSet(arr2));
        System.out.println("2. Using Sorting: " + containsDuplicateUsingSorting(arr2.clone()));
        System.out.println("3. Using Streams: " + containsDuplicateUsingStreams(arr2));
        
        System.out.println("\nAll duplicates in array 1: " + findAllDuplicates(arr1));
    }
}