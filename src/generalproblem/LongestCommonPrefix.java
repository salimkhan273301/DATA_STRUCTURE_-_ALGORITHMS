package generalproblem;

public class LongestCommonPrefix {

    // Approach 1: Horizontal Scanning
    public String longestCommonPrefix1(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }

    // Approach 2: Vertical Scanning
    public String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    // Approach 3: Divide and Conquer
    public String longestCommonPrefix3(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        return longestCommonPrefix(strs, 0, strs.length - 1);
    }

    private String longestCommonPrefix(String[] strs, int left, int right) {
        if (left == right) {
            return strs[left];
        }
        int mid = (left + right) / 2;
        String lcpLeft = longestCommonPrefix(strs, left, mid);
        String lcpRight = longestCommonPrefix(strs, mid + 1, right);
        return commonPrefix(lcpLeft, lcpRight);
    }

    private String commonPrefix(String left, String right) {
        int min = Math.min(left.length(), right.length());
        for (int i = 0; i < min; i++) {
            if (left.charAt(i) != right.charAt(i)) {
                return left.substring(0, i);
            }
        }
        return left.substring(0, min);
    }

    // Approach 4: Binary Search
    public String longestCommonPrefix4(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        int minLen = Integer.MAX_VALUE;
        for (String str : strs) {
            minLen = Math.min(minLen, str.length());
        }
        int low = 1;
        int high = minLen;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (isCommonPrefix(strs, mid)) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return strs[0].substring(0, (low + high) / 2);
    }

    private boolean isCommonPrefix(String[] strs, int len) {
        String str1 = strs[0].substring(0, len);
        for (int i = 1; i < strs.length; i++) {
            if (!strs[i].startsWith(str1)) {
                return false;
            }
        }
        return true;
    }

    // Approach 5: Using Trie Data Structure
    public String longestCommonPrefix5(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        if (strs.length == 1) return strs[0];
        Trie trie = new Trie();
        for (int i = 1; i < strs.length; i++) {
            trie.insert(strs[i]);
        }
        return trie.searchLongestPrefix(strs[0]);
    }

    class TrieNode {
        TrieNode[] children;
        boolean isEnd;
        int size;

        public TrieNode() {
            children = new TrieNode[26];
            isEnd = false;
            size = 0;
        }
    }

    class Trie {
        TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new TrieNode();
                    node.size++;
                }
                node = node.children[c - 'a'];
            }
            node.isEnd = true;
        }

        public String searchLongestPrefix(String word) {
            TrieNode node = root;
            StringBuilder prefix = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (node.children[c - 'a'] != null && node.size == 1 && !node.isEnd) {
                    prefix.append(c);
                    node = node.children[c - 'a'];
                } else {
                    return prefix.toString();
                }
            }
            return prefix.toString();
        }
    }

    // Test Cases
    public static void main(String[] args) {
        LongestCommonPrefix lcp = new LongestCommonPrefix();
        String[][] testCases = {
            {"flower", "flow", "flight"}, // Expected: "fl"
            {"dog", "racecar", "car"},    // Expected: ""
            {"", "abc", "def"},           // Expected: ""
            {"abc", "abc", "abc"},        // Expected: "abc"
            {"a"},                        // Expected: "a"
            {"ab", "a"},                  // Expected: "a"
            {"a", "b"},                  // Expected: ""
            {"aa", "a"},                 // Expected: "a"
            {"aaa", "aa", "aaa"},         // Expected: "aa"
            {"c", "c"}                   // Expected: "c"
        };

        for (int i = 0; i < testCases.length; i++) {
            String[] strs = testCases[i];
            String expected = "";
            if (i == 0) expected = "fl";
            else if (i == 1) expected = "";
            else if (i == 2) expected = "";
            else if (i == 3) expected = "abc";
            else if (i == 4) expected = "a";
            else if (i == 5) expected = "a";
            else if (i == 6) expected = "";
            else if (i == 7) expected = "a";
            else if (i == 8) expected = "aa";
            else if (i == 9) expected = "c";

            String result1 = lcp.longestCommonPrefix1(strs);
            String result2 = lcp.longestCommonPrefix2(strs);
            String result3 = lcp.longestCommonPrefix3(strs);
            String result4 = lcp.longestCommonPrefix4(strs);
            String result5 = lcp.longestCommonPrefix5(strs);

            System.out.println("Test Case " + (i + 1) + ": " + java.util.Arrays.toString(strs));
            System.out.println("Expected: " + expected);
            System.out.println("Approach 1: " + result1 + " " + (result1.equals(expected) ? "✓" : "✗"));
            System.out.println("Approach 2: " + result2 + " " + (result2.equals(expected) ? "✓" : "✗"));
            System.out.println("Approach 3: " + result3 + " " + (result3.equals(expected) ? "✓" : "✗"));
            System.out.println("Approach 4: " + result4 + " " + (result4.equals(expected) ? "✓" : "✗"));
            System.out.println("Approach 5: " + result5 + " " + (result5.equals(expected) ? "✓" : "✗"));
            System.out.println();
        }
    }
}