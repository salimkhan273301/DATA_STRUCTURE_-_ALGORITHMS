package com.leetcode;

public class MakeAnagrams {
	
	public int minSteps(String s, String t) {
        int[] freqCount = new int[26]; // Tracks frequency differences
        
        // Count frequency of characters in 's'
        for (char c : s.toCharArray()) {
            freqCount[c - 'a']++;
        }
        
        // Adjust frequency by subtracting characters in 't'
        for (char c : t.toCharArray()) {
            freqCount[c - 'a']--;
        }
        
        // Sum absolute differences to get total steps
        int steps = 0;
        for (int count : freqCount) {
            steps += Math.abs(count);
        }
        
        // Each step fixes two differences (one excess in 's' and one deficit in 't')
        return steps / 2;
    }
	
	 public int minSteps1(String s, String t) {

	        int[] freq_count=new int[26];

	        for(char c:s.toCharArray())
	                freq_count[c-'a']++;

	                
	                
	        for(char c:t.toCharArray())
	                freq_count[c-'a']--;

	        int solution=0;
	         for(int i=0; i<26; i++){
	            solution+=Math.abs(freq_count[i]);
	         }       


	        return solution;
	        
	    }

	 public static void main(String[] args) {
		 MakeAnagrams solution = new MakeAnagrams();
		    
		    // Test Case 1: Basic case
		    String s1 = "bab";
		    String t1 = "aba";
		    System.out.println(solution.minSteps(s1, t1)); // Expected: 1

		    // Test Case 2: No steps needed
		    String s2 = "leetcode";
		    String t2 = "practice";
		    System.out.println(solution.minSteps(s2, t2)); // Expected: 5

		    // Test Case 3: All characters differ
		    String s3 = "abc";
		    String t3 = "def";
		    System.out.println(solution.minSteps(s3, t3)); // Expected: 3

		    // Test Case 4: Equal strings
		    String s4 = "anagram";
		    String t4 = "mangaar";
		    System.out.println(solution.minSteps(s4, t4)); // Expected: 0
		}
}
