package basic.questions;

import java.util.Scanner;

public class ReverseAString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the String::");
		String str = sc.nextLine();

		String reverse = doReverse(str);
		System.out.println(reverse);

	}

	private static String doReverse(String str) {
		// TODO Auto-generated method stub(

		StringBuilder sb = new StringBuilder();

		for (int i = str.length() - 1; i >= 0; i--) {
			sb.append(str.charAt(i));
		}

		return sb.toString();
	}

}
