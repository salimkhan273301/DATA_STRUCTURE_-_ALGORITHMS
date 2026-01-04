package com.leetcode;

/*Given a string s, return true if the s can be palindrome after deleting at most one character from it.

		 

Example 1:

Input: s = "aba"
Output: true
Example 2:

Input: s = "abca"
Output: true
Explanation: You could delete the character 'c'.
Example 3:

Input: s = "abc"
Output: false*/
 
public class ValidPalindrom {
	

	    public static boolean validPalindrome(String s) {
	        int left = 0, right = s.length() - 1;

	        while (left < right) {
	            // If mismatch, try removing one character from either side
	            if (s.charAt(left) != s.charAt(right)) {
	                return isPalindrome(s, left + 1, right) || isPalindrome(s, left, right - 1);
	            }
	            left++;
	            right--;
	        }

	        return true;
	    }

	    private static boolean isPalindrome(String s, int left, int right) {
	        while (left < right) {
	            if (s.charAt(left) != s.charAt(right)) return false;
	            left++;
	            right--;
	        }
	        return true;
	    }
	


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String input="abca";
		boolean flag=validPalindrome(input);
		System.out.println(flag);

	}

}
