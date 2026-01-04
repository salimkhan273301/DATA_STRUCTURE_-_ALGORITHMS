package com.leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class MaxSumofaPairWithEqualSumofDigits {
	
	
	    public int maximumSum(int[] nums) {
	        HashMap<Integer, Integer> digitSumToMaxNum = new HashMap<>();
	        int maxSum = -1;

	        for (int num : nums) {
	            int digitSum = getDigitSum(num);
	            
	            if (digitSumToMaxNum.containsKey(digitSum)) {
	                // Update maxSum if current pair sum is larger
	                int currentMax = digitSumToMaxNum.get(digitSum);
	                maxSum = Math.max(maxSum, currentMax + num);
	                // Update the map to store the larger number for this digit sum
	                digitSumToMaxNum.put(digitSum, Math.max(currentMax, num));
	            } else {
	                digitSumToMaxNum.put(digitSum, num);
	            }
	        }

	        return maxSum;
	    }

	
	    
	 
	        public int maximumSum1(int[] nums) {
	            return Arrays.stream(nums)
	                .boxed()
	                .collect(Collectors.groupingBy(
	                    this::getDigitSum,
	                    Collectors.toList()
	                ))
	                .values()
	                .stream()
	                .filter(list -> list.size() >= 2)  // Ensure at least 2 numbers
	                .map(list -> {
	                    Collections.sort(list, Collections.reverseOrder());
	                    return list.get(0) + list.get(1);  // Sum of top 2 largest
	                })
	                .max(Integer::compare)
	                .orElse(-1);
	        }

	        // for sum of digit i have created this
	        private int getDigitSum(int num) {
	            int sum = 0;
	            while (num > 0) {
	                sum += num % 10;
	                num /= 10;
	            }
	            return sum;
	        }
	        
	       
	            public int maximumSum2(int[] nums) {
	                // Step 1: Group numbers by their digit sums
	                Map<Integer, List<Integer>> digitSumToNumbers = Arrays.stream(nums)
	                    .boxed()
	                    .collect(Collectors.groupingBy(
	                        this::getDigitSum1  // Key: Digit sum, Value: List of numbers
	                    ));
	                
	                System.out.println(digitSumToNumbers);

	                // Step 2: Filter groups with at least 2 numbers
	                List<List<Integer>> validGroups = digitSumToNumbers.values()
	                    .stream()
	                    .filter(group -> group.size() >= 2)  // Only keep groups with ≥2 numbers
	                    .collect(Collectors.toList());

	                System.out.println(validGroups);
	                // Step 3: Find the maximum sum of the top 2 numbers in each group
	                return validGroups.stream()
	                    .mapToInt(group -> {
	                        // Sort in descending order and pick the top 2
	                        Collections.sort(group, Collections.reverseOrder())
	                        ;
	                        return group.get(0) + group.get(1);
	                    })
	                    .max()  // Find the largest sum among all groups
	                    .orElse(-1);  // Return -1 if no valid pairs exist
	               
	            }

	            // Helper method to compute the digit sum of a number
	            private int getDigitSum1(int num) {
	                int sum = 0;
	                while (num > 0) {
	                    sum += num % 10;
	                    num /= 10;
	                }
	                return sum;
	            }
	        
	            public int maximumSum3(int[] nums) {
	                // Step 1: Group numbers by digit sum and keep only the top 2 numbers per group
	                Map<Integer, PriorityQueue<Integer>> digitSumToTopTwo = Arrays.stream(nums)
	                    .boxed()
	                    .collect(Collectors.groupingBy(
	                        this::getDigitSum3,
	                        Collectors.toCollection(() -> new PriorityQueue<>(Comparator.reverseOrder()))
	                    ));

	                // Step 2: Compute maximum sum of top 2 numbers from each group
	                return digitSumToTopTwo.values()
	                    .stream()
	                    .filter(pq -> pq.size() >= 2)  // Ensure at least 2 numbers
	                    .mapToInt(pq -> {
	                        int first = pq.poll();  // Largest number
	                        int second = pq.poll(); // Second largest
	                        return first + second;
	                    })
	                    .max()
	                    .orElse(-1);  // Return -1 if no valid pairs
	            }

	            // Helper to compute digit sum
	            private int getDigitSum3(int num) {
	                int sum = 0;
	                while (num > 0) {
	                    sum += num % 10;
	                    num /= 10;
	                }
	                return sum;
	            }
	

	    public static void main(String[] args) {
	    	MaxSumofaPairWithEqualSumofDigits sol = new MaxSumofaPairWithEqualSumofDigits();
	        System.out.println(sol.maximumSum2(new int[]{18, 43, 36, 13, 7})); // 54
	        System.out.println("====================================");
	        System.out.println(sol.maximumSum2(new int[]{10, 12, 19, 14}));
	        System.out.println("====================================");// -1
	        System.out.println(sol.maximumSum2(new int[]{9, 81, 72, 63}));     // 153 (81 + 72)
	    }
}
