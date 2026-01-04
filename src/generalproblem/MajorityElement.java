package generalproblem;

import java.util.*;

public class MajorityElement {
    public static int findMajorityElement(int[] arr, int n) {
        // Phase 1: Find candidate using Boyer-Moore Voting Algorithm
        int count = 0;
        int candidate = -1;

        for (int num : arr) {
            if (count == 0) {
                candidate = num;
                count = 1;
            } else if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }

        // Phase 2: Verify if candidate is actually a majority
        int freq = 0;
        for (int num : arr) {
            if (num == candidate) {
                freq++;
            }
        }

        if (freq > n / 2) {
            return candidate;
        } else {
            return -1;
        }
    }

    // Main for testing multiple inputs
    public static void main(String[] args) {
		/*
		 * Scanner sc = new Scanner(System.in); int T = sc.nextInt(); // Number of test
		 * cases
		 * 
		 * while (T-- > 0) { int n = sc.nextInt(); int[] arr = new int[n];
		 * 
		 * for (int i = 0; i < n; i++) { arr[i] = sc.nextInt(); } // int arr1[]= {2 ,3
		 * ,9 ,2, 2}; // int n1=arr1.length;
		 * 
		 * // System.out.println(findMajorityElement(arr1, n1)); }
		 */
        int arr1[]= {2 ,3 ,9 ,2, 2};
        int n1=arr1.length;

        System.out.println(findMajorityElement(arr1, n1));
    }
}
