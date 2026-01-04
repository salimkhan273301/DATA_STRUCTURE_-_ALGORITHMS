package basic.questions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PossibleityOfPalindromByrearrangingorSame {
	
	private static List<String> isPalindromList(String[] words) {
		
		
		
		
		
		
		return Arrays.stream(words).filter(e->{
		Long count=	e.chars().mapToObj(d->(char)d).collect(Collectors.groupingBy(d->d, Collectors.counting())).values().stream().filter(d->d%2!=0).count();
		if(count<=1)
			return true;
		else
			return false;
		}).collect(Collectors.toList());
		
		}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 String[] words = {"civic", "level", "deified", "ivicc", "rotor","xyz"};
		 
		 
		 List<String> palindromList=isPalindromList(words);
		 System.out.println(palindromList);
		 

	}

	/*
	 * private static List<String> isPalindromList(String[] words) { List<String>
	 * list= Arrays.stream(words).filter(e->{ Long
	 * count=e.chars().mapToObj(d->(char)d).collect(Collectors.groupingBy(Function.
	 * identity(),Collectors.counting())).values().stream().filter(d->d%2!=0).count(
	 * ); if(count<=1) return true; return false; }).collect(Collectors.toList());
	 * return list; }
	 */
	
	
	

}
