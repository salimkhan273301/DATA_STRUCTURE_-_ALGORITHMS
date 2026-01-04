package java8String;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FilterTheArr {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Integer> list=Arrays.asList(3,6,2,5,1,4,8,7,9,11,13,14,16,15,15,1,4,5,6,7,8,5,5);
		
		//Map<String,List<Integer>> map=list.stream().collect(Collectors.groupingBy(x->x%2==0?"EVEN":"ODD"));
		//map.forEach((x,y)->System.out.println(x+":"+y));
		Map<Integer,Long> map=list.stream().collect(Collectors.groupingBy(x->x,Collectors.counting()));
		
		System.out.println(map);
		List<Integer> dublicateList=map.entrySet().stream().filter(x-> x.getValue()>1)
				.map(Map.Entry::getKey)
				.collect(Collectors.toList());
		
		System.out.println(dublicateList);
		
		List<Integer> oddList=map.entrySet().stream()
				.filter(x-> x.getValue()==1)
				.map(Map.Entry::getKey)
				.collect(Collectors.toList());
		
		System.out.println(oddList);
		
		
		 
		

	}

}
