package backtracking;

import java.util.ArrayList;
import java.util.List;

public class StringPermuBacktracking {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<String> result = findPermutation("ABC");
		System.out.println(result);

	}

	private static ArrayList<String> findPermutation(String s) {

		// Code here
		ArrayList<String> res = new ArrayList<>();
		boolean[] dp = new boolean[s.length()];
		helper(s, new StringBuilder(), dp, res);
		return res;

	}

	private static void helper(String s, StringBuilder curr, boolean[] dp, ArrayList<String> res) {
		// TODO Auto-generated method stub

		if (curr.length() == s.length()) {
			res.add(curr.toString());
			return;
		}

		for (int i = 0; i < s.length(); i++) {
			if (!dp[i]) {
				curr.append(s.charAt(i));
				dp[i]=true;
				helper(s, curr, dp, res);
				curr.deleteCharAt(curr.length()-1);
				dp[i]=false;

			}
		}
	}

}
