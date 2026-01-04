package java8String;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FrequencyOfWOrds {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> words = Arrays.asList("apple", "banana", "apple", "cherry", "banana", "apple");
		
		Map<String,Long> map=words.stream().collect(Collectors.groupingBy(
				Function.identity(),
				Collectors.counting()
				));

		System.out.println(map);
		

		Map<String,Long> map0=words.stream().collect(Collectors.groupingBy(
				Function.identity(),// to find the identical
				LinkedHashMap::new,//to maintain the insertion order
				Collectors.counting()// counting the identical words occurence in the list
				));

		System.out.println(map);
		
		
		Map<String,Long> map1=words.stream().collect(Collectors.groupingBy(
				x->x,// to find the identical
				LinkedHashMap::new,//to maintain the insertion order
				Collectors.counting()// counting the identical words occurence in the list
				));

		System.out.println(map1);
		
		
		
		Map<String, Integer> wordFrequencyMap = words.stream()
                .collect(Collectors.groupingBy(
                    word -> word, // Group by word itself
                    Collectors.summingInt(word -> 1) // Sum counts
                ));

          System.out.println("Word Frequencies: " + wordFrequencyMap);
          
          
          Map<String, Integer> wordFrequencyMap1 = words.stream()
                  .collect(Collectors.groupingBy(
                      word -> word, // Group by word itself
                      Collectors.reducing(0, x->1, Integer::sum) // Reduce to count
                  ));

System.out.println("Word Frequencies: " + wordFrequencyMap1);
		
		
		Map<String,Integer> map2=words.stream().collect(Collectors.toMap(x->x,x->1,Integer::sum));
		
		System.out.println(map2);
		
		
		
		
		
		

	}

}
