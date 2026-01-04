package string;



public class IsSubsequence {

    // 1️⃣ Two-Pointer (your solution, clean version)
    public static boolean isSubsequence1(String s, String t) {
        int i = 0, j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == s.length();
    }

    // 2️⃣ Index + String.indexOf (uses built-in search)
    // Time: O(|s| * |t|) worst case, but indexOf is optimized
    public static boolean isSubsequence2(String s, String t) {
        int index = -1;
        for (char c : s.toCharArray()) {
            index = t.indexOf(c, index + 1);
            if (index == -1) return false;
        }
        return true;
    }

    // 3️⃣ Dynamic Programming (LCS check)
    // If LCS(s,t) == s.length() -> subsequence
    public static boolean isSubsequence3(String s, String t) {
        int m = s.length(), n = t.length();
        int[][] dp = new int[m+1][n+1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i-1) == t.charAt(j-1))
                    dp[i][j] = 1 + dp[i-1][j-1];
                else
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        return dp[m][n] == m;
    }

    // 4️⃣ Recursion
    // Compare last characters and shrink strings
    public static boolean isSubsequence4(String s, String t) {
        return helper(s, t, s.length(), t.length());
    }
    private static boolean helper(String s, String t, int m, int n) {
        if (m == 0) return true;   // s fully matched
        if (n == 0) return false;  // t exhausted
        if (s.charAt(m-1) == t.charAt(n-1))
            return helper(s, t, m-1, n-1);
        return helper(s, t, m, n-1);
    }

    // 5️⃣ Preprocessing + Binary Search (for multiple queries)
    // Preprocess t into index lists for each char, then check
    // Time: O(|t| + |s| log |t|), good for many queries
    public static boolean isSubsequence5(String s, String t) {
        java.util.Map<Character, java.util.List<Integer>> map = new java.util.HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            map.computeIfAbsent(t.charAt(i), k -> new java.util.ArrayList<>()).add(i);
        }

        int prev = -1;
        for (char c : s.toCharArray()) {
            if (!map.containsKey(c)) return false;
            java.util.List<Integer> list = map.get(c);
            int pos = java.util.Collections.binarySearch(list, prev+1);
            if (pos < 0) pos = -pos - 1;
            if (pos == list.size()) return false;
            prev = list.get(pos);
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "abc", t = "ahbgdc";

        System.out.println("Two Pointer: " + isSubsequence1(s, t));
        System.out.println("IndexOf: " + isSubsequence2(s, t));
        System.out.println("DP LCS: " + isSubsequence3(s, t));
        System.out.println("Recursion: " + isSubsequence4(s, t));
        System.out.println("Binary Search Preprocessing: " + isSubsequence5(s, t));
    }
}

