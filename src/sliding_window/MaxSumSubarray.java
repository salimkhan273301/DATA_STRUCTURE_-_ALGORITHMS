package sliding_window;

public class MaxSumSubarray  {
	public static void main(String[] args) {
		 int[] arr = {2, 1, 5, 1, 3, 2};
	        int k = 3;
	        System.out.println("Maximum sum: " + maxSumSubarray(arr, k)); // Output: 9
	}

	private static int maxSumSubarray(int[] arr, int k) {
		// TODO Auto-generated method stub
		int maxSum=Integer.MAX_VALUE;
		int sum=0;
		
		
		for(int i=0 ; i<k; i++) {
			sum+=arr[i];
		}
		
		maxSum=sum;
		
		for(int j=k; j<arr.length; j++) {
			
			sum+=arr[j]-arr[j-k];
			maxSum=Math.max(maxSum, sum);
			
		}
		
		
		
		
		return maxSum;
	}
}

