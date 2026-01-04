package backtracking;

import java.util.ArrayList;
import java.util.List;

public class PalindromPartitioning {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String s = "aab";
		List<List<String>> result = partition(s);
		System.out.println(result);

	}

	private static List<List<String>> partition(String s) {
		// TODO Auto-generated method stub
		

		List<List<String>> result = new ArrayList<>();
		backtrack(s, 0, new ArrayList<>(), result);

		return result;
	}

	private static void backtrack(String s, int index, ArrayList<String> perm, List<List<String>> result) {
		// TODO Auto-generated method stub
		if (s.length() == index) {
			result.add(new ArrayList<>(perm));
			return;
		}

		for (int end = index; end < s.length(); end++) {
			if (isPalindrom(s, index, end)) {
				perm.add(s.substring(index, end + 1));
				backtrack(s, end + 1, perm, result);
				perm.remove(perm.size() - 1);
			}

		}

	}

	private static boolean isPalindrom(String s, int index, int end) {
		// TODO Auto-generated method stub
		while (index < end) {
			if (s.charAt(index++) != s.charAt(end--))
				return false;
		}

		return true;
	}

}
