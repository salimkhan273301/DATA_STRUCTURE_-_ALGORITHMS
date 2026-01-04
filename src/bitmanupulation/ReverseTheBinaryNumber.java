package bitmanupulation;

import java.util.Arrays;

public class ReverseTheBinaryNumber {
	
	 public static int reverseBitsPure(int n) {
	        int result = 0;
	        for (int i = 0; i < 32; i++) {
	            result <<= 1;          // shift result left to make space
	            result |= (n & 1);     // take the last bit of n and add to result
	            n >>>= 1;              // unsigned right shift of n
	        }
	        return result;
	    }
	
	  public static int reverseBits(int n) {
		  int[] bits = new int[32];
		  char[] reverse= new char[32];

	        // Step 1: convert decimal to binary (store bits)
	        for (int j = 0; j < 32; j++) {
	            bits[j] = n%2;  // last bit
	            n/=2;         // unsigned right shift
	        }
	        
	        System.out.println(Arrays.toString(bits));

	        // Step 2: reverse the binary (build string from end to start)
	        StringBuilder sb = new StringBuilder();
	        for (int i = bits.length - 1; i >= 0; i--) {
	            sb.append(bits[i]);
	        }
	        reverse=sb.toString().toCharArray();
	        System.out.println(Arrays.toString(reverse));

	        // Step 3: convert reversed binary string back to decimal
	        int result = (int) Long.parseLong(sb.reverse().toString(), 2);

	        return result;
	       
	    }

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n=43261596;//Output: 964176192
		
	int res=	reverseBits(n);
	
	System.out.println(res);
	
	
	

	}

}
