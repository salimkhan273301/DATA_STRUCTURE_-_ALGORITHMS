package com.leetcode;

import java.util.Arrays;

public class RightRotationOfKTime {
	
	private static int[] rightRotate(int[] arr, int k) {
		// TODO Auto-generated method
		int n=arr.length;
		k%=n;
		int[] temp=new int[n];
		
		for(int i=0; i<n; i++) {
			temp[(i+k)%n]=arr[i];
		}
		
 
		
		return temp;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub3
		
		int[] arr= {1,2,3,4,5,6};
		int k=7;
		int result[]= rightRotate(arr,k);
		
		System.out.println(Arrays.toString(result));

	}

	

}
