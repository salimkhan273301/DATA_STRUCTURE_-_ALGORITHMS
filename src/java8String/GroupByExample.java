package java8String;

import java.util.*;
import java.util.stream.Collectors;
class Person {
    private String name;
    private String city;
    private String state;

    public Person(String name, String city, String state) {
        this.name = name;
        this.city = city;
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return name;
    }
}

public class GroupByExample {
    public static void main(String[] args) {
        // Create a list of Person objects
        List<Person> people = Arrays.asList(
            new Person("Alice", "New York", "NY"),
            new Person("Bob", "Los Angeles", "CA"),
            new Person("Charlie", "San Francisco", "CA"),
            new Person("David", "New York", "NY"),
            new Person("Eve", "Buffalo", "NY"),
            new Person("Frank", "San Francisco", "CA")
        );

        // Group by City
        Map<String, List<Person>> peopleByCity = people.stream()
            .collect(Collectors.groupingBy(Person::getCity));

        // Print the grouping by city
        System.out.println("People grouped by city:");
        peopleByCity.forEach((city, persons) -> System.out.println(city + ": " + persons));

        System.out.println();

        // Group by State, then by City
        Map<String, Map<String, List<Person>>> peopleByStateAndCity = people.stream()
            .collect(Collectors.groupingBy(Person::getState,
                     Collectors.groupingBy(Person::getCity)));

        // Print the hierarchical grouping
        System.out.println("People grouped by state and city:");
        peopleByStateAndCity.forEach((state, cities) -> {
            System.out.println(state + ":");
            cities.forEach((city, persons) -> System.out.println("  " + city + ": " + persons));
        });
    }
}
