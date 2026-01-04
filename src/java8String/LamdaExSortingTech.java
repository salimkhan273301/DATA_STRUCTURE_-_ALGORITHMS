package java8String;

import java.util.Arrays;
import java.util.List;

public class LamdaExSortingTech {

	public static void main(String[] args) {
		// TODO Auto-generated method stubL
		
		List<String> name=Arrays.asList(new String[] {"A","B","C","D","E"});
		
	// sorting the array using lamda expressions
		// ascending order 
		name.sort((a,b)->a.compareToIgnoreCase(b));
		
		name.forEach(x->System.out.print(x+" "));
		System.out.println("--------------bellow is decending order sorting ------------------");
		name.sort((a,b)->b.compareToIgnoreCase(a));
		
		name.forEach(x->System.out.print(x+" "));


	}

}
