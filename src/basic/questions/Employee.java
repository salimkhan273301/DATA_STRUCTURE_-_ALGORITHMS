package basic.questions;

import java.util.HashMap;
import java.util.Objects;

public class Employee {
    private int id;
    private String name;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,name); // Only id
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Employee other = (Employee) obj;
        return id == other.id && Objects.equals(name, other.name); // id and name
    }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "'}";
    }

    public static void main(String[] args) {
        Employee emp1 = new Employee(1, "Alice");
        Employee emp2 = new Employee(2, "Bob");
        Employee emp3 = new Employee(1, "Charlie"); // Same id, different name as emp1
        Employee emp4 = new Employee(2, "Bob"); // Same id and name as emp2

        HashMap<Employee, String> map = new HashMap<>();
        map.put(emp1, "Developer");
        map.put(emp2, "Designer");
        map.put(emp3, "Manager"); // Should be treated differently due to name but has same hashCode as emp1
        map.put(emp4, "Tester");  // Should replace emp2 since id and name are the same

        System.out.println("HashMap size: " + map.size());
        map.forEach((k, v) -> System.out.println(k + " -> " + v));
        
       // System.out.println(map.get(emp1));
        
        System.out.println(emp1.equals(emp3));
        
        System.out.println(emp1.hashCode());
        System.out.println(emp3.hashCode());
    }
}
