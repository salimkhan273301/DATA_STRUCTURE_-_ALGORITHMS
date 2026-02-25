package basic_110_question;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UnionIntersection {
    
    // Solution 1: Using HashSet (for unsorted arrays)
    public static Set<Integer> unionUsingSet(int[] arr1, int[] arr2) {
        Set<Integer> union = new HashSet<>();
        
        for (int num : arr1) union.add(num);
        for (int num : arr2) union.add(num);
        
        return union;
    }
    
    public static Set<Integer> intersectionUsingSet(int[] arr1, int[] arr2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> intersection = new HashSet<>();
        
        for (int num : arr1) set1.add(num);
        
        for (int num : arr2) {
            if (set1.contains(num)) {
                intersection.add(num);
            }
        }
        
        return intersection;
    }
    
    // Solution 2: Using Two Pointers (for sorted arrays)
    public static List<Integer> unionSorted(int[] arr1, int[] arr2) {
        List<Integer> union = new ArrayList<>();
        int i = 0, j = 0;
        
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j]) {
                union.add(arr1[i++]);
            } else if (arr2[j] < arr1[i]) {
                union.add(arr2[j++]);
            } else {
                union.add(arr1[i]);
                i++;
                j++;
            }
        }
        
        while (i < arr1.length) union.add(arr1[i++]);
        while (j < arr2.length) union.add(arr2[j++]);
        
        return union;
    }
    
    public static List<Integer> intersectionSorted(int[] arr1, int[] arr2) {
        List<Integer> intersection = new ArrayList<>();
        int i = 0, j = 0;
        
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j]) {
                i++;
            } else if (arr2[j] < arr1[i]) {
                j++;
            } else {
                intersection.add(arr1[i]);
                i++;
                j++;
            }
        }
        
        return intersection;
    }
    
    // Solution 3: Using Java 8 Streams
    public static Set<Integer> unionStreams(int[] arr1, int[] arr2) {
        return IntStream.concat(Arrays.stream(arr1), Arrays.stream(arr2))
                .boxed()
                .collect(Collectors.toSet());
    }
    
    public static Set<Integer> intersectionStreams(int[] arr1, int[] arr2) {
        Set<Integer> set1 = Arrays.stream(arr1).boxed().collect(Collectors.toSet());
        
        return Arrays.stream(arr2)
                .filter(set1::contains)
                .boxed()
                .collect(Collectors.toSet());
    }
    
    // Solution 4: Using BitSet (for limited range of values)
    public static Set<Integer> unionUsingBitSet(int[] arr1, int[] arr2, int maxValue) {
        BitSet bitSet = new BitSet(maxValue + 1);
        Set<Integer> union = new HashSet<>();
        
        for (int num : arr1) bitSet.set(num);
        for (int num : arr2) bitSet.set(num);
        
        for (int i = bitSet.nextSetBit(0); i >= 0; i = bitSet.nextSetBit(i + 1)) {
            union.add(i);
        }
        
        return union;
    }
    
    public static Set<Integer> intersectionUsingBitSet(int[] arr1, int[] arr2, int maxValue) {
        BitSet bitSet1 = new BitSet(maxValue + 1);
        BitSet bitSet2 = new BitSet(maxValue + 1);
        Set<Integer> intersection = new HashSet<>();
        
        for (int num : arr1) bitSet1.set(num);
        for (int num : arr2) bitSet2.set(num);
        
        bitSet1.and(bitSet2);
        
        for (int i = bitSet1.nextSetBit(0); i >= 0; i = bitSet1.nextSetBit(i + 1)) {
            intersection.add(i);
        }
        
        return intersection;
    }
    
    // Solution 5: Using Map for frequency (handles duplicates)
    public static List<Integer> unionWithDuplicates(int[] arr1, int[] arr2) {
        Map<Integer, Integer> frequency = new HashMap<>();
        List<Integer> union = new ArrayList<>();
        
        for (int num : arr1) {
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
            union.add(num);
        }
        
        for (int num : arr2) {
            if (frequency.getOrDefault(num, 0) == 0) {
                union.add(num);
            } else {
                frequency.put(num, frequency.get(num) - 1);
            }
        }
        
        return union;
    }
    
    public static List<Integer> intersectionWithDuplicates(int[] arr1, int[] arr2) {
        Map<Integer, Integer> frequency = new HashMap<>();
        List<Integer> intersection = new ArrayList<>();
        
        for (int num : arr1) {
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }
        
        for (int num : arr2) {
            if (frequency.getOrDefault(num, 0) > 0) {
                intersection.add(num);
                frequency.put(num, frequency.get(num) - 1);
            }
        }
        
        return intersection;
    }
    
    // Solution 6: Using TreeSet (sorted union)
    public static Set<Integer> unionSortedUsingTreeSet(int[] arr1, int[] arr2) {
        Set<Integer> union = new TreeSet<>();
        
        for (int num : arr1) union.add(num);
        for (int num : arr2) union.add(num);
        
        return union;
    }
    
    public static void main(String[] args) {
        int[] arr1 = {1, 3, 4, 5, 7};
        int[] arr2 = {2, 3, 5, 6, 8, 9};
        
        System.out.println("Array 1: " + Arrays.toString(arr1));
        System.out.println("Array 2: " + Arrays.toString(arr2));
        
        System.out.println("\n--- Union Operations ---");
        System.out.println("1. Using HashSet: " + unionUsingSet(arr1, arr2));
        System.out.println("2. Two Pointers (sorted): " + unionSorted(arr1, arr2));
        System.out.println("3. Using Streams: " + unionStreams(arr1, arr2));
        System.out.println("4. Using BitSet: " + unionUsingBitSet(arr1, arr2, 10));
        System.out.println("5. With Duplicates: " + unionWithDuplicates(arr1, arr2));
        System.out.println("6. Using TreeSet: " + unionSortedUsingTreeSet(arr1, arr2));
        
        System.out.println("\n--- Intersection Operations ---");
        System.out.println("1. Using HashSet: " + intersectionUsingSet(arr1, arr2));
        System.out.println("2. Two Pointers (sorted): " + intersectionSorted(arr1, arr2));
        System.out.println("3. Using Streams: " + intersectionStreams(arr1, arr2));
        System.out.println("4. Using BitSet: " + intersectionUsingBitSet(arr1, arr2, 10));
        System.out.println("5. With Duplicates: " + intersectionWithDuplicates(arr1, arr2));
        
        // Test with duplicates
        System.out.println("\n--- Testing with Duplicates ---");
        int[] arr3 = {1, 2, 2, 3, 4, 4, 5};
        int[] arr4 = {2, 2, 3, 3, 4, 6, 7};
        
        System.out.println("Array 3: " + Arrays.toString(arr3));
        System.out.println("Array 4: " + Arrays.toString(arr4));
        System.out.println("Union with duplicates: " + unionWithDuplicates(arr3, arr4));
        System.out.println("Intersection with duplicates: " + intersectionWithDuplicates(arr3, arr4));
    }
}