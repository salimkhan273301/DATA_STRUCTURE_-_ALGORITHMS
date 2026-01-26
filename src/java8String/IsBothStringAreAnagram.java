package java8String;

import java.util.stream.Collectors;

public class IsBothStringAreAnagram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	String s1="listen";
	String s2="silent";
	
	boolean isAnag=isAnagram(s1,s2);
	System.out.println("Is Anagram::"+isAnag);

	}

	private static boolean isAnagram(String s1, String s2) {
		// TODO Auto-generated method stub
		String sorted=s1.chars().sorted().peek(e->System.out.println(e)).mapToObj(e->(char)e).peek(e->System.out.println(e))
				.map(String::valueOf)
				.peek(e->System.out.println(e))
				.collect(Collectors.joining());
	
		String sorted2=s2.chars().sorted().peek(e->System.out.println(e)).mapToObj(e->(char)e).peek(e->System.out.println(e))
				.map(String::valueOf)
				.peek(e->System.out.println(e))
				.collect(Collectors.joining());
		
		return sorted.equals(sorted2);
	}

}
