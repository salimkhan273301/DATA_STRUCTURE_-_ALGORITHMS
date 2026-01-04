package com.leetcode;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FirstUniqueCharacterinaString {
	
	private static int firstUniqChar(String s) {
		// TODO Auto-generated method stub
		Map<Character,Long> freq=s.chars().mapToObj(e->(char)e).collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new, Collectors.counting()));
		for(int i=0; i<s.length(); i++) {
			if(freq.get(s.charAt(i))==1)
				return i;
		}
		return -1;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String s = "leetcode";
		int position=firstUniqChar(s);
		System.out.println(position);
	}

	
}
