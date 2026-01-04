package com.leetcode;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValidPalindrome {
	
	 public static boolean isPalindrome(String s) {
	        // Step 1: Remove all non-alphanumeric characters and convert to lowercase
	        String cleaned = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

	        // Step 2: Check if cleaned string equals its reverse
	        String reversed = new StringBuilder(cleaned).reverse().toString();

	        return cleaned.equals(reversed);
	    }

	
    public static boolean isPalindromeCreationPossible(String s) {
    	
        Map<Character, Long> freq=s.replaceAll("[^a-zA-Z]", "").chars().mapToObj(e->(char)e)
        		.map(e->Character.toLowerCase(e))
        		.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
       Long count= freq.entrySet().stream().filter(e->
        	e.getValue()%2!=0
        ).count();
       System.out.println(freq);
       System.out.println(count);
       if(count<=1) {
    	   return true;
       }
        
        return false;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1="race a car";
		String s="A man, a plan, a canal: Panama";
		
		boolean flag=isPalindrome(s1);
		
		System.out.println(flag);
		
		

	}

}
