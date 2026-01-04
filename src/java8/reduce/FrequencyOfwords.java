package java8.reduce;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FrequencyOfwords {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		  List<String> names = Arrays.asList(
		            "Alice", "Bob", "Charlie", "David", "Eva","Eva",
		            "Frank", "Grace", "Henry", "Ivy", "Jack"
		        );
		  
		  Map<String,Integer> freqn=names.stream().map(String::toUpperCase).collect(Collectors.toMap(k->k+" "+"khan", v->v.length(),Integer::sum));
		  
		  System.out.println(freqn);
		  
		  Map<String,Integer> map=names.stream().map(String::toUpperCase).reduce(new HashMap<String,Integer>(), (map_,e)->{
			  map_.put(e,map_.getOrDefault(e,0)+1);
			  return map_;
		  },(map1,map2)->{
			  map2.forEach((k,v)->map1.merge(k,v,Integer::sum));
			  
			  return map1;
		  });
		  
		  System.out.println(map);
		  
		  
		  Map<String, Integer> map2 = names.stream()
				    .map(String::toUpperCase)
				    .collect(Collectors.toMap(
				        Function.identity(),
				        e -> 1,
				        Integer::sum
				    ));
		  
		  System.out.println(map2);
		  
		// Example 4: Collect into a TreeMap (sorted keys)
		  Map<String, Integer> sortedNameLength = names.stream()
		      .collect(Collectors.toMap(
		          Function.identity(),
		          String::length,
		          (oldVal, newVal) -> oldVal,  // Merge function (ignores duplicates)
		          TreeMap::new                  // Map supplier (TreeMap)
		      ));

		  // Output: Keys are sorted alphabetically
		  
		  System.out.println(sortedNameLength);
		  
//============================================================================================		  
		  List<String> namesWithNull = Arrays.asList("Alice", null, "Bob");

		// Filter nulls before collecting
		Map<String, Integer> filteredMap = namesWithNull.stream()
		    .filter(Objects::nonNull)
		    .collect(Collectors.toMap(
		        Function.identity(),
		        String::length
		    ));

		  System.out.println(filteredMap);
		  
		// Output: {Alice=5, Bob=3}
		  
		//  ========================================================================
				  
				  record Person(String name, int age) {}

		  List<Person> people = List.of(
		      new Person("Alice", 25),
		      new Person("Bob", 30)
		  );

		  // Example 7: Map name to age
		  Map<String, Integer> nameToAge = people.stream()
		      .collect(Collectors.toMap(
		          Person::name,   // Key: name
		          Person::age     // Value: age
		      ));

		  // Output: {Alice=25, Bob=30}
		  
		  
		  System.out.println(nameToAge);
		  
		  
	}

}
