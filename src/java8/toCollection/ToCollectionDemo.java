package java8.toCollection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ToCollectionDemo {

	public static void main(String[] args) {
		
		//Stream stream=Stream.of(1,2,3,4,5,6,7,8,9);
		Stream<String> stream=Stream.of("SALIM","NADEEM","HASAN","REHAN","SALMAN","SADAB");
		
		Set<String> hashSet = stream.collect(Collectors.toCollection(HashSet::new));
		Set<String> linkedSet = stream.collect(Collectors.toCollection(LinkedHashSet::new));
		
		System.out.println(hashSet);
		// TODO Auto-generated method stub
		
		List<String> names = Arrays.asList(
	            "Alice", "Bob", "Charlie", "David", "Eva","Eva",
	            "Frank", "Grace", "Henry", "Ivy", "Jack"
	        );

		List<String> arrayList = names.stream().collect(Collectors.toCollection(ArrayList::new));
		List<String> linkedList = names.stream().collect(Collectors.toCollection(LinkedList::new));
	}
	List<Integer> numbers = Stream.of(1, 2, 3)
		    .collect(Collectors.toCollection(LinkedList::new));
	
	
	
	Set<String> sortedSet = Stream.of("banana", "apple", "cherry")
		    .collect(Collectors.toCollection(() -> new TreeSet<>(String.CASE_INSENSITIVE_ORDER)));
		// Result: TreeSet containing ["apple", "banana", "cherry"] (case-insensitive order)
	
	
	
	Queue<Integer> priorityQueue = Stream.of(5, 3, 8, 1)
		    .collect(Collectors.toCollection(PriorityQueue::new));
		// Elements will be ordered according to their natural ordering
	
	
	List<String> orderedList = Stream.of("z", "a", "b")
		    .collect(Collectors.toCollection(LinkedList::new));
		// Maintains exact insertion order: ["z", "a", "b"]

		Set<String> orderedSet = Stream.of("z", "a", "b", "a")
		    .collect(Collectors.toCollection(LinkedHashSet::new));
		// Maintains insertion order with no duplicates: ["z", "a", "b"]

}
