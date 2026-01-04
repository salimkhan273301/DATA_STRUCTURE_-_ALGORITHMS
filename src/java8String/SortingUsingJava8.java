package java8String;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SortingUsingJava8 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 List<Student> students = Arrays.asList(
		            new Student(1, "Alice", 22, 85.5),
		            new Student(2, "Bob", 20, 91.0),
		            new Student(3, "Charlie", 23, 88.0)
		        );
		 
		students.stream().sorted(Comparator.comparingInt(Student::getId)).forEach(System.out::println);
		

	}

}
