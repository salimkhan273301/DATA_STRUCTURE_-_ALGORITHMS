package dynamicprograming;

import java.util.HashMap;
import java.util.Map;

public class FabonaciSeries {

	public static void main(String[] args) {
		System.out.println(fibonacci(10));
	}

	private static Long fibonacci(int n) {
		Map<Integer, Long> memo = new HashMap<>();

		if (n <= 1) {
			return (long) n;
		}

		if (memo.containsKey(n)) {
			return memo.get(n);
		}

		long result=fibonacci(n - 1) + fibonacci(n - 2);

		memo.put(n, result);

		return result;
	}

}
