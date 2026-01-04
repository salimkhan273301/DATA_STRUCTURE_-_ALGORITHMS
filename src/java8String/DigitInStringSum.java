package java8String;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DigitInStringSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stu
		
		List<String> list=Arrays.asList("AKHTER47","Salim66","NASIR27","SAJID77","HASIM99","KHAN66");
		
		int sum=list.stream().flatMapToInt(x->x.chars()).filter(Character::isDigit).map(Character::getNumericValue).sum();
		
		System.out.println("SUM::"+sum);
		
		List<String> names=list.stream().map(str->str.replaceAll("\\d", "")).filter(str->!str.isEmpty()).map(str->str.toUpperCase()).collect(Collectors.toList());
		System.out.println(names);
		
		List<Integer> digit=list.stream().map(str->str.replaceAll("\\D","")).filter(str->!str.isEmpty()).map(Integer::valueOf).collect(Collectors.toList());
		
		System.out.println(digit);

		int sumofDidit=list.stream().map(str->str.replaceAll("\\D","")).filter(str->!str.isEmpty()).mapToInt(Integer::valueOf).sum();
		
		
		System.out.println("SUMOfElement::"+sumofDidit);
		
		

				
		

	}

}
