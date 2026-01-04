package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupAnagrams {
	
	public static List<List<String>> groupAnagrams(String[] strs) {
	    Map<String, List<String>> map = new HashMap<>();
	    
	    for (String s : strs) {
	        int[] count = new int[26];
	        for (char c : s.toCharArray()) {
	            count[c - 'a']++;
	        }
	        
	        StringBuilder keyBuilder = new StringBuilder();
	        for (int i = 0; i < 26; i++) {
	            if (count[i] > 0) {
	                keyBuilder.append((char) ('a' + i)).append(count[i]);
	            }
	        }
	        String key = keyBuilder.toString();
	        
	        map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
	    }
	    
	    return new ArrayList<>(map.values());
	}
	
	public List<List<String>> groupAnagrams1(String[] strs) {
	    Map<String, List<String>> map = new HashMap<>();
	    
	    for (String s : strs) {
	        char[] chars = s.toCharArray();
	        Arrays.sort(chars);
	        String key = new String(chars);
	        
	        if (!map.containsKey(key)) {
	            map.put(key, new ArrayList<>());
	        }
	        map.get(key).add(s);
	    }
	    
	    return new ArrayList<>(map.values());
	}
	
	public List<List<String>> groupAnagrams2(String[] strs) {
	    return new ArrayList<>(
	        Arrays.stream(strs)
	            .collect(Collectors.groupingBy(
	                s -> {
	                    char[] chars = s.toCharArray();
	                    Arrays.sort(chars);
	                    return new String(chars);
	                }
	            ))
	            .values()
	    );
	}
	
	public List<List<String>> groupAnagrams4(String[] strs) {
	    Map<Long, List<String>> map = new HashMap<>();
	    int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
	    
	    for (String s : strs) {
	        long key = 1;
	        for (char c : s.toCharArray()) {
	            key *= primes[c - 'a'];
	        }
	        
	        map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
	    }
	    
	    return new ArrayList<>(map.values());
	}

	public static void main(String[] args) {
	    // Test Case 1: Basic Anagrams
	    String[] input1 = {"eat", "tea", "tan", "ate", "nat", "bat"};
	    System.out.println("Test 1: " + groupAnagrams(input1));
	    // Expected: [ ["eat","tea","ate"], ["tan","nat"], ["bat"] ]

	    // Test Case 2: Empty Input
	    String[] input2 = {};
	    System.out.println("Test 2: " + groupAnagrams(input2));
	    // Expected: []

	    // Test Case 3: No Anagrams
	    String[] input3 = {"hello", "world"};
	    System.out.println("Test 3: " + groupAnagrams(input3));
	    // Expected: [ ["hello"], ["world"] ]

	    // Test Case 4: Single Character Strings
	    String[] input4 = {"a", "a", "b"};
	    System.out.println("Test 4: " + groupAnagrams(input4));
	    // Expected: [ ["a","a"], ["b"] ]
	}

}
