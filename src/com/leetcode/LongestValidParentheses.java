package com.leetcode;

import java.util.Stack;

public class LongestValidParentheses {

	public static int longestValidParentheses(String s) {

		int longestValidP = 0;
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(-1);

		for (int i = 0; i < s.length(); i++) {
			char current = s.charAt(i);
			if (current == '(')
				stack.push(i);
			else {

				stack.pop();
				if (stack.isEmpty()) {
					stack.push(i);
				} else {
					int topOfStack = stack.peek();
					longestValidP = Math.max(longestValidP, i - topOfStack);
				}

			}

		}
		return longestValidP;

	}
	
	public static int longestValidParentheses1(String s) {
        int left = 0, right = 0, max = 0;
        
        // Left to right pass
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') left++;
            else right++;
            
            if (left == right) max = Math.max(max, 2 * right);
            else if (right > left) left = right = 0;
        }
        
        left = right = 0;
        // Right to left pass
        for (int i = s.length()-1; i >= 0; i--) {
            if (s.charAt(i) == '(') left++;
            else right++;
            
            if (left == right) max = Math.max(max, 2 * left);
            else if (left > right) left = right = 0;
        }
        
        return max;
    }

// two pointer
	    public int longestValidParentheses3(String s) {
	        int left = 0, right = 0, maxlength = 0;
	        
	        // Left to right scan
	        for (int i = 0; i < s.length(); i++) {
	            if (s.charAt(i) == '(') {
	                left++;
	            } else {
	                right++;
	            }
	            
	            if (left == right) {
	                maxlength = Math.max(maxlength, 2 * right);
	            } else if (right >= left) {
	                left = right = 0;
	            }
	        }
	        
	        left = right = 0;
	        // Right to left scan
	        for (int i = s.length() - 1; i >= 0; i--) {
	            if (s.charAt(i) == '(') {
	                left++;
	            } else {
	                right++;
	            }
	            
	            if (left == right) {
	                maxlength = Math.max(maxlength, 2 * left);
	            } else if (left >= right) {
	                left = right = 0;
	            }
	        }
	        
	        return maxlength;
	    }
	// dynamic approach
	    public int longestValidParentheses4(String s) {
	        int maxans = 0;
	        int dp[] = new int[s.length()];
	        
	        for (int i = 1; i < s.length(); i++) {
	            if (s.charAt(i) == ')') {
	                if (s.charAt(i - 1) == '(') {
	                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
	                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
	                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
	                }
	                maxans = Math.max(maxans, dp[i]);
	            }
	        }
	        return maxans;
	    }
	    
	    // brout force
	    
	    public int longestValidParentheses5(String s) {
	        int maxlen = 0;
	        for (int i = 0; i < s.length(); i++) {
	            for (int j = i + 2; j <= s.length(); j += 2) {
	                if (isValid(s.substring(i, j))) {
	                    maxlen = Math.max(maxlen, j - i);
	                }
	            }
	        }
	        return maxlen;
	    }
	    
	    private boolean isValid(String s) {
	        Stack<Character> stack = new Stack<>();
	        for (char c : s.toCharArray()) {
	            if (c == '(') {
	                stack.push(c);
	            } else if (!stack.isEmpty() && stack.peek() == '(') {
	                stack.pop();
	            } else {
	                return false;
	            }
	        }
	        return stack.isEmpty();
	    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = ")()())";
		int result = longestValidParentheses(s);
		System.out.println(result);
	}

}
