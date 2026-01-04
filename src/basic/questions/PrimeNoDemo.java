package basic.questions;

import java.util.Scanner;

public class PrimeNoDemo {

	public static boolean isPrime(int num) {
		
		 if (num <= 1) {
	            return false;
	        }

		for (int i = 2; i <= Math.sqrt(num); i++) {

			if (num % i == 0)
				return false;

		}

		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the last range till you want to find out prime No::");
		int number=sc.nextInt();
		
		for (int p=0; p<=number; p++)
		{
			if(isPrime(p)) {
				System.out.print(p+" ");
			}
		}

	}

}
