package hashing;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MinSubsetswithConsecutiveNumbers {

	
	  public static int numOfSubset(int[] arr) {
	        // Your code goes here
	        int count[]= {0};
	        Map<Integer,Boolean> map=new HashMap<>();
	        
	        for(int n:arr){
	            map.put(n,true);
	        }
	        
	        for(int e: map.keySet()){
	            if(map.containsKey(e-1))map.put(e,false);
	        }
	        
	        map.forEach((k,v)->{
	            if(v.booleanValue()==true)
	            count[0]++;
	        });
	        
	        return count[0];
	    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[]arr= {100, 56, 5, 6, 102, 58, 101, 57, 7, 103};
		System.out.print(numOfSubset(arr));
		// User function Template for Java

		

	}

}
