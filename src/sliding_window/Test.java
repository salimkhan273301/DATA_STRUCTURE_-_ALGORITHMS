package sliding_window;

import java.util.*;

public class Test {
	public static class Result {
		long count;
		List<List<Integer>> subarrays;

		public Result(long count, List<List<Integer>> subarrays) {
			this.count = count;
			this.subarrays = subarrays;
		}
	}

	public long continuousSubarrays(int[] nums) {

        TreeMap<Integer,Integer> map=new TreeMap<>();

int count=0; int left=0;
for(int right=0; right<nums.length; right++){
    map.put(nums[right],map.getOrDefault(nums[right],0)+1);

    while(map.lastKey()-map.firstKey()>2) {
    	map.put(nums[left], map.get(nums[left])-1);
    	if(map.get(nums[left])==0)
    		map.remove(nums[left]);
    	
    	 left++;
    	
    }
    count+=right-left+1;
   
}
 
return count;
        
    }

	public Result treeMapApproachWithSubarrays(int[] nums) {
		TreeMap<Integer, Integer> map = new TreeMap<>();
		long count = 0;
		int left = 0;
		List<List<Integer>> allSubarrays = new ArrayList<>();

		for (int right = 0; right < nums.length; right++) {
			map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);

			while (map.lastKey() - map.firstKey() > 2) {
				map.put(nums[left], map.get(nums[left]) - 1);
				if (map.get(nums[left]) == 0) {
					map.remove(nums[left]);
				}
				left++;
			}

			// Store all valid subarrays from left to right
			for (int i = right; i >= left; i--) {
				List<Integer> sub = new ArrayList<>();
				for (int j = i; j <= right; j++) {
					sub.add(nums[j]);
				}
				allSubarrays.add(sub);
			}

			count += right - left + 1;
		}

		return new Result(count, allSubarrays);
	}

	public static void main(String[] args) {
		Test sol = new Test();
		int[] nums = { 5, 4, 2, 4 };

		Result res = sol.treeMapApproachWithSubarrays(nums);

		System.out.println("Total valid subarrays count: " + res.count);
		System.out.println("Valid subarrays:");
		for (List<Integer> sub : res.subarrays) {
			System.out.println(sub);
		}
	}
}
