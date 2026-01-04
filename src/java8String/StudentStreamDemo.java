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
        /*
        OUTPUT:
        Studnet [id=1, name=Alice, age=22, percentage=85.5]
        Studnet [id=2, name=Bob, age=21, percentage=78.3]
        Studnet [id=3, name=Charlie, age=23, percentage=92.1]
        Studnet [id=4, name=Diana, age=20, percentage=88.7]
        Studnet [id=5, name=Ethan, age=22, percentage=76.5]
        Studnet [id=6, name=Fiona, age=21, percentage=95.2]
        Studnet [id=7, name=George, age=20, percentage=65.8]
        */
        
        // ============================================
        // 1. FILTERING OPERATIONS
        // ============================================
        System.out.println("1. FILTERING OPERATIONS:");
        
        // a. Filter students with percentage > 80
        System.out.println("\na) Students with percentage > 80:");
        students.stream()
                .filter(s -> s.getPercentage() > 80)
                .forEach(s -> System.out.println(s.getName() + ": " + s.getPercentage()));
        /*
        OUTPUT:
        Alice: 85.5
        Charlie: 92.1
        Diana: 88.7
        Fiona: 95.2
        */
        
        // b. Filter students aged 21
        System.out.println("\nb) Students aged 21:");
        students.stream()
                .filter(s -> s.getAge() == 21)
                .forEach(s -> System.out.println(s.getName() + ": " + s.getAge()));
        /*
        OUTPUT:
        Bob: 21
        Fiona: 21
        */
        
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
        /*
        OUTPUT:
        [Alice, Bob, Charlie, Diana, Ethan, Fiona, George]
        */
        
        // b. Get percentages
        System.out.println("\nb) All percentages:");
        List<Double> percentages = students.stream()
                                           .map(Student::getPercentage)
                                           .collect(Collectors.toList());
        System.out.println(percentages);
        /*
        OUTPUT:
        [85.5, 78.3, 92.1, 88.7, 76.5, 95.2, 65.8]
        */
        
        // ============================================
        // 3. SORTING OPERATIONS
        // ============================================
        System.out.println("\n\n3. SORTING OPERATIONS:");
        
        // a. Sort by name (natural order)
        System.out.println("\na) Sorted by name:");
        students.stream()
                .sorted(Comparator.comparing(Student::getName))
                .forEach(s -> System.out.println(s.getName()));
        /*
        OUTPUT:
        Alice
        Bob
        Charlie
        Diana
        Ethan
        Fiona
        George
        */
        
        // b. Sort by percentage (descending)
        System.out.println("\nb) Sorted by percentage (descending):");
        students.stream()
                .sorted(Comparator.comparingDouble(Student::getPercentage).reversed())
                .forEach(s -> System.out.println(s.getName() + ": " + s.getPercentage()));
        /*
        OUTPUT:
        Fiona: 95.2
        Charlie: 92.1
        Diana: 88.7
        Alice: 85.5
        Bob: 78.3
        Ethan: 76.5
        George: 65.8
        */
        
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
        /*
        OUTPUT:
        Students above 80%: 4
        */
        
        // b. Sum of all percentages
        System.out.println("\nb) Sum of percentages:");
        double totalPercentage = students.stream()
                                         .mapToDouble(Student::getPercentage)
                                         .sum();
        System.out.println("Total percentage sum: " + totalPercentage);
        /*
        OUTPUT:
        Total percentage sum: 582.1
        */
        
        // c. Average percentage
        System.out.println("\nc) Average percentage:");
        OptionalDouble avgPercentage = students.stream()
                                               .mapToDouble(Student::getPercentage)
                                               .average();
        avgPercentage.ifPresent(avg -> System.out.println("Average: " + avg));
        /*
        OUTPUT:
        Average: 83.15714285714286
        */
        
        // d. Maximum percentage
        System.out.println("\nd) Maximum percentage:");
        Optional<Student> topper = students.stream()
                                           .max(Comparator.comparingDouble(Student::getPercentage));
        topper.ifPresent(s -> System.out.println("Topper: " + s.getName() + " - " + s.getPercentage()));
        /*
        OUTPUT:
        Topper: Fiona - 95.2
        */
        
        // e. Minimum percentage
        System.out.println("\ne) Minimum percentage:");
        Optional<Student> lowest = students.stream()
                                           .min(Comparator.comparingDouble(Student::getPercentage));
        lowest.ifPresent(s -> System.out.println("Lowest: " + s.getName() + " - " + s.getPercentage()));
        /*
        OUTPUT:
        Lowest: George - 65.8
        */
        
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
        /*
        OUTPUT:
        Studnet [id=1, name=Alice, age=22, percentage=85.5]
        Studnet [id=3, name=Charlie, age=23, percentage=92.1]
        Studnet [id=4, name=Diana, age=20, percentage=88.7]
        Studnet [id=6, name=Fiona, age=21, percentage=95.2]
        */
        
        // b. Collect to Map (id -> Student)
        System.out.println("\nb) Student Map (ID -> Name):");
        Map<Integer, String> studentMap = students.stream()
                                                  .collect(Collectors.toMap(
                                                      Student::getId,
                                                      Student::getName
                                                  ));
        System.out.println(studentMap);
        /*
        OUTPUT:
        {1=Alice, 2=Bob, 3=Charlie, 4=Diana, 5=Ethan, 6=Fiona, 7=George}
        */
        
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
        /*
        OUTPUT:
        Age 20: Diana, George
        Age 21: Bob, Fiona
        Age 22: Alice, Ethan
        Age 23: Charlie
        */
        
        // d. Partition by passing criteria (>=75%)
        System.out.println("\nd) Partition by pass/fail (>=75%):");
        Map<Boolean, List<Student>> passedFailed = students.stream()
                                                          .collect(Collectors.partitioningBy(s -> s.getPercentage() >= 75));
        System.out.println("Passed: " + passedFailed.get(true).size() + " students");
        System.out.println("Failed: " + passedFailed.get(false).size() + " students");
        /*
        OUTPUT:
        Passed: 5 students
        Failed: 2 students
        */
        
        // e. Join all names
        System.out.println("\ne) All names joined:");
        String allNames = students.stream()
                                  .map(Student::getName)
                                  .collect(Collectors.joining(" -> "));
        System.out.println("Names: " + allNames);
        /*
        OUTPUT:
        Names: Alice -> Bob -> Charlie -> Diana -> Ethan -> Fiona -> George
        */
        
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
        /*
        OUTPUT:
        Ages: [22, 21, 23, 20]
        */
        
        // b. Limit and Skip
        System.out.println("\nb) First 3 students:");
        students.stream()
                .limit(3)
                .forEach(s -> System.out.println(s.getName()));
        /*
        OUTPUT:
        Alice
        Bob
        Charlie
        */
        
        System.out.println("\nc) Skip first 2 students:");
        students.stream()
                .skip(2)
                .forEach(s -> System.out.println(s.getName()));
        /*
        OUTPUT:
        Charlie
        Diana
        Ethan
        Fiona
        George
        */
        
        // c. AnyMatch, AllMatch, NoneMatch
        System.out.println("\nd) Match operations:");
        boolean anyAbove90 = students.stream()
                                     .anyMatch(s -> s.getPercentage() > 90);
        System.out.println("Any student above 90%: " + anyAbove90);
        /*
        OUTPUT:
        Any student above 90%: true
        */
        
        boolean allAbove60 = students.stream()
                                     .allMatch(s -> s.getPercentage() > 60);
        System.out.println("All students above 60%: " + allAbove60);
        /*
        OUTPUT:
        All students above 60%: true
        */
        
        boolean noneAbove100 = students.stream()
                                       .noneMatch(s -> s.getPercentage() > 100);
        System.out.println("No student above 100%: " + noneAbove100);
        /*
        OUTPUT:
        No student above 100%: true
        */
        
        // ============================================
        // 7. FLATMAP EXAMPLE
        // ============================================
        System.out.println("\n\n7. FLATMAP OPERATION:");
        
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
        /*
        OUTPUT:
        Math
        Physics
        Chemistry
        Biology
        Computer Science
        */
        
        // ============================================
        // 8. FIND OPERATIONS
        // ============================================
        System.out.println("\n\n8. FIND OPERATIONS:");
        
        // Find first student with percentage > 90
        Optional<Student> firstAbove90 = students.stream()
                                                 .filter(s -> s.getPercentage() > 90)
                                                 .findFirst();
        firstAbove90.ifPresent(s -> 
            System.out.println("First student above 90%: " + s.getName()));
        /*
        OUTPUT:
        First student above 90%: Charlie
        */
        
        // Find any student with age 22
        Optional<Student> anyAge22 = students.stream()
                                             .filter(s -> s.getAge() == 22)
                                             .findAny();
        anyAge22.ifPresent(s -> 
            System.out.println("Any student age 22: " + s.getName()));
        /*
        OUTPUT:
        Any student age 22: Alice  (or Ethan - can be any)
        */
        
        // ============================================
        // 9. STATISTICS SUMMARY
        // ============================================
        System.out.println("\n\n9. STATISTICS SUMMARY:");
        
        DoubleSummaryStatistics stats = students.stream()
                .mapToDouble(Student::getPercentage)
                .summaryStatistics();
        
        System.out.println("Statistics Summary:");
        System.out.println("Count: " + stats.getCount());
        System.out.println("Sum: " + stats.getSum());
        System.out.println("Min: " + stats.getMin());
        System.out.println("Max: " + stats.getMax());
        System.out.println("Average: " + stats.getAverage());
        /*
        OUTPUT:
        Statistics Summary:
        Count: 7
        Sum: 582.1
        Min: 65.8
        Max: 95.2
        Average: 83.15714285714286
        */
        
        // ============================================
        // 10. REDUCE OPERATION
        // ============================================
        System.out.println("\n\n10. REDUCE OPERATION:");
        
        // Concatenate all names using reduce
        Optional<String> concatenatedNames = students.stream()
                .map(Student::getName)
                .reduce((name1, name2) -> name1 + ", " + name2);
        
        concatenatedNames.ifPresent(result -> 
            System.out.println("All names: " + result));
        /*
        OUTPUT:
        All names: Alice, Bob, Charlie, Diana, Ethan, Fiona, George
        */
        
        // Sum of percentages using reduce
        double totalUsingReduce = students.stream()
                .map(Student::getPercentage)
                .reduce(0.0, Double::sum);
        System.out.println("Total percentage using reduce: " + totalUsingReduce);
        /*
        OUTPUT:
        Total percentage using reduce: 582.1
        */
        
        System.out.println("\n=== ALL OPERATIONS COMPLETED ===");
        
        
        
     // Q1: Find second highest percentage
        Optional<Student> secondHighest = students.stream()
                .sorted(Comparator.comparingDouble(Student::getPercentage).reversed())
                .skip(1)
                .findFirst();
        // OUTPUT: Charlie: 92.1%

        // Q2: Find duplicate ages
        Map<Integer, Long> ageCount = students.stream()
                .collect(Collectors.groupingBy(Student::getAge, Collectors.counting()));
        List<Integer> duplicateAges = ageCount.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        // OUTPUT: [20, 21, 22]

        // Q3: Find top 3 students by percentage
        List<Student> top3 = students.stream()
                .sorted(Comparator.comparingDouble(Student::getPercentage).reversed())
                .limit(3)
                .collect(Collectors.toList());
        // OUTPUT: Fiona, Charlie, Diana

        // Q4: Convert List to Map with duplicate key handling
        Map<Integer, Student> studentIdMap = students.stream()
                .collect(Collectors.toMap(
                    Student::getId,
                    student -> student,
                    (existing, replacement) -> existing  // handle duplicates
                ));

        // Q5: Check if all students passed (>=60%)
        boolean allPassed = students.stream()
                .allMatch(s -> s.getPercentage() >= 60);
        // OUTPUT: true
    }
}