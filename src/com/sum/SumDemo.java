package com.sum;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SumDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int arr[]= {1,2,3,4,5,6,7,8,9};
		int sum=Arrays.stream(arr)
				.boxed()// for converting int to Integer
				.collect(Collectors.summingInt(Integer::intValue));
		System.out.println(sum);
		
		
		Integer sum1 = Stream.of(1, 2, 3)
			    .collect(Collectors.summingInt(Integer::intValue));
		System.out.println(sum);
			// Result: 6
		
		IntSummaryStatistics stats = Stream.of(1, 2, 3)
			    .collect(Collectors.summarizingInt(Integer::intValue));
			// Contains count, sum, min, average, max
		
		
		
		System.out.println(stats);
		
		Double avg = Stream.of(1, 2, 3)
			    .collect(Collectors.averagingInt(Integer::intValue));
			// Result: 2.0
	}

}
