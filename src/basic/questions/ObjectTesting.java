package basic.questions;

public class ObjectTesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		String s1="salim";
		String s2="salim";
		
		String s3=new String("salim");
		String s4=new String("salim");
		
		System.out.println(s3.hashCode());
		System.out.println(s4.hashCode());
		
		System.out.println(s1.equals(s2));
		System.out.println(s1==s2);
		
		

	}

}
