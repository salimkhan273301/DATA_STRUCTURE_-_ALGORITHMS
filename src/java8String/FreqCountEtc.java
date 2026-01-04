package java8String;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FreqCountEtc {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Map<String,Integer> freq=new LinkedHashMap<>();
		
		
				
		
		List<String> words = Arrays.asList("apple", "banana", "apple", "orange", "banana", "mango");
		
	Map<String,Long> map=words.stream().map(String::toUpperCase).collect(Collectors.groupingBy(w->w,Collectors.counting()));
	System.out.println(map);
	
	
	System.out.println("===========================2nd Way==========================");
	
	words.forEach(word->freq.merge(word.toUpperCase(), 1,Integer::sum));
	
	System.out.println(freq);
	
	System.out.println("===========================2nd Way==========================");
	
	 Map<String, Integer> frequencyMap = words.stream()
             .map(String::toLowerCase) // Convert all words to lowercase
             .collect(Collectors.toMap(
                 w -> w, // Key mapper (word itself)
                 w -> 1, // Value mapper (initialize count to 1)
                 Integer::sum, // Merge function (sum counts for duplicate keys)
                 LinkedHashMap::new // Use LinkedHashMap to preserve insertion order
             ));
System.out.println(frequencyMap); // {apple=3, banana=2, orange=1}


System.out.println("===========================2nd Way==========================");

Map<String,Integer> fre=words.stream().collect(Collectors.groupingBy(w->w,Collectors.reducing(0,w->1,Integer::sum)));

System.out.println(fre);

System.out.println("===========================2nd Way==========================");
// Create a frequency map using Stream.collect with a mutable HashMap
Map<String, Integer> frequencyMap1 = words.stream()
                                        .collect(
                                            HashMap::new, // Supplier: Creates a new HashMap to store the results
                                            (map_my, word) -> map_my.merge(word, 1, Integer::sum), // Accumulator: Updates the map with word frequencies
                                            Map::putAll // Combiner: Merges two maps (used in parallel streams)
                                        );

// Print the frequency map
System.out.println(frequencyMap1); // Output: {orange=1, banana=2, apple=3}

System.out.println("===========================2nd Way==========================");




// Create a frequency map using Stream.reduce
Map<String, Integer> frequencyMap2 = words.stream()
                                        .reduce(
                                            new HashMap<>(), // Identity: Initial empty HashMap
                                            (map_1, word) -> { // Accumulator: Updates the map with word frequencies
                                                map_1.merge(word, 1, Integer::sum);
                                                return map_1;
                                            },
                                            (map1, map2) -> { // Combiner: Merges two maps (used in parallel streams)
                                                map2.forEach((key, value) -> map1.merge(key, value, Integer::sum));
                                                return map1;
                                            }
                                        );

// Print the frequency map
System.out.println(frequencyMap2); // Output: {orange=1, banana=2, apple=3}

	}

}
