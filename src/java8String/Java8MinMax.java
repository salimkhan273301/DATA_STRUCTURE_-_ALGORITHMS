package java8String;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Java8MinMax {
	
	
	public static void main(String[] args) {
	        List<Integer> listOfIntegers = Arrays.asList(45, 12, 56, 15, 24, 75, 31, 89);
	         
	        int max = listOfIntegers.stream().max(Comparator.naturalOrder()).get();
	         
	        System.out.println("Maximum Element : "+max);
	         
	        int min = listOfIntegers.stream().min(Comparator.naturalOrder()).get();
	         
	        System.out.println("Minimum Element : "+min);
	        
	        int min1=listOfIntegers.stream().max((a,b)->b-a).get();
	        
	        
	        System.out.println("Min"+min1);
	        
	        int max1=listOfIntegers.stream().max((a,b)->a-b).get();
	        
	        System.out.println("Max"+max1);
	        
	        List<Integer> sorted=listOfIntegers.stream().sorted((a,b)->a.compareTo(b)).collect(Collectors.toList());
	        System.out.println(sorted);
	        
	        List<Integer> dec_sorted=listOfIntegers.stream().sorted((a,b)->b.compareTo(a)).collect(Collectors.toCollection(ArrayList::new));
	        
	        System.out.println(dec_sorted);
	        		
	    }

}
