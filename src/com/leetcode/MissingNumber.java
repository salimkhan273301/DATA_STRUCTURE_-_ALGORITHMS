package com.leetcode;

public class MissingNumber {
	
	 public int missingNumber3(int[] nums) {
	        int summ=0;
	        int n=nums.length;
	        for(int num:nums)
	        {
	            summ+=num;
	        }
	        for(int i=1;i<=n;i++)
	        {
	            summ-=i;
	        }
	        return Math.abs(summ);
	    }
	public static int missingNumber(int[] nums) {
	    int missing = nums.length;
	    for (int i = 0; i < nums.length; i++) {
	        missing ^= i ^ nums[i];
	    }
	    return missing;
	}
	public int missingNumber2(int[] nums) {
	    int sumOfNumbers = 0;
	    int n = nums.length;
	    
	    // Sum all numbers in the array
	    for (int num : nums) {
	        sumOfNumbers += num;
	    }
	    
	    // Sum of first 'n' natural numbers: n*(n+1)/2
	    int expectedSum = n * (n + 1) / 2;
	    
	    // The missing number is the difference
	    return expectedSum - sumOfNumbers;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Test Case 1: Missing 2
	    int[] nums1 = {3, 0, 1};
	    System.out.println("Missing (Expected 2): " + missingNumber(nums1));

	    // Test Case 2: Missing 8
	    int[] nums2 = {9, 6, 4, 2, 3, 5, 7, 0, 1};
	    System.out.println("Missing (Expected 8): " + missingNumber(nums2));

	    // Test Case 3: Missing 0 (edge case)
	    int[] nums3 = {1, 2, 3};
	    System.out.println("Missing (Expected 0): " + missingNumber(nums3));

	    // Test Case 4: Missing 1 (edge case)
	    int[] nums4 = {0};
	    System.out.println("Missing (Expected 1): " + missingNumber(nums4));

	    // Test Case 5: Empty array (invalid input, but should handle gracefully)
	    int[] nums5 = {};
	    System.out.println("Missing (Expected 0): " + missingNumber(nums5));


	}

}
