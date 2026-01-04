package java8.reduce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class CombineTwoList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Integer> list=Arrays.asList(1,2,3,4,5);
		List<Integer> list1=Arrays.asList(6,7,8,9,10);
		
		//List<Integer> combined=Stream.of(list,list1).flatMap(List::stream).toList();
		//System.out.println(combined);
		
		
		// java 8 
		List<Integer> combined1=Stream.of(list,list1).reduce(new ArrayList<Integer>(), (combiner,e)->{
			combiner.addAll(e);
		
		return combiner;
		});
		
		
		System.out.println(combined1);
		
		List<Integer> combiner2=Stream.of(list, list1).reduce(new LinkedList<Integer>(), (com,e)->{
			com.addAll(e);
			return com;
			
		});
		
		System.out.println(combiner2);
		
		// java 15
		List<Integer> combiner3=Stream.of(list,list1).reduce(List.of(),(a,b)->Stream.concat(a.stream(),b.stream()).toList() );
		
		System.out.println(combiner3);

	}

}
