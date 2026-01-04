package generalproblem;

public class Demo {
	
	private static String reversePrefix(char ch, String s) {
		
		String sub2="";
		String sub1="";
		if(!s.isEmpty()) {
			int end=s.indexOf(ch);
			int start=0;
			
			String sub=s.substring(start,end+1);
			StringBuilder sb=new StringBuilder(sub);
			
			sb.reverse();
			sub1=sb.toString();
			sub2=s.substring(end+1,s.length());
			
		}
		
		return sub1+sub2;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String word="abcdefd";
		 char ch='d';
		String result=reversePrefix(ch, word);
		System.out.println(result);

	}

	

}
