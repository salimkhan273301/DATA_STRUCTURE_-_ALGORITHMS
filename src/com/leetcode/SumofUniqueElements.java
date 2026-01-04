package com.leetcode;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SumofUniqueElements {
	
	private static int sumOfUnique(int[] arr) {
	
		Map<Integer, Integer> freq=Arrays.stream(arr).boxed().collect(Collectors.toMap(e->e, v->1,Integer::sum));
		System.out.println(freq);
		
		   final int[] sum = {0};

	        freq.forEach((k, v) -> {
	            if (v == 1) {
	                sum[0] += k;
	            }
	        });

	        return sum[0]; 
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//int[] arr= {1,2,3,2};//1,1,1,1,1
		int[] arr= {1,1,1,1,1};
		int result=sumOfUnique(arr);
		System.out.println(result);

	}

	

}
