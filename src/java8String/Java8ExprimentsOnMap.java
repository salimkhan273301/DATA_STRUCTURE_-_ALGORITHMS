package java8String;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Java8ExprimentsOnMap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 String input = "java programming";
		 
        // Store the result in a Map
        Map<Character, Long> frequencyMap = input.chars() // Convert to IntStream
                                               .mapToObj(c -> (char) c) // Convert to Stream<Character>
                                               .collect(Collectors.groupingBy(
                                                   Function.identity(), // Key: Character
                                                   LinkedHashMap::new, // Preserve insertion order
                                                   Collectors.counting() // Value: Count of occurrences
                                               ));
        
        System.out.println(frequencyMap);
        
        frequencyMap.forEach((k,v)->System.out.print (k+":"+ v+" "));
        
	}

}
