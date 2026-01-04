package java8String;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ElementCountDemo {
	
	
	
	public static void main(String[] args) 
    {
        List<String> stationeryList = Arrays.asList("Pen", "Eraser", "Note Book", "Pen", "Pencil", "Stapler", "Note Book", "Pencil");
         
        Map<String, Long> stationeryCountMap = 
                stationeryList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
         
        System.out.println(stationeryCountMap);
        
        
        
       
        String input = "apple banana apple cherry banana apple";
        
        // to check the words frequency in  the string
        
        Map<String,Long> map=Arrays.stream(input.split(" ")).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        
        
        System.out.println(map);
        
        Map<String,Integer> modifiedmap=Arrays.stream(input.split(" ")).sorted().collect(Collectors.toMap(x->x, x->1,Integer::sum));
        System.out.println(modifiedmap);
        
        
    }
	
}
