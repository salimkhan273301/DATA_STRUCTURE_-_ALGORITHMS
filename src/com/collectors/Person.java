package com.collectors;

import java.util.List;
import java.util.Objects;

public class Person {
    private String name;
    private int age;
    private String department;
    private double salary;

    public Person(String name, int age, String department, double salary) {
        this.name = name;
        this.age = age;
        this.department = department;
        this.salary = salary;
    }

    // Getters
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }

    // toString for printing
    @Override
    public String toString() {
        return name + " (" + age + ") - " + department;
    }

    // equals and hashCode for proper Set operations
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && 
               Objects.equals(name, person.name) && 
               Objects.equals(department, person.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, department);
    }
}