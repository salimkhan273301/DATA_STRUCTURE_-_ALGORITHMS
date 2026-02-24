package sliding_window;

public class MinSizeSubarraySum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] nums = {2, 3, 1, 2, 4, 3};
        int target = 7;
        System.out.println("Minimum length: " + minSubArrayLen(target, nums)); // Output: 2

	}

	private static int minSubArrayLen(int target, int[] nums) {
		// TODO Auto-generated method stub
		int left=0;
		int minLen=Integer.MAX_VALUE;
		int sum=0;
		for(int right=0; right<nums.length; right++) {
			sum+=nums[right];
			
			while(sum>=target) {
				minLen=Math.min(minLen, right-left+1);
				sum-=nums[left];
				left++;
			}
			
		
			
		}
		return minLen==Integer.MAX_VALUE?0:minLen;
	}

}
