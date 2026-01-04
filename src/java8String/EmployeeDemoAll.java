package java8String;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Collectors;

class Employee {
    private int id;
    private String name;
    private int age;
    private String city;
    private String state;
    private double salary;

    // Constructor
    public Employee(int id, String name, int age, String city, String state, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.city = city;
        this.state = state;
        this.salary = salary;
    }

    // Getters and toString method
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + '\'' +
               ", age=" + age + ", city='" + city + '\'' +
               ", state='" + state + '\'' + ", salary=" + salary + '}';
    }
}

public class EmployeeDemoAll {
    public static void main(String[] args) {
        // Create a list of employees
        List<Employee> employees = new ArrayList<>();

        // Add 10 Employee objects to the list
        employees.add(new Employee(1, "Alice", 30, "New York", "NY", 95000));
        employees.add(new Employee(2, "Bob", 25, "Los Angeles", "CA", 65000));
        employees.add(new Employee(3, "Charlie", 35, "Chicago", "IL", 80000));
        employees.add(new Employee(4, "Diana", 28, "Houston", "TX", 72000));
        employees.add(new Employee(5, "Eve", 40, "Phoenix", "AZ", 85000));
        employees.add(new Employee(6, "Frank", 45, "Philadelphia", "PA", 90000));
        employees.add(new Employee(7, "Grace", 70, "San Antonio", "TX", 95000));
        employees.add(new Employee(8, "Hank", 38, "San Diego", "CA", 78000));
        employees.add(new Employee(9, "Ivy", 32, "Dallas", "TX", 67000));
        employees.add(new Employee(10, "Jack", 29, "San Jose", "CA", 62000));

        // Display the list of employees
        for (Employee employee : employees) {
            System.out.println(employee);
        }
        
        System.out.println("================================================================");
        
        employees.stream().sorted(Comparator.comparing(Employee::getAge)).forEach(System.out::println);
        
        System.out.println("================================================================");
        
        employees.stream().sorted(Comparator.comparing(Employee::getAge).reversed()).forEach(System.out::println);
        
        System.out.println("================================================================");
        
        Map<String,List<Employee>> byCity=employees.stream().collect(Collectors.groupingBy(Employee::getState));
        
        System.out.println(byCity);
        
        System.out.println("====================total salary sum ===========================================");
        
        double sum=employees.stream().mapToDouble(Employee::getSalary).sum();
       
        System.out.println("sum:: "+sum);
        
        System.out.println("====================grouping by  salary  ===========================================");
        
        
        Map<Double,Long> groupingbysalary=employees.stream().collect(Collectors.groupingBy(Employee::getSalary,Collectors.counting()));
        
        System.out.println(groupingbysalary);
        
        
 System.out.println("====================grouping by  city  ===========================================");
        
        
        Map<String,Long> groupingbyCity=employees.stream().collect(Collectors.groupingBy(Employee::getCity,Collectors.counting()));
        
        System.out.println(groupingbyCity);
        
        
System.out.println("====================grouping by  state  ===========================================");
        
        
        Map<String,Long> groupingbyState=employees.stream().collect(Collectors.groupingBy(Employee::getState,Collectors.counting()));
        
        System.out.println(groupingbyState);
        
        System.out.println("=========================find max age============================================");
        
        Optional<Employee> maxage=employees.stream().max(Comparator.comparingInt(Employee::getAge));
        
        System.out.println(maxage);
        
        
        System.out.println("================================find second max age---------------------");
        
        Employee secondmax=employees.stream().sorted(Comparator.comparingInt(Employee::getAge).reversed())
        		.skip(1).findFirst().orElse(null);
        
        System.out.println(secondmax);
        
        
        
 System.out.println("================================find second min age---------------------");
        
        Employee secondmin=employees.stream().sorted(Comparator.comparingInt(Employee::getAge))
        		.skip(1).findFirst().orElse(null);
        
        System.out.println(secondmin);
        
        
        System.out.println("================================find names of employees---------------------");
        
        List<String> namesofemp=employees.stream().map(Employee::getName).collect(Collectors.toList());
        System.out.println(namesofemp);
        
        System.out.println("=================================find avrage  of ages===========================");
        
        OptionalDouble ageavg=employees.stream().mapToInt(Employee::getAge).average();
        
        
        System.out.println(ageavg);
        
        
        System.out.println("=================================find DoubleSummaryStatistics salary state wise ===========================");
        
        
        Map<String, DoubleSummaryStatistics> salarysumstatewise=employees.stream().collect(Collectors.groupingBy(Employee::getState,Collectors.summarizingDouble(Employee::getSalary)));
        
        System.out.println(salarysumstatewise);
        
        
        System.out.println("================================= List Employees by Age Group ===========================");
         
        
        Map<String, List<Employee>> employeesByAgeGroup = employees.stream()
        	    .collect(Collectors.groupingBy(e -> {
        	        if (e.getAge() < 30) return "<30";
        	        else if (e.getAge() <= 40) return "30-40";
        	        else return ">40";
        	    }));

        	System.out.println("Employees by Age Group: " + employeesByAgeGroup);
        	
        	
        	 System.out.println("================================= Find if there are any employees with duplicate names. ===========================");  	
        	Set<String> uniqueNames = new HashSet<>();
        	Set<String> duplicateNames = employees.stream()
        	    .map(Employee::getName)
        	    .filter(name -> !uniqueNames.add(name))
        	    .collect(Collectors.toSet());
        	System.out.println("uniqueNames Names: " + uniqueNames);

        	System.out.println("Duplicate Names: " + duplicateNames);
        	
        	
       	 System.out.println("================================= 12. Filter and Collect Based on Custom Condition. ===========================");  	
        	
        	List<Employee> filteredEmployees = employees.stream()
        		    .filter(e -> e.getAge() > 30 && e.getCity().equalsIgnoreCase("New York"))
        		    .collect(Collectors.toList());

        		System.out.println("Filtered Employees: " + filteredEmployees);



    }
}
