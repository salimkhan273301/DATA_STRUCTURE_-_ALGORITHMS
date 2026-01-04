package java8String;

public class Student {
	
	private int id;
	private String name;
	private int age;
	private double percentage;
	
	public Student(int id, String name, int age, double percentage) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.percentage = percentage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	@Override
	public String toString() {
		return "Studnet [id=" + id + ", name=" + name + ", age=" + age + ", percentage=" + percentage + "]";
	}
	
	
	
	
	

}
