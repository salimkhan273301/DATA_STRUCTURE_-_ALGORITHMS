package com.leetcode;

import java.util.Arrays;

public class DutchFlag {

	private static int[] sortColor(int[] arr) {
		int count0 = 0, count1 = 0, count2 = 0;
		for (int e : arr) {
			if (e == 0)
				count0++;
			else if (e == 1)
				count1++;
			else
				count2++;

		}

		int index = 0;
		while (count0-- > 0)
			arr[index++] = 0;
		while (count1-- > 0)
			arr[index++] = 1;
		while (count2-- > 0)
			arr[index++] = 2;
		return arr;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] arr = { 2, 0, 2, 1, 1, 0 };
		int[] result = sortColor(arr);
		System.out.println(Arrays.toString(result));

	}

}
