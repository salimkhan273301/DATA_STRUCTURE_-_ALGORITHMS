package com.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {
	
	
// brute force to solve it
	
	public static int lengthOfLongestSubstring3(String s) {
	    int n = s.length();
	    int maxLength = 0;
	    
	    // Check all possible substrings
	    for (int i = 0; i < n; i++) {
	        for (int j = i; j < n; j++) {
	            // Check if substring s[i..j] has all unique characters
	            if (allUnique(s, i, j)) {
	                maxLength = Math.max(maxLength, j - i + 1);
	            }
	        }
	    }
	    return maxLength;
	}

	private static boolean allUnique(String s, int start, int end) {
	    Set<Character> set = new HashSet<>();
	    for (int i = start; i <= end; i++) {
	        char c = s.charAt(i);
	        if (set.contains(c)) {
	            return false;
	        }
	        set.add(c);
	    }
	    return true;
	}
	
	//=========================end of brute force==============================
	
	//==============================sliding window problem solving technique==============================
	public static int lengthOfLongestSubstring4(String s) {
	    int n = s.length();
	    Set<Character> set = new HashSet<>();
	    int maxLength = 0, left = 0, right = 0;
	    
	    while (right < n) {
	        if (!set.contains(s.charAt(right))) {
	            set.add(s.charAt(right));
	            maxLength = Math.max(maxLength, right - left + 1);
	            right++;
	        } else {
	            set.remove(s.charAt(left));
	            left++;
	        }
	    }
	    return maxLength;
	}

	//============Optimized Sliding Window (O(2n))=======================================
	public static int lengthOfLongestSubstring(String s) {

		int maxLength = 0;
		int w_srink = 0;

		HashSet<Character> set = new HashSet<Character>();

		for (int w_expend = 0; w_expend < s.length(); w_expend++) {

			while (w_srink < w_expend && set.contains(s.charAt(w_expend))) {
				set.remove(s.charAt(w_srink));
				w_srink++;
			}
			set.add(s.charAt(w_expend));

			maxLength = Math.max(maxLength, w_expend - w_srink + 1);
		}
		return maxLength;

	}
	
	
	//============Optimized Sliding Window (O(n))=======================================
	public static int lengthOfLongestSubstring2(String s) {

	
		int start = 0,end=0,maxLength=0;
		

		Map<Character,Integer> map = new HashMap<Character,Integer>();

		while(end<s.length()){
			
			if(map.containsKey(s.charAt(end))) {
				start=map.get(s.charAt(end))+1;
				
				
			}
			map.put(s.charAt(end), end);

			maxLength = Math.max(maxLength, end - start + 1);
			end++;
		}

			
		
		
		return maxLength;

	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "abcabcbb";
		String s1="bbbbb";
		String s3="pwwkew";
		int result = lengthOfLongestSubstring2(s3);
		System.out.println("Longest Substring Without Repeating Characters::" + result);

	}

}
