package java8String;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindFIrstReaptingChar {

	    public static void main(String[] args) {
	        String input = "java programming";

	        Character firstNonRepeated = input.chars() // Convert to IntStream
	                                          .mapToObj(c -> (char) c) // Convert to Stream<Character>
	                                          .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting())) // Group by character and count
	                                          .entrySet()
	                                          .stream()
	                                          .filter(entry -> entry.getValue() == 1) // Filter non-repeated characters
	                                          .map(Map.Entry::getKey)
	                                          .findFirst() // Get the first non-repeated character
	                                          .orElse(null);

	        System.out.println("First Non-Repeated Character: " + firstNonRepeated); // Output: j
	    }
	}

