package basic_110_question;

import java.util.*;
import java.util.stream.Collectors;

public class KthSmallestLargest {
    
    // Solution 1: Using Sorting
    public static int kthSmallestUsingSort(int[] arr, int k) {
        if (k < 1 || k > arr.length) {
            throw new IllegalArgumentException("Invalid k");
        }
        
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        return sorted[k - 1];
    }
    
    public static int kthLargestUsingSort(int[] arr, int k) {
        if (k < 1 || k > arr.length) {
            throw new IllegalArgumentException("Invalid k");
        }
        
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        return sorted[arr.length - k];
    }
    
    // Solution 2: Using Min Heap (PriorityQueue)
    public static int kthLargestUsingMinHeap(int[] arr, int k) {
        if (k < 1 || k > arr.length) {
            throw new IllegalArgumentException("Invalid k");
        }
        
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        for (int num : arr) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        
        return minHeap.peek();
    }
    
    // Solution 3: Using Max Heap
    public static int kthSmallestUsingMaxHeap(int[] arr, int k) {
        if (k < 1 || k > arr.length) {
            throw new IllegalArgumentException("Invalid k");
        }
        
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        
        for (int num : arr) {
            maxHeap.offer(num);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        
        return maxHeap.peek();
    }
    
    // Solution 4: Quick Select Algorithm
    public static int kthSmallestQuickSelect(int[] arr, int k) {
        if (k < 1 || k > arr.length) {
            throw new IllegalArgumentException("Invalid k");
        }
        
        int[] copy = arr.clone();
        return quickSelect(copy, 0, copy.length - 1, k - 1);
    }
    
    private static int quickSelect(int[] arr, int left, int right, int k) {
        if (left == right) return arr[left];
        
        int pivotIndex = partition(arr, left, right);
        
        if (k == pivotIndex) {
            return arr[k];
        } else if (k < pivotIndex) {
            return quickSelect(arr, left, pivotIndex - 1, k);
        } else {
            return quickSelect(arr, pivotIndex + 1, right, k);
        }
    }
    
    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left;
        
        for (int j = left; j < right; j++) {
            if (arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        
        swap(arr, i, right);
        return i;
    }
    
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    // Solution 5: Using TreeSet (for unique elements)
    public static int kthSmallestUsingTreeSet(int[] arr, int k) {
        if (k < 1 || k > arr.length) {
            throw new IllegalArgumentException("Invalid k");
        }
        
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int num : arr) {
            treeSet.add(num);
        }
        
        if (k > treeSet.size()) {
            throw new IllegalArgumentException("k exceeds number of unique elements");
        }
        
        return new ArrayList<>(treeSet).get(k - 1);
    }
    
    // Solution 6: Using Streams
    public static int kthSmallestUsingStreams(int[] arr, int k) {
        return Arrays.stream(arr)
                .sorted()
                .skip(k - 1)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid k"));
    }
    
    // Bonus: Find kth smallest with duplicates handling
    public static int kthSmallestWithDuplicates(int[] arr, int k) {
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int num : arr) {
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }
        
        List<Integer> uniqueSorted = frequency.keySet().stream()
                .sorted()
                .collect(Collectors.toList());
        
        int count = 0;
        for (int num : uniqueSorted) {
            count += frequency.get(num);
            if (count >= k) {
                return num;
            }
        }
        
        throw new IllegalArgumentException("Invalid k");
    }
    
    public static void main(String[] args) {
        int[] arr = {7, 10, 4, 3, 20, 15, 8, 12, 6, 9};
        int k = 4;
        
        System.out.println("Original Array: " + Arrays.toString(arr));
        System.out.println("k = " + k);
        
        System.out.println("\n--- Kth Smallest (k=" + k + ") ---");
        System.out.println("1. Using Sorting: " + kthSmallestUsingSort(arr, k));
        System.out.println("3. Using Max Heap: " + kthSmallestUsingMaxHeap(arr, k));
        System.out.println("4. Quick Select: " + kthSmallestQuickSelect(arr, k));
        System.out.println("5. Using TreeSet: " + kthSmallestUsingTreeSet(arr, k));
        System.out.println("6. Using Streams: " + kthSmallestUsingStreams(arr, k));
        
        System.out.println("\n--- Kth Largest (k=" + k + ") ---");
        System.out.println("1. Using Sorting: " + kthLargestUsingSort(arr, k));
        System.out.println("2. Using Min Heap: " + kthLargestUsingMinHeap(arr, k));
        
        // Test with duplicates
        int[] arrWithDuplicates = {7, 10, 4, 3, 20, 15, 7, 10, 6, 6};
        System.out.println("\n--- With Duplicates ---");
        System.out.println("Array: " + Arrays.toString(arrWithDuplicates));
        System.out.println("Kth smallest with duplicates: " + 
                          kthSmallestWithDuplicates(arrWithDuplicates, 5));
        
        // Performance test
        System.out.println("\n--- Performance Test (Large Array) ---");
        Random rand = new Random();
        int[] largeArray = new int[1000000];
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = rand.nextInt(1000000);
        }
        
        long start = System.currentTimeMillis();
        int result = kthSmallestQuickSelect(largeArray.clone(), 500000);
        long end = System.currentTimeMillis();
        System.out.println("Quick Select: " + (end - start) + " ms");
        
        start = System.currentTimeMillis();
        result = kthSmallestUsingSort(largeArray.clone(), 500000);
        end = System.currentTimeMillis();
        System.out.println("Sorting: " + (end - start) + " ms");
    }
}
