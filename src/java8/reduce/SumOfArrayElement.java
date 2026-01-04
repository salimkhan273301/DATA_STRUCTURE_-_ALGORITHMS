package java8.reduce;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;

public class SumOfArrayElement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//int[] arr= {1,2,3,4,56,7,8,9,10};
		List<Integer> list=Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		int sum=list.parallelStream().reduce(0,Integer::sum,Integer::sum);
		int sum1=list.stream().reduce(Integer::sum).get();
		int sum2=list.parallelStream().reduce(Integer::sum).get();
		int sum3=list.stream().reduce(0, Integer::sum);
		
		System.out.println(sum);
		System.out.println(sum2);
		
		System.out.println("==========================================");
		
		int[] arr= {1,2,3,4,5,6,7,8,9,10};
		int sum4=Arrays.stream(arr).sum();
		System.out.println(sum4);
		int sum5=Arrays.stream(arr).reduce(0,(total,e)->total+e);
		System.out.println(sum5);
		
		System.out.println("====================================================");
		
		OptionalInt sum6=Arrays.stream(arr).reduce((total,e)->total+e);
		System.out.println(sum6.getAsInt());
		
		int sum7=Arrays.stream(arr).reduce(Integer::sum).getAsInt();//.reduce(0,Integer::sum);
		
		System.out.println(sum7);

	}

}
