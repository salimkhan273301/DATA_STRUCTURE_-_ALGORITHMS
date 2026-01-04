package basic.questions;

import java.util.Arrays;

public class FindLargestAndSmallestNo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int arr[] = {};
		
		/*
		 * int min=arr[0]; int max=arr[0];
		 * 
		 * for(int e:arr) {
		 * 
		 * if(e>max) { max=e; } if(e<min) {
		 * 
		 * min=e; }
		 * 
		 * }
		 * 
		 * System.out.println("MIN Value::"+min); System.out.println("MAX Value::"+max);
		 */
		         
		System.out.println("======================================================");
		
		Arrays.stream(arr).sorted().limit(3).forEachOrdered(x->System.out.println(x));
		
		int minvalue=Arrays.stream(arr).min().orElse(-1);
		
		if(minvalue!=-1) {
		System.out.println(minvalue);
		}
		else {
			
		}
			
		
	}

}
