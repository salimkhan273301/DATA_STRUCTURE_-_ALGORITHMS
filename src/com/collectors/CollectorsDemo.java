package com.collectors;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Optional<String> max = Stream.of("a", "bb", "ccc")
			    .collect(Collectors.maxBy(Comparator.comparing(String::length)));
			// Result: Optional["ccc"]
		
		String joined = Stream.of("a", "b", "c")
			    .collect(Collectors.joining(", "));
			// Result: "a, b, c"
		
		String joined1 = Stream.of("a", "b", "c")
			    .collect(Collectors.joining(", ", "[", "]"));
			// Result: "[a, b, c]"
		
		List<Integer> lengths = Stream.of("a", "bb", "ccc")
			    .collect(Collectors.mapping(
			        String::length,
			        Collectors.toList()
			    ));
			// Result: [1, 2, 3]
		
		List<String> filtered = Stream.of("a", "bb", "ccc", "dddd")
			    .collect(Collectors.filtering(
			        s -> s.length() <= 2,
			        Collectors.toList()
			    ));
			// Result: ["a", "bb"]
		
		
		Optional<Integer> sum = Stream.of(1, 2, 3)
			    .collect(Collectors.reducing(Integer::sum));
			// Result: Optional[6]
		
		Integer sum3 = Stream.of(1, 2, 3)
			    .collect(Collectors.reducing(0, Integer::sum));
			// Result: 6
		
		
		Integer sumOfSquares = Stream.of(1, 2, 3)
			    .collect(Collectors.reducing(
			        0,
			        n -> n * n,
			        Integer::sum
			    ));
			// Result: 14 (1 + 4 + 9)

	}

}
