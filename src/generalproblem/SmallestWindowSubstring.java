package generalproblem;

import java.util.*;

public class SmallestWindowSubstring {

    // --------------------------------------------------------
    // 1. Sliding Window with HashMap (Most common & efficient)
    // --------------------------------------------------------
    public static String minWindow1(String s1, String s2) {
        if (s1.length() < s2.length()) return "";

        Map<Character, Integer> need = new HashMap<>();
        for (char c : s2.toCharArray()) need.put(c, need.getOrDefault(c, 0) + 1);

        int required = need.size();
        int formed = 0;
        Map<Character, Integer> window = new HashMap<>();

        int l = 0, r = 0;
        int[] ans = {-1, 0, 0}; // length, left, right

        while (r < s1.length()) {
            char c = s1.charAt(r);
            window.put(c, window.getOrDefault(c, 0) + 1);

            if (need.containsKey(c) && window.get(c).intValue() == need.get(c).intValue())
                formed++;

            while (l <= r && formed == required) {
                if (ans[0] == -1 || r - l + 1 < ans[0]) {
                    ans[0] = r - l + 1;
                    ans[1] = l;
                    ans[2] = r;
                }

                char leftChar = s1.charAt(l);
                window.put(leftChar, window.get(leftChar) - 1);
                if (need.containsKey(leftChar) && window.get(leftChar) < need.get(leftChar))
                    formed--;

                l++;
            }
            r++;
        }
        return ans[0] == -1 ? "" : s1.substring(ans[1], ans[2] + 1);
    }

    // --------------------------------------------------------
    // 2. Sliding Window with Array (Optimized for lowercase letters)
    // --------------------------------------------------------
    public static String minWindow2(String s1, String s2) {
        if (s1.length() < s2.length()) return "";

        int[] need = new int[26];
        for (char c : s2.toCharArray()) need[c - 'a']++;

        int[] window = new int[26];
        int required = s2.length();
        int l = 0, minLen = Integer.MAX_VALUE, start = 0;

        for (int r = 0; r < s1.length(); r++) {
            char c = s1.charAt(r);
            if (need[c - 'a'] > 0) {
                window[c - 'a']++;
                if (window[c - 'a'] <= need[c - 'a']) required--;
            }

            while (required == 0) {
                if (r - l + 1 < minLen) {
                    minLen = r - l + 1;
                    start = l;
                }
                char left = s1.charAt(l);
                if (need[left - 'a'] > 0) {
                    window[left - 'a']--;
                    if (window[left - 'a'] < need[left - 'a']) required++;
                }
                l++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s1.substring(start, start + minLen);
    }

    // --------------------------------------------------------
    // 3. Brute Force (Check all substrings) – O(n^3) 😵
    // --------------------------------------------------------
    public static String minWindow3(String s1, String s2) {
        int n = s1.length();
        String ans = "";
        int minLen = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                String sub = s1.substring(i, j + 1);
                if (containsAll(sub, s2) && sub.length() < minLen) {
                    ans = sub;
                    minLen = sub.length();
                }
            }
        }
        return ans;
    }

    private static boolean containsAll(String s, String t) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) freq[c - 'a']++;
        for (char c : t.toCharArray()) {
            if (--freq[c - 'a'] < 0) return false;
        }
        return true;
    }

    // --------------------------------------------------------
    // 4. Binary Search on Window Size + Check Feasibility
    // --------------------------------------------------------
    public static String minWindow4(String s1, String s2) {
        int n = s1.length();
        int left = s2.length(), right = n, bestLen = -1, start = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            int idx = validWindow(s1, s2, mid);
            if (idx != -1) {
                bestLen = mid;
                start = idx;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return bestLen == -1 ? "" : s1.substring(start, start + bestLen);
    }

    private static int validWindow(String s1, String s2, int len) {
        int[] need = new int[26];
        for (char c : s2.toCharArray()) need[c - 'a']++;

        for (int i = 0; i + len <= s1.length(); i++) {
            String sub = s1.substring(i, i + len);
            if (containsAll(sub, s2)) return i;
        }
        return -1;
    }

    // --------------------------------------------------------
    // 5. Two Pointers + Queue (store useful indices)
    // --------------------------------------------------------
    public static String minWindow5(String s1, String s2) {
        Map<Character, Integer> need = new HashMap<>();
        for (char c : s2.toCharArray()) need.put(c, need.getOrDefault(c, 0) + 1);

        Map<Character, Integer> window = new HashMap<>();
        Queue<Integer> q = new LinkedList<>();
        int l = 0, minLen = Integer.MAX_VALUE, start = 0;
        int count = 0;

        for (int r = 0; r < s1.length(); r++) {
            char c = s1.charAt(r);
            if (need.containsKey(c)) {
                q.add(r);
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c) <= need.get(c)) count++;

                while (count == s2.length()) {
                    int leftIdx = q.peek();
                    if (r - leftIdx + 1 < minLen) {
                        minLen = r - leftIdx + 1;
                        start = leftIdx;
                    }
                    char leftChar = s1.charAt(q.poll());
                    window.put(leftChar, window.get(leftChar) - 1);
                    if (window.get(leftChar) < need.get(leftChar)) count--;
                }
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s1.substring(start, start + minLen);
    }

    // --------------------------------------------------------
    // 6. Sliding Window with PriorityQueue
    // --------------------------------------------------------
    public static String minWindow6(String s1, String s2) {
        Map<Character, Integer> need = new HashMap<>();
        for (char c : s2.toCharArray()) need.put(c, need.getOrDefault(c, 0) + 1);

        Map<Character, Integer> window = new HashMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1] - a[0]));
        int l = 0, count = 0;

        for (int r = 0; r < s1.length(); r++) {
            char c = s1.charAt(r);
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c) <= need.get(c)) count++;
            }

            while (count == s2.length()) {
                pq.offer(new int[]{l, r});
                char left = s1.charAt(l);
                if (need.containsKey(left)) {
                    window.put(left, window.get(left) - 1);
                    if (window.get(left) < need.get(left)) count--;
                }
                l++;
            }
        }
        return pq.isEmpty() ? "" : s1.substring(pq.peek()[0], pq.peek()[1] + 1);
    }

    // --------------------------------------------------------
    // 7. Recursive Backtracking (inefficient, just educational)
    // --------------------------------------------------------
    public static String minWindow7(String s1, String s2) {
        return helper(s1, s2, 0, s1.length());
    }

    private static String helper(String s1, String s2, int start, int minLen) {
        if (start >= s1.length()) return "";
        String ans = "";
        for (int end = start; end < s1.length(); end++) {
            String sub = s1.substring(start, end + 1);
            if (containsAll(sub, s2) && sub.length() < minLen) {
                ans = sub;
                minLen = sub.length();
            }
        }
        String next = helper(s1, s2, start + 1, minLen);
        if (!next.isEmpty() && next.length() < minLen) ans = next;
        return ans;
    }

    // --------------------------------------------------------
    // 8. Sliding Window with Two Frequency Arrays (128 ASCII)
    // --------------------------------------------------------
    public static String minWindow8(String s1, String s2) {
        int[] need = new int[128];
        for (char c : s2.toCharArray()) need[c]++;
        int required = s2.length();

        int l = 0, minLen = Integer.MAX_VALUE, start = 0;
        for (int r = 0; r < s1.length(); r++) {
            if (need[s1.charAt(r)]-- > 0) required--;
            while (required == 0) {
                if (r - l + 1 < minLen) {
                    minLen = r - l + 1;
                    start = l;
                }
                if (++need[s1.charAt(l++)] > 0) required++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s1.substring(start, start + minLen);
    }

    // --------------------------------------------------------
    // 9. Using Sliding Window + StringBuilder for Frequency
    // --------------------------------------------------------
    public static String minWindow9(String s1, String s2) {
        int[] need = new int[26];
        for (char c : s2.toCharArray()) need[c - 'a']++;

        int l = 0, count = s2.length();
        int minLen = Integer.MAX_VALUE, start = 0;

        for (int r = 0; r < s1.length(); r++) {
            if (need[s1.charAt(r) - 'a']-- > 0) count--;

            while (count == 0) {
                if (r - l + 1 < minLen) {
                    minLen = r - l + 1;
                    start = l;
                }
                if (++need[s1.charAt(l++) - 'a'] > 0) count++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s1.substring(start, start + minLen);
    }

    // --------------------------------------------------------
    // 10. Dynamic Programming (not efficient, educational)
    // --------------------------------------------------------
    public static String minWindow10(String s1, String s2) {
        int n = s1.length();
        int[][] dp = new int[n + 1][s2.length() + 1];
        Arrays.fill(dp[0], -1);

        for (int i = 1; i <= n; i++) {
            dp[i][0] = i;
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        int start = 0, len = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            if (dp[i][s2.length()] != -1) {
                int newLen = i - dp[i][s2.length()];
                if (newLen < len) {
                    start = dp[i][s2.length()];
                    len = newLen;
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : s1.substring(start, start + len);
    }

    // --------------------------------------------------------
    // Test Cases
    // --------------------------------------------------------
    public static void main(String[] args) {
        System.out.println(minWindow1("timetopractice", "toc")); // toprac
        System.out.println(minWindow2("zoomlazapzo", "oza"));    // apzo
        System.out.println(minWindow3("zoom", "zooe"));          // ""
        System.out.println(minWindow4("timetopractice", "toc")); // toprac
        System.out.println(minWindow5("zoomlazapzo", "oza"));    // apzo
        System.out.println(minWindow6("timetopractice", "toc")); // toprac
        System.out.println(minWindow7("zoomlazapzo", "oza"));    // apzo
        System.out.println(minWindow8("zoomlazapzo", "oza"));    // apzo
        System.out.println(minWindow9("zoom", "zooe"));          // ""
        System.out.println(minWindow10("timetopractice", "toc"));// toprac
    }
}
