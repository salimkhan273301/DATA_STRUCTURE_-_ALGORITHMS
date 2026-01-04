package java8String;

import java.util.Arrays;
import java.util.List;

public class SpeedParrVsSeq {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		// Sequential Stream
		long startTime = System.currentTimeMillis();
		numbers.stream().forEach(System.out::println);
		long endTime = System.currentTimeMillis();
		System.out.println("Sequential Time: " + (endTime - startTime) + "ms");

		// Parallel Stream
		startTime = System.currentTimeMillis();
		numbers.parallelStream().forEach(System.out::println);
		endTime = System.currentTimeMillis();
		System.out.println("Parallel Time: " + (endTime - startTime) + "ms");

	}

}
