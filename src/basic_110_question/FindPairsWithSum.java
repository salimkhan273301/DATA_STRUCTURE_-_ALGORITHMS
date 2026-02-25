package basic_110_question;

import java.util.*;
import java.util.stream.Collectors;

public class FindPairsWithSum {
    
    // Solution 1: Using HashSet (For unique pairs)
    public static Set<String> findPairsUsingSet(int[] arr, int sum) {
        Set<Integer> seen = new HashSet<>();
        Set<String> pairs = new HashSet<>();
        
        for (int num : arr) {
            int complement = sum - num;
            if (seen.contains(complement)) {
                int first = Math.min(num, complement);
                int second = Math.max(num, complement);
                pairs.add(first + "," + second);
            }
            seen.add(num);
        }
        
        return pairs;
    }
    
    // Solution 2: Using Brute Force
    public static List<int[]> findPairsBruteForce(int[] arr, int sum) {
        List<int[]> pairs = new ArrayList<>();
        
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == sum) {
                    pairs.add(new int[]{arr[i], arr[j]});
                }
            }
        }
        
        return pairs;
    }
    
    // Solution 3: Using Two Pointers (Requires sorted array)
    public static List<int[]> findPairsUsingTwoPointers(int[] arr, int sum) {
        Arrays.sort(arr);
        List<int[]> pairs = new ArrayList<>();
        
        int left = 0;
        int right = arr.length - 1;
        
        while (left < right) {
            int currentSum = arr[left] + arr[right];
            
            if (currentSum == sum) {
                pairs.add(new int[]{arr[left], arr[right]});
                left++;
                right--;
            } else if (currentSum < sum) {
                left++;
            } else {
                right--;
            }
        }
        
        return pairs;
    }
    
    // Solution 4: Using HashMap with Counts
    public static Map<String, Integer> findPairsWithCounts(int[] arr, int sum) {
        Map<Integer, Integer> numCounts = new HashMap<>();
        Map<String, Integer> pairCounts = new HashMap<>();
        
        // Count occurrences
        for (int num : arr) {
            numCounts.put(num, numCounts.getOrDefault(num, 0) + 1);
        }
        
        // Find pairs
        for (int num : arr) {
            int complement = sum - num;
            if (numCounts.containsKey(complement)) {
                if (num == complement && numCounts.get(num) > 1) {
                    pairCounts.put(num + "," + complement, 
                                  pairCounts.getOrDefault(num + "," + complement, 0) + 1);
                } else if (num < complement) {
                    pairCounts.put(num + "," + complement, 
                                  pairCounts.getOrDefault(num + "," + complement, 0) + 1);
                }
            }
        }
        
        return pairCounts;
    }
    
    // Solution 5: Using Java 8 Streams
    public static Set<String> findPairsUsingStreams(int[] arr, int sum) {
        Set<Integer> seen = new HashSet<>();
        
        return Arrays.stream(arr)
                .boxed()
                .filter(num -> {
                    if (seen.contains(sum - num)) {
                        return true;
                    }
                    seen.add(num);
                    return false;
                })
                .map(num -> {
                    int min = Math.min(num, sum - num);
                    int max = Math.max(num, sum - num);
                    return min + "," + max;
                })
                .collect(Collectors.toSet());
    }
    
    // Solution 6: Using Binary Search
    public static List<int[]> findPairsUsingBinarySearch(int[] arr, int sum) {
        Arrays.sort(arr);
        List<int[]> pairs = new ArrayList<>();
        
        for (int i = 0; i < arr.length - 1; i++) {
            int complement = sum - arr[i];
            int index = Arrays.binarySearch(arr, i + 1, arr.length, complement);
            
            if (index > i) {
                pairs.add(new int[]{arr[i], arr[index]});
            }
        }
        
        return pairs;
    }
    
    public static void main(String[] args) {
        int[] arr = {2, 4, 3, 5, 6, -2, 4, 7, 8, 9};
        int sum = 7;
        
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Target Sum: " + sum);
        
        System.out.println("\n1. Using HashSet:");
        findPairsUsingSet(arr, sum).forEach(System.out::println);
        
        System.out.println("\n2. Using Brute Force:");
        for (int[] pair : findPairsBruteForce(arr, sum)) {
            System.out.println(Arrays.toString(pair));
        }
        
        System.out.println("\n3. Using Two Pointers:");
        for (int[] pair : findPairsUsingTwoPointers(arr.clone(), sum)) {
            System.out.println(Arrays.toString(pair));
        }
        
        System.out.println("\n4. Using HashMap with Counts:");
        findPairsWithCounts(arr, sum).forEach((k, v) -> 
            System.out.println(k + " appears " + v + " time(s)"));
        
        System.out.println("\n5. Using Streams:");
        findPairsUsingStreams(arr, sum).forEach(System.out::println);
        
        System.out.println("\n6. Using Binary Search:");
        for (int[] pair : findPairsUsingBinarySearch(arr.clone(), sum)) {
            System.out.println(Arrays.toString(pair));
        }
    }
}