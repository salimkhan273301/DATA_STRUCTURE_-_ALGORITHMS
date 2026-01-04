package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Subset11 {
	
	 public static List<List<Integer>> subsetsWithDup(int[] nums) {
		 	int n=nums.length;
		 	Arrays.sort(nums);
	        Set<List<Integer>> res=new HashSet<>();
	        
	        for(int start=0; start<(1<<n); start++) {
	        	List<Integer> curr=new ArrayList<Integer>();
	        	for(int end=0; end<n; end++) {
	        		if((start&(1<<end))!=0) {
	        			curr.add(nums[end]);
	        		}
	        	}
	        	
	        	res.add(curr);
	        	
	        }
	        return  new ArrayList<>(res);
	    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr= {1,2,2};
		List<List<Integer>>result=subsetsWithDup(arr);
		System.out.println(result);

	}

}
