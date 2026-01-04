package java8String;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ListToMapDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<String> list=Arrays.asList("salim"
				, "nadeem"
				, "hasim"
				, "kasim"
				, "nasir "
				, "arif"
				, "suleman"
				, "asraf"
				, "kalim"
				, "salimullah");
		
		Map<String,Integer> map=list.stream().map(String::toUpperCase).collect(Collectors.toMap(x->x, String::length));
		
		
	}
}
