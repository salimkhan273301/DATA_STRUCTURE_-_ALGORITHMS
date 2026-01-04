package java8String;

import java.util.Arrays;
import java.util.List;

public class JavaImpTopics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		List<? super Number> list=Arrays.asList(1,2.2,3,3.3,4);
		list.add(4.4);
		
//		for(Number data:list) {
//			System.out.println(data);
//		}
//	
		

	}

}
