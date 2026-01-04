package java8String;

import java.util.Arrays;
import java.util.List;

public class SecondHeighestNo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		        List<Integer> numbers = Arrays.asList(10, 20, 30, 40, 50);

		        int secondHighest = numbers.stream()
		                                   .sorted((a, b) -> b.compareTo(a)) // Sort in descending order
		                                   .skip(1) // Skip the first element (highest)
		                                   .findFirst() // Get the second element
		                                   .orElse(-1); // Default value if list is empty

		        System.out.println("Second Highest: " + secondHighest); // Output: 40
		    }
		

	

}
