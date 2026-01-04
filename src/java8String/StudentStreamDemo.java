package java8String;


import java.util.*;
import java.util.stream.*;

public class StudentStreamDemo {
    public static void main(String[] args) {
        
        // Create 5 Student objects
        List<Student> students = Arrays.asList(
            new Student(1, "Alice", 22, 85.5),
            new Student(2, "Bob", 21, 78.3),
            new Student(3, "Charlie", 23, 92.1),
            new Student(4, "Diana", 20, 88.7),
            new Student(5, "Ethan", 22, 76.5),
            new Student(6, "Fiona", 21, 95.2), // Added extra for demo
            new Student(7, "George", 20, 65.8)  // Added extra for demo
        );
        
        System.out.println("=== ALL STUDENTS ===");
        students.forEach(System.out::println);
        System.out.println();
        
        // ============================================
        // 1. FILTERING OPERATIONS
        // ============================================
        System.out.println("1. FILTERING OPERATIONS:");
        
        // a. Filter students with percentage > 80
        System.out.println("\na) Students with percentage > 80:");
        students.stream()
                .filter(s -> s.getPercentage() > 80)
                .forEach(s -> System.out.println(s.getName() + ": " + s.getPercentage()));
        
        // b. Filter students aged 21
        System.out.println("\nb) Students aged 21:");
        students.stream()
                .filter(s -> s.getAge() == 21)
                .forEach(s -> System.out.println(s.getName() + ": " + s.getAge()));
        
        // c. Multiple filters
        System.out.println("\nc) Students aged >20 with percentage >80:");
        students.stream()
                .filter(s -> s.getAge() > 20)
                .filter(s -> s.getPercentage() > 80)
                .forEach(System.out::println);
        
        // ============================================
        // 2. MAPPING OPERATIONS
        // ============================================
        System.out.println("\n\n2. MAPPING OPERATIONS:");
        
        // a. Get only student names
        System.out.println("\na) All student names:");
        List<String> names = students.stream()
                                     .map(Student::getName)
                                     .collect(Collectors.toList());
        System.out.println(names);
        
        // b. Get percentages
        System.out.println("\nb) All percentages:");
        List<Double> percentages = students.stream()
                                           .map(Student::getPercentage)
                                           .collect(Collectors.toList());
        System.out.println(percentages);
        
        // c. Map to custom objects (Name + Percentage)
        System.out.println("\nc) Name with Percentage:");
        students.stream()
                .map(s -> s.getName() + " - " + s.getPercentage() + "%")
                .forEach(System.out::println);
        
        // ============================================
        // 3. SORTING OPERATIONS
        // ============================================
        System.out.println("\n\n3. SORTING OPERATIONS:");
        
        // a. Sort by name (natural order)
        System.out.println("\na) Sorted by name:");
        students.stream()
                .sorted(Comparator.comparing(Student::getName))
                .forEach(s -> System.out.println(s.getName()));
        
        // b. Sort by percentage (descending)
        System.out.println("\nb) Sorted by percentage (descending):");
        students.stream()
                .sorted(Comparator.comparingDouble(Student::getPercentage).reversed())
                .forEach(s -> System.out.println(s.getName() + ": " + s.getPercentage()));
        
        // c. Sort by age then percentage
        System.out.println("\nc) Sorted by age then percentage:");
        students.stream()
                .sorted(Comparator.comparingInt(Student::getAge)
                                 .thenComparingDouble(Student::getPercentage))
                .forEach(s -> System.out.println(s.getAge() + "yrs: " + s.getName() + " - " + s.getPercentage()));
        
        // ============================================
        // 4. REDUCTION OPERATIONS (Terminal)
        // ============================================
        System.out.println("\n\n4. REDUCTION OPERATIONS:");
        
        // a. Count operations
        System.out.println("\na) Count operations:");
        long countAbove80 = students.stream()
                                    .filter(s -> s.getPercentage() > 80)
                                    .count();
        System.out.println("Students above 80%: " + countAbove80);
        
        // b. Sum of all percentages
        System.out.println("\nb) Sum of percentages:");
        double totalPercentage = students.stream()
                                         .mapToDouble(Student::getPercentage)
                                         .sum();
        System.out.println("Total percentage sum: " + totalPercentage);
        
        // c. Average percentage
        System.out.println("\nc) Average percentage:");
        OptionalDouble avgPercentage = students.stream()
                                               .mapToDouble(Student::getPercentage)
                                               .average();
        avgPercentage.ifPresent(avg -> System.out.println("Average: " + avg));
        
        // d. Maximum percentage
        System.out.println("\nd) Maximum percentage:");
        Optional<Student> topper = students.stream()
                                           .max(Comparator.comparingDouble(Student::getPercentage));
        topper.ifPresent(s -> System.out.println("Topper: " + s.getName() + " - " + s.getPercentage()));
        
        // e. Minimum percentage
        System.out.println("\ne) Minimum percentage:");
        Optional<Student> lowest = students.stream()
                                           .min(Comparator.comparingDouble(Student::getPercentage));
        lowest.ifPresent(s -> System.out.println("Lowest: " + s.getName() + " - " + s.getPercentage()));
        
        // ============================================
        // 5. COLLECTORS OPERATIONS
        // ============================================
        System.out.println("\n\n5. COLLECTORS OPERATIONS:");
        
        // a. Collect to List
        System.out.println("\na) Students with A grade (>85%):");
        List<Student> gradeAStudents = students.stream()
                                               .filter(s -> s.getPercentage() >= 85)
                                               .collect(Collectors.toList());
        gradeAStudents.forEach(System.out::println);
        
        // b. Collect to Map (id -> Student)
        System.out.println("\nb) Student Map (ID -> Name):");
        Map<Integer, String> studentMap = students.stream()
                                                  .collect(Collectors.toMap(
                                                      Student::getId,
                                                      Student::getName
                                                  ));
        System.out.println(studentMap);
        
        // c. Group by age
        System.out.println("\nc) Group students by age:");
        Map<Integer, List<Student>> studentsByAge = students.stream()
                                                            .collect(Collectors.groupingBy(Student::getAge));
        studentsByAge.forEach((age, studentList) -> {
            System.out.println("Age " + age + ": " + 
                studentList.stream()
                          .map(Student::getName)
                          .collect(Collectors.joining(", ")));
        });
        
        // d. Partition by passing criteria (>=75%)
        System.out.println("\nd) Partition by pass/fail (>=75%):");
        Map<Boolean, List<Student>> passedFailed = students.stream()
                                                          .collect(Collectors.partitioningBy(s -> s.getPercentage() >= 75));
        System.out.println("Passed: " + passedFailed.get(true).size() + " students");
        System.out.println("Failed: " + passedFailed.get(false).size() + " students");
        
        // e. Join all names
        System.out.println("\ne) All names joined:");
        String allNames = students.stream()
                                  .map(Student::getName)
                                  .collect(Collectors.joining(" -> "));
        System.out.println("Names: " + allNames);
        
        // ============================================
        // 6. SPECIAL OPERATIONS
        // ============================================
        System.out.println("\n\n6. SPECIAL OPERATIONS:");
        
        // a. Distinct ages
        System.out.println("\na) Distinct ages:");
        List<Integer> distinctAges = students.stream()
                                             .map(Student::getAge)
                                             .distinct()
                                             .collect(Collectors.toList());
        System.out.println("Ages: " + distinctAges);
        
        // b. Limit and Skip
        System.out.println("\nb) First 3 students:");
        students.stream()
                .limit(3)
                .forEach(s -> System.out.println(s.getName()));
        
        System.out.println("\nc) Skip first 2 students:");
        students.stream()
                .skip(2)
                .forEach(s -> System.out.println(s.getName()));
        
        // c. AnyMatch, AllMatch, NoneMatch
        System.out.println("\nd) Match operations:");
        boolean anyAbove90 = students.stream()
                                     .anyMatch(s -> s.getPercentage() > 90);
        System.out.println("Any student above 90%: " + anyAbove90);
        
        boolean allAbove60 = students.stream()
                                     .allMatch(s -> s.getPercentage() > 60);
        System.out.println("All students above 60%: " + allAbove60);
        
        boolean noneAbove100 = students.stream()
                                       .noneMatch(s -> s.getPercentage() > 100);
        System.out.println("No student above 100%: " + noneAbove100);
        
        // ============================================
        // 7. PARALLEL STREAMS
        // ============================================
        System.out.println("\n\n7. PARALLEL STREAMS:");
        
        System.out.println("Processing with parallel stream:");
        students.parallelStream()
                .filter(s -> s.getPercentage() > 80)
                .forEach(s -> System.out.println(
                    Thread.currentThread().getName() + " - " + s.getName()));
        
        // ============================================
        // 8. FLATMAP EXAMPLE (with multiple courses)
        // ============================================
        System.out.println("\n\n8. FLATMAP OPERATION:");
        
        // Create students with multiple courses
        List<List<String>> studentCourses = Arrays.asList(
            Arrays.asList("Math", "Physics"),
            Arrays.asList("Chemistry", "Biology"),
            Arrays.asList("Math", "Computer Science"),
            Arrays.asList("Physics", "Chemistry")
        );
        
        System.out.println("All unique courses:");
        studentCourses.stream()
                      .flatMap(List::stream)
                      .distinct()
                      .forEach(System.out::println);
        
        // ============================================
        // 9. FIND OPERATIONS
        // ============================================
        System.out.println("\n\n9. FIND OPERATIONS:");
        
        // Find first student with percentage > 90
        Optional<Student> firstAbove90 = students.stream()
                                                 .filter(s -> s.getPercentage() > 90)
                                                 .findFirst();
        firstAbove90.ifPresent(s -> 
            System.out.println("First student above 90%: " + s.getName()));
        
        // Find any student with age 22
        Optional<Student> anyAge22 = students.stream()
                                             .filter(s -> s.getAge() == 22)
                                             .findAny();
        anyAge22.ifPresent(s -> 
            System.out.println("Any student age 22: " + s.getName()));
        
        // ============================================
        // 10. PEEK OPERATION (for debugging)
        // ============================================
        System.out.println("\n\n10. PEEK (Debugging):");
        
        List<String> topStudents = students.stream()
                                           .peek(s -> System.out.println("Processing: " + s.getName()))
                                           .filter(s -> s.getPercentage() > 85)
                                           .peek(s -> System.out.println("Passed filter: " + s.getName()))
                                           .map(Student::getName)
                                           .collect(Collectors.toList());
        
        System.out.println("Top students: " + topStudents);
    }
}