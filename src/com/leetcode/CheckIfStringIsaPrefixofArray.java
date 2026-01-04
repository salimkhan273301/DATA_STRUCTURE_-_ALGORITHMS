package com.leetcode;

public class CheckIfStringIsaPrefixofArray {
	 public static boolean isPrefixString(String s, String[] words) {
	        
		  StringBuilder sb=new StringBuilder();
	        for(int i=0; i<words.length; i++){
	        	
	            if((sb.append(words[i]).toString()).equals(s))
	                return true;
	        }
	        return false;
	    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "iloveleetcode";
		String arr[]= {"i","love","leetcode","apples"};
		boolean flag=isPrefixString(s, arr);
		System.out.println(flag);

	}

}
