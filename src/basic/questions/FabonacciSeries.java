package basic.questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class FabonacciSeries {
	
	private static int getFabonacciNumber1(int n) {
		int[]  arr=new int[n+1];
		
		if(n<=1)
			return n;
		
		return  getFabonacciNumber1(n-1)+ getFabonacciNumber1(n-2);
	}
	
	private static int[] getFabonacciNumber3(int n) {
		int[]  arr=new int[n];
		
		if(n==0)
			return new int[]{};
		if(n==1)
			return new int[]{0};
		
		
		arr[0]=0; 
		arr[1]=1;
		IntStream.range(2, n).forEach(i->{arr[i]=arr[i-1]+arr[i-2];});
		
	  
	    
		return arr ;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> fibonacciList=new ArrayList();
		
		for(int i=0; i<=10; i++) {
			
			
			fibonacciList.add(getFabonacciNumber1(i));
		}

		System.out.println(fibonacciList);
		int[] fib=getFabonacciNumber3(10);
		System.out.print(Arrays.toString(fib)+" ");
	}

	private static int getFabonacciNumber(int n) {
		int[]  arr=new int[n+1];
		
		if(n<=1)
			return n;
		
		
		arr[0]=0; 
		arr[1]=1;
		
		for (int i = 2; i <= n; i++) {
	        arr[i] = arr[i - 1] + arr[i - 2];
	    }
		return arr[n];
	}

}
