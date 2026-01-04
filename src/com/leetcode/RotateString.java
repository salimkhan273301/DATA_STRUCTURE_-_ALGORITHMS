package com.leetcode;

import java.util.Arrays;

public class RotateString {
	
public static boolean rotateString(String s, String goal) {
	if(s.length()!=goal.length())
		return false;
	
	
	return (s+s).contains(goal);
        
        
    }


    public static  boolean rotateString1(String s, String goal) {
        char[] ch = s.toCharArray();
        int n = ch.length;
        for(int i=0; i<ch.length; i++){
            char first = ch[0];
            for(int j=0;j<n-1;j++){
                ch[j]=ch[j+1];
            }
            ch[n-1]=first;
            String str = new String(ch);
            if(str.equals(goal)){
                return true;
            }
        }
        return false;
    }
    
    public static boolean rotateString2(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }
        
        char[] ch = s.toCharArray();
        int n = ch.length;
        
        for (int i = 0; i < n; i++) {
            // Perform rotation
            char first = ch[0];
            for (int j = 0; j < n - 1; j++) {
                ch[j] = ch[j + 1];
            }
            ch[n - 1] = first;
            
            // Convert rotated array to string using StringBuilder
            StringBuilder str = new StringBuilder();
            for (char c : ch) {
                str.append(c);
            }
            
            // Compare with goal
            if (str.toString().equals(goal)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean rotateString4(String s, String goal) {
        char[] sarr=s.toCharArray();
        int n=sarr.length;
         for(int i=0;i<n;i++){
             char ch=sarr[0];
             for(int j=0;j<sarr.length-1;j++){
                 sarr[j]=sarr[j+1];
             }
             sarr[n-1]=ch;
             if(Arrays.equals(sarr,goal.toCharArray())){
                 return true;
             }

         }
         return false;
         
     }
    
    public static boolean rotateString5(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }
        if (s.isEmpty()) {
            return true;  // both strings are empty
        }

        char[] sarr = s.toCharArray();
        char[] goalArr = goal.toCharArray();
        int n = sarr.length;

        for (int i = 0; i < n; i++) {
            if (Arrays.equals(sarr, goalArr)) {
                return true;
            }
            // Rotate left by one
            char first = sarr[0];
            System.arraycopy(sarr, 1, sarr, 0, n - 1);
            sarr[n - 1] = first;
            
        
			/*
			 * System.arraycopy( srcArray, // source array srcPos,
			 *  // starting position insource destArray,
			 *  // destination array destPos,
			 *  // starting position destination length
			 *  // number of elements to copy );
			 */
        }
        return false;
    }


	public static void main(String[] args) {
		
		String s = "abcde";
		String goal = "cdeab";
		
		boolean result=rotateString1(s,goal);
		System.out.println(result);
				

	}

}
