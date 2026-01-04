package java8String;

import java.util.stream.IntStream;

public class CloseRangeDemo {

	public static boolean isPrime(int num) {
		if(num<2)
			return false ;
		for(int i=2; i<=Math.sqrt(num); i++) {
			if(num%i==0)
				return false;
			
		}
		return true;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		int sum = IntStream.rangeClosed(0, 10)
//				.filter(num -> num % 2 == 0)
//				//.peek(num -> System.out.println(num))
//				.sum();
//
//		System.out.println(sum);
//
//		
//		System.out.println("===========================================");
//		
//		int sum1=IntStream.rangeClosed(0, 100).filter(num->num%2!=0).reduce(0, Integer::sum);
//		
//		System.out.println(sum1);
//		System.out.println("===========================================");
//		int sum3=IntStream.rangeClosed(0,100).filter(num->num%2==0).reduce(0, (a,b)->a+b);
//		System.out.println(sum3);
		
		
		// printing the prime number in a range
		
		IntStream.rangeClosed(0, 1000).filter(CloseRangeDemo::isPrime).forEach(p->System.out.print(p+" "));
		System.out.println("===========================================");
	int primeSum=	IntStream.rangeClosed(0,100).filter(CloseRangeDemo::isPrime).reduce(0,Integer::sum);
	
	System.out.println(primeSum);
		
	}

}
