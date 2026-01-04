package generalproblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
 class Pair<V,U>{
	
	public final V first;
	public final U second;
	
	public Pair(V first,U second) {
		this.first=first;
		
		this.second=second;
	}
	 public String toString() {
	        return first + " " + second;
	    }
	
	
}

/*
 * public class TwoSum { public static ArrayList<Pair<Integer, Integer>>
 * twoSum(ArrayList<Integer> arr, int target, int n) { // Write your code here.
 * ArrayList<Pair<Integer, Integer>> result=new ArrayList<>(); int left=0; int
 * right=n-1; while(left<right) { int sum=arr.get(left)+arr.get(right);
 * if(sum==target){ result.add( new Pair<>(arr.get(left),arr.get(right)));
 * left++; right--;
 * 
 * } else if(sum>target) right--; else left++; } return result; } }
 * 
 * 
 * import java.util.*;
 * 
 * //Define a simple Pair class class Pair<U, V> { public final U first; public
 * final V second;
 * 
 * public Pair(U first, V second) { this.first = first; this.second = second; }
 * 
 * public String toString() { return first + " " + second; } }
 */
public class TwoSum {

 public static ArrayList<Pair<Integer, Integer>> twoSum(ArrayList<Integer> arr, int target, int n) {
     ArrayList<Pair<Integer, Integer>> result = new ArrayList<>();
     Map<Integer, Integer> map = new HashMap<>();

     for (int j = 0; j < arr.size(); j++) {
         int curr = arr.get(j);
         int comp = target - curr;

         if (map.containsKey(comp)) {
             int freq = map.get(comp);
             for (int i = 0; i < freq; i++) {
                 result.add(new Pair<>(comp, curr));
             }
         }

         map.put(curr, map.getOrDefault(curr, 0) + 1);
     }

     return result;
 }

 // Main method for testing
 public static void main(String[] args) {
     ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(1, -1, -1, 2, 2));
     int target = 1;

     ArrayList<Pair<Integer, Integer>> pairs = twoSum(arr, target, arr.size());

     for (Pair<Integer, Integer> pair : pairs) {
         System.out.println(pair);
     }
 }
}

