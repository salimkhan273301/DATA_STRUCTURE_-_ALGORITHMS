package java8.reduce;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StringListOprations {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		  List<String> names = List.of(
		            "Alice", "Bob", "Charlie", "David", "Eva","Eva",
		            "Frank", "Grace", "Henry", "Ivy", "Jack"
		        );
		  
		  List<Integer> nameLength=names.stream().map(String::length).sorted().toList();
		  System.out.println(nameLength);
		  
		  List<String> reverseOrder=names.parallelStream().sorted(Comparator.reverseOrder()).toList();
		  System.out.println(reverseOrder);
		  
		 int sumOfEachNameLength= names.stream().map(String::length).reduce(0,Integer::sum);
		 System.out.println(sumOfEachNameLength);
		 
		 
		 System.out.println("==============================================");
		 // to handel the duplicate data i have written this marger (value,newvalue)->value
		 Map<String,Integer> map=names.stream().map(String::toUpperCase).collect(Collectors.toMap(e->e, v->v.length(),(value,newvalue)->value= +newvalue));
		 
		 System.out.println(map);

	}

}
