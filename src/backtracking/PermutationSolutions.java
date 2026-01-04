package backtracking;

import java.util.*;

//===================== PROBLEM: Unique Permutations of a String =====================
//10 Different Approaches
public class PermutationSolutions {

 // 1. Backtracking + visited[] + skip duplicates
 public static List<String> permute1(String s) {
     List<String> res = new ArrayList<>();
     char[] arr = s.toCharArray();
     Arrays.sort(arr);
     boolean[] used = new boolean[arr.length];
     dfs(arr, new StringBuilder(), used, res);
     return res;
 }
 private static void dfs(char[] arr, StringBuilder path, boolean[] used, List<String> res) {
     if (path.length() == arr.length) {
         res.add(path.toString());
         return;
     }
     for (int i = 0; i < arr.length; i++) {
         if (used[i]) continue;
         if (i > 0 && arr[i] == arr[i-1] && !used[i-1]) continue;
         used[i] = true;
         path.append(arr[i]);
         dfs(arr, path, used, res);
         path.deleteCharAt(path.length()-1);
         used[i] = false;
     }
 }

 // 2. Backtracking + HashSet per level
 public static List<String> permute2(String s) {
     List<String> res = new ArrayList<>();
     backtrack2("", s, res);
     return res;
 }
 private static void backtrack2(String prefix, String rest, List<String> res) {
     if (rest.isEmpty()) {
         res.add(prefix);
         return;
     }
     HashSet<Character> seen = new HashSet<>();
     for (int i = 0; i < rest.length(); i++) {
         char c = rest.charAt(i);
         if (seen.contains(c)) continue;
         seen.add(c);
         backtrack2(prefix + c, rest.substring(0,i) + rest.substring(i+1), res);
     }
 }

 // 3. Next Permutation (lexicographic)
 public static List<String> permute3(String s) {
     List<String> res = new ArrayList<>();
     char[] arr = s.toCharArray();
     Arrays.sort(arr);
     do {
         res.add(new String(arr));
     } while (nextPermutation(arr));
     return res;
 }
 private static boolean nextPermutation(char[] a) {
     int i = a.length-2;
     while (i >= 0 && a[i] >= a[i+1]) i--;
     if (i < 0) return false;
     int j = a.length-1;
     while (a[j] <= a[i]) j--;
     swap(a,i,j);
     for (int l=i+1, r=a.length-1; l<r; l++,r--) swap(a,l,r);
     return true;
 }
 private static void swap(char[] a, int i, int j) {
     char t = a[i]; a[i]=a[j]; a[j]=t;
 }

 // 4. Recursive Insert Method
 public static List<String> permute4(String s) {
     if (s.isEmpty()) return Collections.singletonList("");
     char first = s.charAt(0);
     List<String> sub = permute4(s.substring(1));
     Set<String> res = new LinkedHashSet<>();
     for (String str : sub) {
         for (int i=0; i<=str.length(); i++) {
             res.add(str.substring(0,i) + first + str.substring(i));
         }
     }
     return new ArrayList<>(res);
 }

 // 5. Iterative BFS (Queue)
 public static List<String> permute5(String s) {
     Queue<String> q = new LinkedList<>();
     q.add("");
     for (char c : s.toCharArray()) {
         int size = q.size();
         Set<String> level = new LinkedHashSet<>();
         while (size-- > 0) {
             String cur = q.poll();
             for (int i = 0; i <= cur.length(); i++) {
                 level.add(cur.substring(0,i) + c + cur.substring(i));
             }
         }
         q.addAll(level);
     }
     return new ArrayList<>(q);
 }

 // 6. Heap’s Algorithm
 public static List<String> permute6(String s) {
     List<String> res = new ArrayList<>();
     char[] arr = s.toCharArray();
     heapPerm(arr, arr.length, res, new HashSet<>());
     return res;
 }
 private static void heapPerm(char[] arr, int n, List<String> res, Set<String> seen) {
     if (n == 1) {
         String str = new String(arr);
         if (seen.add(str)) res.add(str);
         return;
     }
     for (int i=0; i<n; i++) {
         heapPerm(arr, n-1, res, seen);
         if (n % 2 == 0) swap(arr, i, n-1);
         else swap(arr, 0, n-1);
     }
 }

 // 7. DFS + Map<Character, Count>
 public static List<String> permute7(String s) {
     List<String> res = new ArrayList<>();
     Map<Character,Integer> freq = new HashMap<>();
     for (char c: s.toCharArray()) freq.put(c, freq.getOrDefault(c,0)+1);
     dfsMap(freq, "", s.length(), res);
     return res;
 }
 private static void dfsMap(Map<Character,Integer> freq, String path, int len, List<String> res) {
     if (path.length() == len) {
         res.add(path);
         return;
     }
     for (char c: freq.keySet()) {
         int count = freq.get(c);
         if (count > 0) {
             freq.put(c, count-1);
             dfsMap(freq, path+c, len, res);
             freq.put(c, count);
         }
     }
 }

 // 8. Recursive Swap (Backtracking in place)
 public static List<String> permute8(String s) {
     List<String> res = new ArrayList<>();
     char[] arr = s.toCharArray();
     Arrays.sort(arr);
     backtrackSwap(arr, 0, res, new HashSet<>());
     return res;
 }
 private static void backtrackSwap(char[] arr, int idx, List<String> res, Set<String> seen) {
     if (idx == arr.length) {
         String str = new String(arr);
         if (seen.add(str)) res.add(str);
         return;
     }
     for (int i=idx; i<arr.length; i++) {
         swap(arr, idx, i);
         backtrackSwap(arr, idx+1, res, seen);
         swap(arr, idx, i);
     }
 }

 // 9. Using Stream + Recursion
 public static List<String> permute9(String s) {
     if (s.length() <= 1) return Arrays.asList(s);
     return s.chars()
         .distinct()
         .mapToObj(c -> (char)c)
         .flatMap(ch -> permute9(s.replaceFirst(ch+"",""))
             .stream().map(str -> ch + str))
         .toList();
 }

 // 10. Iterative Lexicographic (like STL next_permutation)
 public static List<String> permute10(String s) {
     char[] arr = s.toCharArray();
     Arrays.sort(arr);
     List<String> res = new ArrayList<>();
     do {
         res.add(new String(arr));
     } while (nextPermutation(arr));
     return res;
 }


 // ===================== DRIVER WITH TEST CASES =====================
 public static void main(String[] args) {
     String[] tests = {"abc", "aab", "KK", "ab"};
     for (String t : tests) {
         System.out.println("\nInput: " + t);
         System.out.println("Method1: " + permute1(t));
         System.out.println("Method2: " + permute2(t));
         System.out.println("Method3: " + permute3(t));
         System.out.println("Method4: " + permute4(t));
         System.out.println("Method5: " + permute5(t));
         System.out.println("Method6: " + permute6(t));
         System.out.println("Method7: " + permute7(t));
         System.out.println("Method8: " + permute8(t));
         System.out.println("Method9: " + permute9(t));
         System.out.println("Method10:" + permute10(t));
     }
 }
}
