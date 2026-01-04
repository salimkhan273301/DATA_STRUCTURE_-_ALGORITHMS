package com.collectors;

import java.util.Collections;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class CollectorsBestPrectice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Person> people = List.of(
			    new Person("Alice", 28, "Engineering", 85000),
			    new Person("Bob", 35, "Marketing", 75000),
			    new Person("Charlie", 42, "Engineering", 95000),
			    new Person("Diana", 25, "HR", 65000),
			    new Person("Ethan", 31, "Marketing", 80000),
			    new Person("Fiona", 29, "Engineering", 90000),
			    new Person("Alice", 30, "HR", 70000) // Duplicate name
			);
		
		// 1. Collect names to List
		List<String> names = people.stream()
		    .map(Person::getName)
		    .collect(Collectors.toList());
		// [Alice, Bob, Charlie, Diana, Ethan, Fiona, Alice]

		// 2. Collect to Set (removes duplicates)
		Set<String> uniqueNames = people.stream()
		    .map(Person::getName)
		    .collect(Collectors.toSet());
		// [Alice, Bob, Charlie, Diana, Ethan, Fiona]

		// 3. Collect to specific collection type
		TreeSet<String> sortedNames = people.stream()
		    .map(Person::getName)
		    .collect(Collectors.toCollection(TreeSet::new));
		// [Alice, Bob, Charlie, Diana, Ethan, Fiona]
		
		
		
		
		// 4. Create map of name to age (handling duplicates)
		Map<String, Integer> nameToAge = people.stream()
		    .collect(Collectors.toMap(
		        Person::getName,
		        Person::getAge,
		        (oldAge, newAge) -> newAge // keep the newer age
		    ));
		// {Alice=30, Bob=35, Charlie=42, Diana=25, Ethan=31, Fiona=29}

		// 5. Group people by department
		Map<String, List<Person>> byDepartment = people.stream()
		    .collect(Collectors.groupingBy(Person::getDepartment));
		/*
		{
		  Engineering=[Alice (28), Charlie (42), Fiona (29)],
		  Marketing=[Bob (35), Ethan (31)], 
		  HR=[Diana (25), Alice (30)]
		}
		*/

		// 6. Partition into adults and non-adults
		Map<Boolean, List<Person>> adults = people.stream()
		    .collect(Collectors.partitioningBy(p -> p.getAge() >= 18));
		// {true=[all people], false=[]} since all are adults
		
		
		
		
		
		
		// 7. Count people in each department
		Map<String, Long> deptCounts = people.stream()
		    .collect(Collectors.groupingBy(
		        Person::getDepartment,
		        Collectors.counting()
		    ));
		// {Engineering=3, Marketing=2, HR=2}

		// 8. Sum salaries by department
		Map<String, Double> deptSalaries = people.stream()
		    .collect(Collectors.groupingBy(
		        Person::getDepartment,
		        Collectors.summingDouble(Person::getSalary)
		    ));
		// {Engineering=270000.0, Marketing=155000.0, HR=135000.0}

		// 9. Average age by department
		Map<String, Double> avgAgeByDept = people.stream()
		    .collect(Collectors.groupingBy(
		        Person::getDepartment,
		        Collectors.averagingInt(Person::getAge)
		    ));
		// {Engineering=33.0, Marketing=33.0, HR=27.5}

		// 10. Get comprehensive statistics
		IntSummaryStatistics ageStats = people.stream()
		    .collect(Collectors.summarizingInt(Person::getAge));
		/*
		IntSummaryStatistics{
		  count=7, 
		  sum=220, 
		  min=25, 
		  average=31.428571, 
		  max=42
		}
		*/
		
		
		
		
		// 13. Collecting and then making unmodifiable
		List<String> immutableNames = people.stream()
		    .map(Person::getName)
		    .collect(Collectors.collectingAndThen(
		        Collectors.toList(),
		        Collections::unmodifiableList
		    ));
		// Can't modify the returned list

		// 14. Teeing to get multiple results
		record AgeStats(int min, int max, double avg) {}

		

		// 15. Filtering and mapping together
		Map<String, List<String>> deptToYoungEmployees = people.stream()
		    .collect(Collectors.groupingBy(
		        Person::getDepartment,
		        Collectors.filtering(
		            p -> p.getAge() < 30,
		            Collectors.mapping(Person::getName, Collectors.toList())
		        )
		    ));
		// {Engineering=[Alice], Marketing=[], HR=[Diana, Alice]}
		
		
		
		// 11. Join all names with delimiter
		String allNames = people.stream()
		    .map(Person::getName)
		    .collect(Collectors.joining(", "));
		// Alice, Bob, Charlie, Diana, Ethan, Fiona, Alice

		// 12. Join with prefix/suffix
		String formattedNames = people.stream()
		    .map(Person::getName)
		    .distinct()
		    .collect(Collectors.joining(", ", "Employees: [", "]"));
		// Employees: [Alice, Bob, Charlie, Diana, Ethan, Fiona]

		
		// 16. Concurrent map collection
		ConcurrentMap<String, List<Person>> concurrentDeptMap = people.parallelStream()
		    .collect(Collectors.groupingByConcurrent(Person::getDepartment));
		// Thread-safe version of department grouping
	}

}
