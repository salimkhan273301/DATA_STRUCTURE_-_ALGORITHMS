package java8String;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CharFrequencyCount {


	    public static void main(String[] args) 
	    {
	        String inputString = "Java Concept Of The Day for me";
	         
	        Map<Character, Long> charCountMap = 
	                    inputString
	                    .toUpperCase()
	                    .chars()
	                    .filter(c->c!=' ')
	                                .mapToObj(c -> (char) c)
	                                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
	         
	        System.out.println(charCountMap);
	        
	    char c= charCountMap.entrySet()
            .stream().max(Map.Entry.comparingByValue())
            
            .map(Map.Entry::getKey).get();
	    
	    System.out.println("Max Time came Char:: "+c);
	    }
	
}
