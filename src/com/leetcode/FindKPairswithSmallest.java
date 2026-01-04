package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class  FindKPairswithSmallest  {
	
	
	
	    public static List<List<Integer>> kSmallestPairs1(int[] nums1, int[] nums2, int k) {
	        List<List<Integer>> result = new ArrayList<>();
	        if (nums1.length == 0 || nums2.length == 0 || k == 0) {
	            return result;
	        }

	        // Min-heap to store pairs based on their sum
	        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> (a[0] + a[1]) - (b[0] + b[1]));

	         heap.stream().forEach(e->System.out.print(Arrays.toString(e)+","));
	        // Initialize the heap with the first element of nums2 paired with all elements of nums1
	        for (int i = 0; i < Math.min(nums1.length, k); i++) {
	            heap.offer(new int[]{nums1[i], nums2[0], 0});
	        }
	        heap.stream().forEach(e->System.out.print(Arrays.toString(e)+","));

	        while (k-- > 0 && !heap.isEmpty()) {
	            int[] current = heap.poll();
	            result.add(Arrays.asList(current[0], current[1]));
	            System.out.println();
	            
	            System.out.println(result);

	            // Check if there's a next element in nums2 to pair with current nums1 element
	            if (current[2] + 1 < nums2.length) {
	                heap.offer(new int[]{current[0], nums2[current[2] + 1], current[2] + 1});
	            }
	            
	            heap.stream().forEach(e->System.out.print(Arrays.toString(e)+","));
	            System.out.println();
	        }

	        return result;
	    }

	
	
	
    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0 || k == 0) {
            return result;
        }

        // Generate all possible pairs and their sums
        List<int[]> pairs = new ArrayList<>();
        for (int num1 : nums1) {
            for (int num2 : nums2) {
                pairs.add(new int[]{num1, num2, num1 + num2});
            }
        }
        pairs.stream().forEach(e->System.out.print(Arrays.toString(e)+","));
        System.out.println();

        // Sort the pairs based on their sums
        Collections.sort(pairs, (a, b) -> a[2] - b[2]);

        // Select the top k pairs
        for (int i = 0; i < Math.min(k, pairs.size()); i++) {
            int[] pair = pairs.get(i);
            result.add(Arrays.asList(pair[0], pair[1]));
        }

        return result;
    }
    
    public static List<List<Integer>> kSmallestPairs3(int[] nums1, int[] nums2, int k) {
        return Arrays.stream(nums1)
                .boxed()
                .flatMap(num1 -> Arrays.stream(nums2).peek(e->System.out.print(e+","))
                        .mapToObj(num2 -> new int[]{num1, num2}))
                .sorted(Comparator.comparingInt(pair -> pair[0] + pair[1]))
                .limit(k)
                .map(pair -> Arrays.asList(pair[0], pair[1]))
                .collect(Collectors.toList());
    }
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] arr1= {1,7,11};//1,1,1,1,1
		int[] arr2= {2,4,6};
		int k=3;
		List<List<Integer>> result=kSmallestPairs3(arr1,arr2,k);
		System.out.println(result);

	}
}
