package java8String;

import java.util.Arrays;
import java.util.List;

public class Snippet {
	public static void main(String[] args) {
		List<String> words = Arrays.asList("apple", "banana", "apple", "cherry", "banana", "apple");
		words.forEach(x->System.out.print(x+" "));
	}
}

